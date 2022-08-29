package dev.kalishak.machinefusion.api.capability.temperature;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringRepresentable;

public enum TemperatureType implements StringRepresentable {
    KELVIN("K", ChatFormatting.GREEN),
    CELSIUS("\u00B0C", ChatFormatting.GOLD),
    FAHRENHEIT("\u00B0F", ChatFormatting.DARK_PURPLE);

    private String symbol;
    private ChatFormatting color;

    TemperatureType(String symbol, ChatFormatting color) {
        this.symbol = symbol;
        this.color = color;
    }

    @Override
    public String getSerializedName() {
        return this.symbol;
    }

    public ChatFormatting getColor() {
        return color;
    }

    public static int convertTo(TemperatureType type, int value) {
        switch (type) {
            case CELSIUS -> { return value - 273; }
            case FAHRENHEIT -> { return (int) Math.floor((value - 273) * 1.8F + 32); }
            default -> { return value; }
        }
    }

    public static MutableComponent tooltipFromTemperature(TemperatureType type, int value) {
        if (value < 0) throw new IllegalArgumentException("Temperature cannot be below 0! That's just impossible");

        String s = "tooltip.machinefusion.";

        if (value > 333) s += "hot";
        else if (value > 311) s += "warm";
        else if (value > 294) s += "ordinal";
        else if (value > 279) s += "cool";
        else if (value > 1) s += "freeze";
        else s += "ordinal";
        return Component.translatable(s, Component.literal(convertTo(type, value) + type.getSerializedName()).withStyle(type.getColor()));
    }
}
