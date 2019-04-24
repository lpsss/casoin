package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Supplier.
 */
@Entity
@Table(name = "supplier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "piva")
    private String piva;

    @Column(name = "rag_sociale")
    private String ragSociale;

    @Column(name = "indirizzo")
    private String indirizzo;

    @ManyToOne
    @JsonIgnoreProperties("suppliers")
    private ODVHead oDVHead;

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

    public Supplier nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Supplier cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPiva() {
        return piva;
    }

    public Supplier piva(String piva) {
        this.piva = piva;
        return this;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getRagSociale() {
        return ragSociale;
    }

    public Supplier ragSociale(String ragSociale) {
        this.ragSociale = ragSociale;
        return this;
    }

    public void setRagSociale(String ragSociale) {
        this.ragSociale = ragSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Supplier indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public ODVHead getODVHead() {
        return oDVHead;
    }

    public Supplier oDVHead(ODVHead oDVHead) {
        this.oDVHead = oDVHead;
        return this;
    }

    public void setODVHead(ODVHead oDVHead) {
        this.oDVHead = oDVHead;
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
        Supplier supplier = (Supplier) o;
        if (supplier.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Supplier{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", piva='" + getPiva() + "'" +
            ", ragSociale='" + getRagSociale() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            "}";
    }
}
