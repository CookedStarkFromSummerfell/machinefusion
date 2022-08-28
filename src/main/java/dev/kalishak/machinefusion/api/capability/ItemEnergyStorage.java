package dev.kalishak.machinefusion.api.capability;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ItemEnergyStorage extends EnergyStorage {

    /**
     * @return This energy storage holder
     */
    ItemStack getInstance();

    /**
     * @return Maximum rate of I/O
     */
    long maxTransfer();
}
