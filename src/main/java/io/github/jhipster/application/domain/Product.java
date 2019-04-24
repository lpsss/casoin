package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "codice")
    private String codice;

    @Column(name = "marca")
    private String marca;

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ODVRow> oDVRows = new HashSet<>();
    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ODARow> oDARows = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Product nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public Product codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getMarca() {
        return marca;
    }

    public Product marca(String marca) {
        this.marca = marca;
        return this;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Set<ODVRow> getODVRows() {
        return oDVRows;
    }

    public Product oDVRows(Set<ODVRow> oDVRows) {
        this.oDVRows = oDVRows;
        return this;
    }

    public Product addODVRow(ODVRow oDVRow) {
        this.oDVRows.add(oDVRow);
        oDVRow.setProduct(this);
        return this;
    }

    public Product removeODVRow(ODVRow oDVRow) {
        this.oDVRows.remove(oDVRow);
        oDVRow.setProduct(null);
        return this;
    }

    public void setODVRows(Set<ODVRow> oDVRows) {
        this.oDVRows = oDVRows;
    }

    public Set<ODARow> getODARows() {
        return oDARows;
    }

    public Product oDARows(Set<ODARow> oDARows) {
        this.oDARows = oDARows;
        return this;
    }

    public Product addODARow(ODARow oDARow) {
        this.oDARows.add(oDARow);
        oDARow.setProduct(this);
        return this;
    }

    public Product removeODARow(ODARow oDARow) {
        this.oDARows.remove(oDARow);
        oDARow.setProduct(null);
        return this;
    }

    public void setODARows(Set<ODARow> oDARows) {
        this.oDARows = oDARows;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", codice='" + getCodice() + "'" +
            ", marca='" + getMarca() + "'" +
            "}";
    }
}
