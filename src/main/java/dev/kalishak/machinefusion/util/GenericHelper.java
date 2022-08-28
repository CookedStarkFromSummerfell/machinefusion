package dev.kalishak.machinefusion.util;

import dev.kalishak.machinefusion.api.capability.ItemFluidStorage;
import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import dev.kalishak.machinefusion.world.fluid.LongFluidStack;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface GenericHelper {

    static LazyOptional<ItemFluidStorage> getFluidStackHandler(@NotNull ItemStack container) {
        return container.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE);
    }

    /**
     * Helper method to get the fluid contained in an itemStack
     */
    static Optional<LongFluidStack> getFluidContained(@NotNull ItemStack container) {
        if (!container.isEmpty()) {
            container = ItemHandlerHelper.copyStackWithSize(container, 1);
            Optional<LongFluidStack> fluidContained = getFluidStackHandler(container)
                    .map(handler -> handler.drain(Long.MAX_VALUE, IFluidHandler.FluidAction.SIMULATE));
            if (fluidContained.isPresent() && !fluidContained.get().isEmpty()) {
                return fluidContained;
            }
        } return Optional.empty();
    }
}
