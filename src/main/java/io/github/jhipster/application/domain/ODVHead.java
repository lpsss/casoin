package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ODVHead.
 */
@Entity
@Table(name = "odv_head")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ODVHead implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_ft")
    private Integer nrFt;

    @Column(name = "data_fattura")
    private LocalDate dataFattura;

    @Column(name = "totale_ft")
    private Integer totaleFt;

    @OneToMany(mappedBy = "oDVHead")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ODVRow> orders = new HashSet<>();
    @OneToMany(mappedBy = "oDVHead")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Supplier> suppliers = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNrFt() {
        return nrFt;
    }

    public ODVHead nrFt(Integer nrFt) {
        this.nrFt = nrFt;
        return this;
    }

    public void setNrFt(Integer nrFt) {
        this.nrFt = nrFt;
    }

    public LocalDate getDataFattura() {
        return dataFattura;
    }

    public ODVHead dataFattura(LocalDate dataFattura) {
        this.dataFattura = dataFattura;
        return this;
    }

    public void setDataFattura(LocalDate dataFattura) {
        this.dataFattura = dataFattura;
    }

    public Integer getTotaleFt() {
        return totaleFt;
    }

    public ODVHead totaleFt(Integer totaleFt) {
        this.totaleFt = totaleFt;
        return this;
    }

    public void setTotaleFt(Integer totaleFt) {
        this.totaleFt = totaleFt;
    }

    public Set<ODVRow> getOrders() {
        return orders;
    }

    public ODVHead orders(Set<ODVRow> oDVRows) {
        this.orders = oDVRows;
        return this;
    }

    public ODVHead addOrder(ODVRow oDVRow) {
        this.orders.add(oDVRow);
        oDVRow.setODVHead(this);
        return this;
    }

    public ODVHead removeOrder(ODVRow oDVRow) {
        this.orders.remove(oDVRow);
        oDVRow.setODVHead(null);
        return this;
    }

    public void setOrders(Set<ODVRow> oDVRows) {
        this.orders = oDVRows;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public ODVHead suppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
        return this;
    }

    public ODVHead addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
        supplier.setODVHead(this);
        return this;
    }

    public ODVHead removeSupplier(Supplier supplier) {
        this.suppliers.remove(supplier);
        supplier.setODVHead(null);
        return this;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ODVHead oDVHead = (ODVHead) o;
        if (oDVHead.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oDVHead.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ODVHead{" +
            "id=" + getId() +
            ", nrFt=" + getNrFt() +
            ", dataFattura='" + getDataFattura() + "'" +
            ", totaleFt=" + getTotaleFt() +
            "}";
    }
}
