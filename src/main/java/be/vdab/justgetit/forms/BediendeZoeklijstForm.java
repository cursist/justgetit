package be.vdab.justgetit.forms;

import javax.validation.constraints.NotNull;

public class BediendeZoeklijstForm {
    @NotNull
    private long productFormId;
    @NotNull
    private long subcategorieFormId;

    public BediendeZoeklijstForm(long productFormId, long subcategorieFormId) {
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
