package dev.kalishak.machinefusion.util;

import net.minecraft.util.StringRepresentable;

public enum EnergyType implements StringRepresentable {

    REDSTONE_FLUX("RF", 1.0F),
    FORGE_ENERGY("FE", 1.0F),
    GC_ENERGY("gJ", 0.75F),
    MEKANISM_ENERGY("MJ", 1.25F);

    private final String symbol;
    private final float conversion;

    EnergyType(String symbol, float conversion) {
        this.symbol = symbol;
        this.conversion = conversion;
    }

    @Override
    public String getSerializedName() {
        return this.symbol;
    }

    public float getConversion() {
        return conversion;
    }
}
