package lopez.irving.favorites.mvvm.view.model;

import android.support.annotation.DrawableRes;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class ProductUiModel {

    private String iconUrl;
    @DrawableRes
    private int linioPlusImageRes;
    @DrawableRes
    private int conditionTypeImageRes;
    private boolean isImported;
    private boolean isFreeShipping;
    private boolean isFavorite;

    public ProductUiModel(String iconUrl, int linioPlusImageRes, int conditionTypeImageRes,
                          boolean isImported, boolean isFreeShipping, boolean isFavorite) {
        this.iconUrl = iconUrl;
        this.linioPlusImageRes = linioPlusImageRes;
        this.conditionTypeImageRes = conditionTypeImageRes;
        this.isImported = isImported;
        this.isFreeShipping = isFreeShipping;
        this.isFavorite = isFavorite;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    @DrawableRes
    public int getLinioPlusImageRes() {
        return linioPlusImageRes;
    }

    @DrawableRes
    public int getConditionTypeImageRes() {
        return conditionTypeImageRes;
    }

    public boolean isImported() {
        return isImported;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
