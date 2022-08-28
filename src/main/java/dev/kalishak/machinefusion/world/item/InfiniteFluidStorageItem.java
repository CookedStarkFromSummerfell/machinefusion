package dev.kalishak.machinefusion.world.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class InfiniteFluidStorageItem extends AbstractFluidItem {
    protected InfiniteFluidStorageItem(long capacity, Supplier<Fluid> acceptedType, Properties properties) {
        super(capacity, acceptedType, properties);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return null;
    }
}
