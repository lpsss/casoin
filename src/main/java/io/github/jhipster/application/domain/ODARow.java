package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A ODARow.
 */
@Entity
@Table(name = "oda_row")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ODARow implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qta")
    private Integer qta;

    @Column(name = "costo", precision = 10, scale = 2)
    private BigDecimal costo;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private ODAHead oDAHead;

    @ManyToOne
    @JsonIgnoreProperties("oDARows")
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQta() {
        return qta;
    }

    public ODARow qta(Integer qta) {
        this.qta = qta;
        return this;
    }

    public void setQta(Integer qta) {
        this.qta = qta;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public ODARow costo(BigDecimal costo) {
        this.costo = costo;
        return this;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public ODAHead getODAHead() {
        return oDAHead;
    }

    public ODARow oDAHead(ODAHead oDAHead) {
        this.oDAHead = oDAHead;
        return this;
    }

    public void setODAHead(ODAHead oDAHead) {
        this.oDAHead = oDAHead;
    }

    public Product getProduct() {
        return product;
    }

    public ODARow product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        ODARow oDARow = (ODARow) o;
        if (oDARow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oDARow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ODARow{" +
            "id=" + getId() +
            ", qta=" + getQta() +
            ", costo=" + getCosto() +
            "}";
    }
}
