package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import dev.kalishak.machinefusion.api.capability.temperature.TemperatureSensitive;
import dev.kalishak.machinefusion.api.capability.temperature.TemperatureType;
import dev.kalishak.machinefusion.config.ConfigManager;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
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

public class SimpleThermalItem extends Item {

    public static final int MAX_TEMPERATURE = 726;

    public SimpleThermalItem(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (allowedIn(p_41391_)) {
            ItemStack cold = new ItemStack(this);
            cold.getCapability(MachineFusionCapabilities.TEMPERATURE_SENSITIVE).ifPresent(handler -> handler.setTemperature(239));
            p_41392_.add(cold);
            ItemStack hot = new ItemStack(this);
            hot.getCapability(MachineFusionCapabilities.TEMPERATURE_SENSITIVE).ifPresent(handler -> handler.setTemperature(340));
            p_41392_.add(hot);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag p_41424_) {
        stack.getCapability(MachineFusionCapabilities.TEMPERATURE_SENSITIVE).ifPresent(
                handler -> tooltip.add(TemperatureType.tooltipFromTemperature(ConfigManager.Client.preferredTemperature.get(), handler.getCurrentTemperature()))
        );
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new Wrapper(TemperatureSensitive.DEFAULT_TEMPERATURE);
    }

    protected class Wrapper implements TemperatureSensitive, ICapabilitySerializable<IntTag> {

        protected int currentTemperature;

        protected Wrapper(int initialTemperature) {
            this.currentTemperature = initialTemperature;
        }

        @Override
        public int getCurrentTemperature() {
            return this.currentTemperature;
        }

        @Override
        public void setTemperature(int temperature) {
            this.currentTemperature = temperature;
        }

        @Override
        public void changeTemperature(float scale, float ticks, int temperature) {
            //TODO
        }

        @Override
        public boolean isVisible() {
            return this.currentTemperature == DEFAULT_TEMPERATURE;
        }

        @Override
        public void onMaxTemperature(ItemStack stack, float ticks, LivingEntity entity, boolean simulate) {
            if (!stack.isEmpty() && entity != null) {
                if (entity.getMainHandItem().equals(stack, false)) {
                    entity.playSound(SoundEvents.ITEM_BREAK);

                    if (!simulate) {
                        entity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    }
                }
            }
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return MachineFusionCapabilities.TEMPERATURE_SENSITIVE.orEmpty(cap, LazyOptional.of(() -> this));
        }

        @Override
        public IntTag serializeNBT() {
            return IntTag.valueOf(this.currentTemperature);
        }

        @Override
        public void deserializeNBT(IntTag nbt) {
            this.currentTemperature = nbt.getAsInt();
        }
    }
}
