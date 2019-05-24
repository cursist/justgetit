package be.vdab.justgetit.manager.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class SubcategorieMargeWijziging {
    @NotNull @Positive
    private Long id;
    @Positive
    private BigDecimal minimumMargePercent;
    @Positive
    private BigDecimal minimumMargeBedrag;

    public SubcategorieMargeWijziging(Long id, BigDecimal minimumMargePercent, BigDecimal minimumMargeBedrag) {
        this.id = id;
        this.minimumMargePercent = minimumMargePercent;
        this.minimumMargeBedrag = minimumMargeBedrag;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMinimumMargePercent() {
        return minimumMargePercent;
    }

    public BigDecimal getMinimumMargeBedrag() {
        return minimumMargeBedrag;
    }
}
