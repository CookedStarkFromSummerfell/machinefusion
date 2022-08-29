package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.api.capability.energy.EnergyStorage;
import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfiniteEnergyItem extends AbstractEnergyItem {

    private int ticks = (int)(Math.random() * 1000.0F);

    public InfiniteEnergyItem(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new Wrapper(stack, 1250L);
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        stack.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).ifPresent(handler -> {
            tooltip.add(Component.translatable("tooltip.machinefusion.energy_remaining", Component.translatable("tooltip.machinefusion.infinite").setStyle(References.Color.getRainbow(ticks))));
            tooltip.add(Component.translatable("tooltip.machinefusion.creative_only").setStyle(References.Color.LIGHT_PURPLE_STYLE));
        });
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return 13;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (++ticks > 1000) ticks -= 1000;
        return Mth.hsvToRgb(this.ticks / 1000.0f, 1, 1);
    }

    private final class Wrapper extends AbstractEnergyItem.Wrapper {

        protected Wrapper(ItemStack holder, long maxTransfer) {
            super(holder, Long.MAX_VALUE, maxTransfer);
        }

        @Override
        public boolean shouldReceive() {
            return false;
        }

        @Override
        public long receiveEnergy(long maxReceive, boolean simulate) {
            return 0L;
        }

        @Override
        public long receiveEnergy(EnergyStorage storage, long maxReceive, boolean simulate) {
            return 0L;
        }

        @Override
        public void setStored(long amount) { }

        @Override
        public boolean shouldExtract() {
            return true;
        }

        @Override
        public long extractEnergy(long maxExtract, boolean simulate) {
            return Math.min(maxExtract, this.maxTransfer);
        }

        @Override
        public long extractEnergy(EnergyStorage storage, long maxExtract, boolean simulate) {
            return storage.receiveEnergy(this, Math.min(maxExtract, maxTransfer), simulate);
        }
    }
}
