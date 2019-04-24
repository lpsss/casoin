package io.github.jhipster.application.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ODVHead entity.
 */
public class ODVHeadDTO implements Serializable {

    private Long id;

    private Integer nrFt;

    private LocalDate dataFattura;

    private Integer totaleFt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNrFt() {
        return nrFt;
    }

    public void setNrFt(Integer nrFt) {
        this.nrFt = nrFt;
    }

    public LocalDate getDataFattura() {
        return dataFattura;
    }

    public void setDataFattura(LocalDate dataFattura) {
        this.dataFattura = dataFattura;
    }

    public Integer getTotaleFt() {
        return totaleFt;
    }

    public void setTotaleFt(Integer totaleFt) {
        this.totaleFt = totaleFt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ODVHeadDTO oDVHeadDTO = (ODVHeadDTO) o;
        if (oDVHeadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oDVHeadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ODVHeadDTO{" +
            "id=" + getId() +
            ", nrFt=" + getNrFt() +
            ", dataFattura='" + getDataFattura() + "'" +
            ", totaleFt=" + getTotaleFt() +
            "}";
    }
}
