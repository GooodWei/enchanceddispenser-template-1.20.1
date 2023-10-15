package com.godwei.config;

import com.google.gson.annotations.Expose;

public class Config {
    @Expose
    private boolean canBucketFill;
    @Expose
    private boolean canBucketExtract;
    @Expose
    private boolean canDyeEntities;
    @Expose
    private boolean canBottleExtract;

    public Config(boolean canBucketFill, boolean canBucketExtract, boolean canDyeEntities, boolean canBottleExtract) {
        this.canBucketFill = canBucketFill;
        this.canBucketExtract = canBucketExtract;
        this.canDyeEntities = canDyeEntities;
        this.canBottleExtract = canBottleExtract;
    }

    public boolean CanBucketFill() {
        return canBucketFill;
    }

    public void setBucketFill(boolean canBucketFill) {
        this.canBucketFill = canBucketFill;
    }

    public boolean CanBucketExtract() {
        return canBucketExtract;
    }

    public void setBucketExtract(boolean canBucketExtract) {
        this.canBucketExtract = canBucketExtract;
    }

    public boolean isCanDyeEntities() {
        return canDyeEntities;
    }

    public void setCanDyeEntities(boolean canDyeEntities) {
        this.canDyeEntities = canDyeEntities;
    }

    public boolean CanBottleExtract() {
        return canBottleExtract;
    }

    public void setBottleExtract(boolean canBottleExtract) {
        this.canBottleExtract = canBottleExtract;
    }
}
