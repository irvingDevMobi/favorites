package lopez.irving.favorites.mvvm.data.remote.model;

import android.support.annotation.Nullable;

/**
 * @author irving.lopez
 * @since 19/11/2017.
 */

public class ProductResponse {

    private long id;
    private String name;
    private double wishListPrice;
    private String slug;
    private String url;
    private String image;
    private int linioPlusLevel;
    private String conditionType;
    private boolean freeShipping;
    private boolean imported;
    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWishListPrice() {
        return wishListPrice;
    }

    public void setWishListPrice(double wishListPrice) {
        this.wishListPrice = wishListPrice;
    }

    @Nullable
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLinioPlusLevel() {
        return linioPlusLevel;
    }

    public void setLinioPlusLevel(int linioPlusLevel) {
        this.linioPlusLevel = linioPlusLevel;
    }

    @Nullable
    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
