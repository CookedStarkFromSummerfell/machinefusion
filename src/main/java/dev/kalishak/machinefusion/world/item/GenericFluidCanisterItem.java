package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import dev.kalishak.machinefusion.world.fluid.LongFluidStack;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class GenericFluidCanisterItem extends AbstractFluidItem {
    private final Supplier<? extends Item> emptyCanister;
    protected final ChatFormatting fluidColorIn;

    protected GenericFluidCanisterItem(long capacity, Supplier<Fluid> content, Supplier<? extends Item> emptyCanister, @Nullable ChatFormatting fluidColorIn, Properties properties) {
        super(capacity, content, properties);
        this.emptyCanister = emptyCanister;
        if (fluidColorIn != null) this.fluidColorIn = fluidColorIn;
        else this.fluidColorIn = ChatFormatting.WHITE;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new Wrapper(stack, this.capacity);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (this.allowedIn(group) && !this.acceptedType.get().isSame( Fluids.EMPTY)) {
            ItemStack stack = new ItemStack(this);
            stack.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).ifPresent(
                    handler -> handler.fill(new LongFluidStack(this.acceptedType.get(), this.capacity), IFluidHandler.FluidAction.EXECUTE)
            );
            stacks.add(stack);
            ItemStack stack1 = new ItemStack(this);
            stack1.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).ifPresent(
                    handler -> handler.fill(new LongFluidStack(this.acceptedType.get(), 700), IFluidHandler.FluidAction.EXECUTE)
            );
            stacks.add(stack1);
            ItemStack stack2 = new ItemStack(this);
            stack2.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).ifPresent(
                    handler -> handler.fill(new LongFluidStack(this.acceptedType.get(), 400), IFluidHandler.FluidAction.EXECUTE)
            );
            stacks.add(stack2);
        } else super.fillItemCategory(group, stacks);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        stack.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).ifPresent(handler -> {
            if (!handler.getFluid().isSame(Fluids.EMPTY) || handler.getFluid() != Fluids.EMPTY) {
                components.add(Component.translatable("tooltip.machinefusion.fluid_remaining", Component.literal(handler.getAmount() + "/" + handler.getCapacity()).withStyle(this.fluidColorIn)));
            }
        });
        super.appendHoverText(stack, level, components, flag);
    }

    private final class Wrapper extends AbstractFluidItem.Wrapper {
        protected Wrapper(ItemStack holder, long capacity) {
            super(holder, acceptedType.get(), capacity);
        }

        @Override
        public ItemStack getInstance() {
            if (this.amount == 0) {
                return new ItemStack(emptyCanister.get());
            } return super.getInstance();
        }

        @Override
        public @NotNull LongFluidStack drain(LongFluidStack resource, IFluidHandler.FluidAction action) {
            LongFluidStack result = super.drain(resource, action);
            return result.getAmount() == 0 ? LongFluidStack.EMPTY : result;
        }

        @Override
        public @NotNull LongFluidStack drain(long maxDrain, IFluidHandler.FluidAction action) {
            LongFluidStack result = super.drain(maxDrain, action);
            return result.getAmount() == 0 ? LongFluidStack.EMPTY : result;
        }

        @Override
        public boolean isFluidValid(LongFluidStack stack) {
            return !stack.getFluid().isSame(Fluids.EMPTY);
        }
    }
}
