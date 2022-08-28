package dev.kalishak.machinefusion.world.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SingleFluidStorageItem extends AbstractFluidItem {

    protected SingleFluidStorageItem(long capacity, Supplier<Fluid> acceptedType, Properties properties) {
        super(capacity, acceptedType, properties);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new Wrapper(stack, acceptedType.get(), this.capacity);
    }

    private final class Wrapper extends AbstractFluidItem.Wrapper {

        protected Wrapper(ItemStack holder, @NotNull Fluid acceptedFluid, long capacity) {
            super(holder, acceptedFluid, capacity);
        }

        @Override
        public void setFluid(@NotNull Fluid fluid) {
            if (!this.acceptedFluid.isSame(Fluids.EMPTY)) throw new IllegalStateException("Cannot change the accepted fluid!");
            super.setFluid(fluid);
        }
    }
}
