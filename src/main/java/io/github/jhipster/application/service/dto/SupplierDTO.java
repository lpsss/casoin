package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Supplier entity.
 */
public class SupplierDTO implements Serializable {

    private Long id;

    private String nome;

    private String cognome;

    private String piva;

    private String ragSociale;

    private String indirizzo;


    private Long oDVHeadId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getRagSociale() {
        return ragSociale;
    }

    public void setRagSociale(String ragSociale) {
        this.ragSociale = ragSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Long getODVHeadId() {
        return oDVHeadId;
    }

    public void setODVHeadId(Long oDVHeadId) {
        this.oDVHeadId = oDVHeadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierDTO supplierDTO = (SupplierDTO) o;
        if (supplierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", piva='" + getPiva() + "'" +
            ", ragSociale='" + getRagSociale() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", oDVHead=" + getODVHeadId() +
            "}";
    }
}
