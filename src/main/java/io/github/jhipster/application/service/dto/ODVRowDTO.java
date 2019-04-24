package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the ODVRow entity.
 */
public class ODVRowDTO implements Serializable {

    private Long id;

    private Integer qta;

    private BigDecimal costo;


    private Long oDVHeadId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQta() {
        return qta;
    }

    public void setQta(Integer qta) {
        this.qta = qta;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Long getODVHeadId() {
        return oDVHeadId;
    }

    public void setODVHeadId(Long oDVHeadId) {
        this.oDVHeadId = oDVHeadId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ODVRowDTO oDVRowDTO = (ODVRowDTO) o;
        if (oDVRowDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oDVRowDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ODVRowDTO{" +
            "id=" + getId() +
            ", qta=" + getQta() +
            ", costo=" + getCosto() +
            ", oDVHead=" + getODVHeadId() +
            ", product=" + getProductId() +
            "}";
    }
}
