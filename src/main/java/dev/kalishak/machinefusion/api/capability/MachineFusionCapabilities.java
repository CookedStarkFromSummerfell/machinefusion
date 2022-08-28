package dev.kalishak.machinefusion.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public final class MachineFusionCapabilities {

    public static final Capability<EnergyStorage> ENERGY_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<FluidStorage> FLUID_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<ItemEnergyStorage> ITEM_ENERGY_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<ItemFluidStorage> ITEM_FLUID_STORAGE = CapabilityManager.get(new CapabilityToken<>() {});
}
