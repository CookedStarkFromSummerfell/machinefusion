package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class SimpleBatteryItem extends AbstractEnergyItem {

    private final long capacity;
    private final long maxTransfer;

    public SimpleBatteryItem(long capacity, long maxTransfer, Properties properties) {
        super(properties);
        this.capacity = capacity;
        this.maxTransfer = maxTransfer;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new Wrapper(stack, capacity, maxTransfer);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (this.allowedIn(group)) {
            ItemStack depleted = new ItemStack(this);
            depleted.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).ifPresent(handler -> handler.setStored(0L));
            stacks.add(depleted);

            ItemStack charged = new ItemStack(this);
            charged.getCapability(MachineFusionCapabilities.ITEM_ENERGY_STORAGE).ifPresent(handler -> handler.setStored(handler.getCapacity()));
            stacks.add(charged);
        }
    }
}
