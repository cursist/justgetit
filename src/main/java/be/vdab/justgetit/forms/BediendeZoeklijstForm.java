package be.vdab.justgetit.forms;

import javax.validation.constraints.NotNull;

public class BediendeZoeklijstForm {
    @NotNull
    private Long productFormId;
    @NotNull
    private Long subcategorieFormId;

    public BediendeZoeklijstForm(Long productFormId, Long subcategorieFormId) {
        this.productFormId = productFormId;
        this.subcategorieFormId = subcategorieFormId;
    }

    public long getProductFormId() {
        return productFormId;
    }

    public long getSubcategorieFormId() {
        return subcategorieFormId;
    }
}
