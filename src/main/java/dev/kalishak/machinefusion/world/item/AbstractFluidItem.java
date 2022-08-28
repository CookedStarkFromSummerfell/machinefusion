package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.api.capability.FluidStorage;
import dev.kalishak.machinefusion.api.capability.ItemFluidStorage;
import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import dev.kalishak.machinefusion.world.fluid.LongFluidStack;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class AbstractFluidItem extends Item {

    protected final long capacity;
    protected Supplier<Fluid> acceptedType;

    protected AbstractFluidItem(long capacity, Supplier<Fluid> acceptedType, Properties properties) {
        super(properties);
        this.capacity = capacity;
        this.acceptedType = acceptedType;
    }

    @Override
    public abstract @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt);

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).isPresent();
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return stack.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).map(handler -> {
            double scale = 1.0D  - Math.max(0.0D, (double) handler.getAmount() / (double) handler.getCapacity());
            return ((int)(255 * scale) << 16) + (((int)(255 * (1.0D - scale))) << 8);
        }).orElse(super.getBarColor(stack));
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return stack.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).map(storage -> (int) Math.round(13.0D - 1.0D / (double)storage.getAmount() * 13.0D / (double)storage.getCapacity())).orElse(super.getBarWidth(stack));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return -1;
    }

    protected class Wrapper implements ItemFluidStorage, ICapabilitySerializable<CompoundTag> {

        protected final ItemStack holder;
        protected Fluid acceptedFluid;
        protected final long capacity;
        protected long amount = 0;

        protected Wrapper(ItemStack holder, @NotNull Fluid acceptedFluid, long capacity) {
            this.holder = holder;
            this.acceptedFluid = acceptedFluid;
            this.capacity = capacity;
        }

        @Override
        public @NotNull Fluid getFluid() {
            return acceptedFluid;
        }

        @Override
        public void setFluid(@NotNull Fluid fluid) {
            this.acceptedFluid = fluid;
        }

        @Override
        public long getAmount() {
            return this.amount;
        }

        @Override
        public void setAmount(long amount) {
            this.amount = amount;
        }

        @Override
        public long getCapacity() {
            return this.capacity;
        }

        @Override
        public boolean isFluidValid(LongFluidStack stack) {
            return stack.getFluid().isSame(this.acceptedFluid);
        }

        @Override
        public long fill(LongFluidStack resource, IFluidHandler.FluidAction action) {
            if (!this.isFluidValid(resource)) return 0L;
            long toFill = Math.min(capacity - amount, resource.getAmount());
            if (action.execute()) this.amount += toFill;
            return toFill;
        }

        @Override
        public @NotNull LongFluidStack drain(long maxDrain, IFluidHandler.FluidAction action) {
            if (maxDrain <= 0) return LongFluidStack.EMPTY;
            long toDrain = Math.min(maxDrain, amount);
            if (action.execute()) this.amount -= toDrain;
            return FluidStorage.toStack(this, (int) toDrain);
        }

        @Override
        @SuppressWarnings("unchecked")
        public @NotNull LongFluidStack drain(LongFluidStack resource, IFluidHandler.FluidAction action) {
            if (!this.isFluidValid(resource)) return LongFluidStack.EMPTY;
            long toDrain = Math.min(resource.getAmount(), amount);
            if (action.execute()) this.amount -= toDrain;
            return FluidStorage.toStack(this, toDrain);
        }

        @Override
        public ItemStack getInstance() {
            return this.holder;
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return MachineFusionCapabilities.ITEM_FLUID_STORAGE.orEmpty(cap, LazyOptional.of(() -> this));
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            if (!this.acceptedFluid.isSame(Fluids.EMPTY)) tag.putString(References.Nbt.FLUID_TAG, ForgeRegistries.FLUIDS.getKey(this.acceptedFluid).toString());
            if (this.amount > 0) tag.putLong(References.Nbt.FLUID_AMOUNT_TAG, this.amount);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            if (nbt.contains(References.Nbt.FLUID_TAG)) this.acceptedFluid = ForgeRegistries.FLUIDS.getValue(ResourceLocation.of(nbt.getString(References.Nbt.FLUID_TAG), ':'));
            else this.acceptedFluid = Fluids.EMPTY;

            if (nbt.contains(References.Nbt.FLUID_AMOUNT_TAG)) this.amount = nbt.getLong(References.Nbt.FLUID_AMOUNT_TAG);
            else this.amount = 0L;
        }
    }
}