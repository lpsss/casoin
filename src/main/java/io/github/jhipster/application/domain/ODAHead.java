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
 * A ODAHead.
 */
@Entity
@Table(name = "oda_head")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ODAHead implements Serializable {

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

    @OneToMany(mappedBy = "oDAHead")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ODARow> orders = new HashSet<>();
    @OneToMany(mappedBy = "oDAHead")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Customer> customers = new HashSet<>();
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

    public ODAHead nrFt(Integer nrFt) {
        this.nrFt = nrFt;
        return this;
    }

    public void setNrFt(Integer nrFt) {
        this.nrFt = nrFt;
    }

    public LocalDate getDataFattura() {
        return dataFattura;
    }

    public ODAHead dataFattura(LocalDate dataFattura) {
        this.dataFattura = dataFattura;
        return this;
    }

    public void setDataFattura(LocalDate dataFattura) {
        this.dataFattura = dataFattura;
    }

    public Integer getTotaleFt() {
        return totaleFt;
    }

    public ODAHead totaleFt(Integer totaleFt) {
        this.totaleFt = totaleFt;
        return this;
    }

    public void setTotaleFt(Integer totaleFt) {
        this.totaleFt = totaleFt;
    }

    public Set<ODARow> getOrders() {
        return orders;
    }

    public ODAHead orders(Set<ODARow> oDARows) {
        this.orders = oDARows;
        return this;
    }

    public ODAHead addOrder(ODARow oDARow) {
        this.orders.add(oDARow);
        oDARow.setODAHead(this);
        return this;
    }

    public ODAHead removeOrder(ODARow oDARow) {
        this.orders.remove(oDARow);
        oDARow.setODAHead(null);
        return this;
    }

    public void setOrders(Set<ODARow> oDARows) {
        this.orders = oDARows;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public ODAHead customers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public ODAHead addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setODAHead(this);
        return this;
    }

    public ODAHead removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.setODAHead(null);
        return this;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
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
        ODAHead oDAHead = (ODAHead) o;
        if (oDAHead.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oDAHead.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ODAHead{" +
            "id=" + getId() +
            ", nrFt=" + getNrFt() +
            ", dataFattura='" + getDataFattura() + "'" +
            ", totaleFt=" + getTotaleFt() +
            "}";
    }
}
