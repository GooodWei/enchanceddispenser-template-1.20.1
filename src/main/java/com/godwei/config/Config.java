package com.godwei.config;

import com.google.gson.annotations.Expose;

public class Config {
    @Expose
    private boolean canBucketFill;
    @Expose
    private boolean canBucketExtract;
    @Expose
    private boolean canBottleFill;
    @Expose
    private boolean canBottleExtract;

    public Config(boolean canBucketFill, boolean canBucketExtract, boolean canBottleFill, boolean canBottleExtract) {
        this.canBucketFill = canBucketFill;
        this.canBucketExtract = canBucketExtract;
        this.canBottleFill = canBottleFill;
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

    public boolean CanBottleFill() {
        return canBottleFill;
    }

    public void setBottleFill(boolean canBottleFill) {
        this.canBottleFill = canBottleFill;
    }

    public boolean CanBottleExtract() {
        return canBottleExtract;
    }

    public void setBottleExtract(boolean canBottleExtract) {
        this.canBottleExtract = canBottleExtract;
    }
}
