package dev.kalishak.machinefusion.api.capability.liquid;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ItemFluidStorage extends FluidStorage {

    /**
     * @return This energy storage holder
     */
    ItemStack getInstance();
}
