package io.github.jhipster.application.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the ODARow entity.
 */
public class ODARowDTO implements Serializable {

    private Long id;

    private Integer qta;

    private BigDecimal costo;


    private Long oDAHeadId;

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

    public Long getODAHeadId() {
        return oDAHeadId;
    }

    public void setODAHeadId(Long oDAHeadId) {
        this.oDAHeadId = oDAHeadId;
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

        ODARowDTO oDARowDTO = (ODARowDTO) o;
        if (oDARowDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oDARowDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ODARowDTO{" +
            "id=" + getId() +
            ", qta=" + getQta() +
            ", costo=" + getCosto() +
            ", oDAHead=" + getODAHeadId() +
            ", product=" + getProductId() +
            "}";
    }
}
