package dev.kalishak.machinefusion.api.capability;

import dev.kalishak.machinefusion.api.capability.energy.EnergyStorage;
import dev.kalishak.machinefusion.api.capability.energy.ItemEnergyStorage;
import dev.kalishak.machinefusion.api.capability.liquid.FluidStorage;
import dev.kalishak.machinefusion.api.capability.liquid.ItemFluidStorage;
import dev.kalishak.machinefusion.api.capability.temperature.TemperatureSensitive;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public final class MachineFusionCapabilities {

    public static final Capability<EnergyStorage> ENERGY_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<FluidStorage> FLUID_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<ItemEnergyStorage> ITEM_ENERGY_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<ItemFluidStorage> ITEM_FLUID_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<TemperatureSensitive> TEMPERATURE_SENSITIVE = CapabilityManager.get(new CapabilityToken<>() {});
}
