package com.godwei.config;

import com.google.gson.annotations.Expose;

public class Config {
    @Expose
    private boolean canBucketFill;
    @Expose
    private boolean canBucketExtract;
    @Expose
    private boolean canPaintEntities;
    @Expose
    private boolean canBottleExtract;

    @Expose
    private boolean canDispenserMineBlock;


    public Config(boolean canBucketFill,
                  boolean canBucketExtract,
                  boolean canPaintEntities,
                  boolean canBottleExtract,
                  boolean canDispenserMineBlock) {
        this.canBucketFill = canBucketFill;
        this.canBucketExtract = canBucketExtract;
        this.canPaintEntities = canPaintEntities;
        this.canBottleExtract = canBottleExtract;
        this.canDispenserMineBlock = canDispenserMineBlock;
    }


    public boolean isCanDispenserMineBlock() {
        return canDispenserMineBlock;
    }

    public void setCanDispenserMineBlock(boolean canDispenserMineBlock) {
        this.canDispenserMineBlock = canDispenserMineBlock;
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

    public boolean isCanPaintEntities() {
        return canPaintEntities;
    }

    public void setCanPaintEntities(boolean canPaintEntities) {
        this.canPaintEntities = canPaintEntities;
    }

    public boolean CanBottleExtract() {
        return canBottleExtract;
    }

    public void setBottleExtract(boolean canBottleExtract) {
        this.canBottleExtract = canBottleExtract;
    }
}
