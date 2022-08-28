package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.api.capability.EnergyStorage;
import dev.kalishak.machinefusion.api.capability.ItemEnergyStorage;
import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import dev.kalishak.machinefusion.util.ClientUtil;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractEnergyItem extends Item {

    public AbstractEnergyItem(Properties properties) {
        super(properties);
    }

    @Override
    public abstract @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt);

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).isPresent();
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return stack.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).map(handler -> {
            double scale = 1.0D  - Math.max(0.0D, (double) handler.getStored() / (double) handler.getCapacity());
            return ((int)(255 * scale) << 16) + (((int)(255 * (1.0D - scale))) << 8);
        }).orElse(super.getBarColor(stack));
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return stack.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).map(storage -> (int) Math.round(13.0D - 1.0D / (double)storage.getStored() * 13.0D / (double)storage.getCapacity())).orElse(super.getBarWidth(stack));
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return -1;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack stack1) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> lines, TooltipFlag context) {
        stack.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).ifPresent(handler -> lines.add(Component.translatable("tooltip.machinefusion.energy_remaining", ClientUtil.getEnergyDisplay(handler.getStored())).setStyle(References.Color.getStorageLevelColor(1.0D - ((double) handler.getStored()) / ((double) handler.getCapacity())))));
        super.appendHoverText(stack, world, lines, context);
    }

    protected class Wrapper implements ItemEnergyStorage, ICapabilitySerializable<CompoundTag> {

        protected final ItemStack holder;
        protected final long capacity;
        protected long stored = 0L;
        protected long maxTransfer;

        private final LazyOptional<ItemEnergyStorage> lazyHandler;

        protected Wrapper(ItemStack holder, long capacity, long maxTransfer) {
            this.holder = holder;
            this.capacity = capacity;
            this.maxTransfer = maxTransfer;

            this.lazyHandler = LazyOptional.of(() -> this);
        }

        @Override
        public long receiveEnergy(long maxReceive, boolean simulate) {
            if (!shouldReceive()) return 0L;
            long received = Math.min(capacity - stored, Math.min(maxReceive, maxTransfer));
            if (!simulate) this.stored += received;
            return received;
        }

        @Override
        public long receiveEnergy(EnergyStorage storage, long maxReceive, boolean simulate) {
            if (!shouldReceive()) return 0L;
            long received = Math.min(capacity - stored, storage.extractEnergy(this, Math.min(maxReceive, maxTransfer), simulate));
            if (!simulate) this.stored += received;
            return received;
        }

        @Override
        public long extractEnergy(long maxExtract, boolean simulate) {
            if (!shouldExtract()) return 0L;
            long extracted = Math.min(stored, Math.min(maxExtract, maxTransfer));
            if (!simulate) this.stored -= extracted;
            return extracted;
        }

        @Override
        public long extractEnergy(EnergyStorage storage, long maxExtract, boolean simulate) {
            if (!shouldExtract()) return 0L;
            long extracted = Math.min(stored, storage.receiveEnergy(this, Math.min(maxExtract, maxTransfer), simulate));
            if (!simulate) this.stored -= extracted;
            return extracted;
        }

        @Override
        public long getStored() {
            return this.stored;
        }

        @Override
        public void setStored(long amount) {
            this.stored = amount;
        }

        @Override
        public long getCapacity() {
            return this.capacity;
        }

        @Override
        public boolean shouldExtract() {
            return this.stored > 0;
        }

        @Override
        public boolean shouldReceive() {
            return this.stored == this.capacity;
        }

        @Override
        public ItemStack getInstance() {
            return this.holder;
        }

        @Override
        public long maxTransfer() {
            return this.maxTransfer;
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return MachineFusionCapabilities.ITEM_ENERGY_STORAGE.orEmpty(cap, this.lazyHandler.cast());
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            if (this.getStored() > 0L) tag.putLong(References.Nbt.ENERGY_TAG, this.getStored());
            if (this.maxTransfer() > 0L) tag.putLong(References.Nbt.MAX_TRANSFER, this.maxTransfer());
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {
            if (tag.contains(References.Nbt.ENERGY_TAG)) this.stored = tag.getLong(References.Nbt.ENERGY_TAG);
            if (tag.contains(References.Nbt.MAX_TRANSFER)) this.maxTransfer = tag.getLong(References.Nbt.MAX_TRANSFER);
        }
    }
}
