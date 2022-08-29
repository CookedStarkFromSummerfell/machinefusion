package dev.kalishak.machinefusion.api.capability.liquid;

import dev.kalishak.machinefusion.world.fluid.LongFluidStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

@AutoRegisterCapability
public interface FluidStorage {

    static LongFluidStack toStack(FluidStorage storage) {
        return new LongFluidStack(storage.getFluid(), Math.min(storage.getAmount(), FluidType.BUCKET_VOLUME));
    }

    static LongFluidStack toStack(FluidStorage storage, long amount) {
        return new LongFluidStack(storage.getFluid(), Math.min(amount, FluidType.BUCKET_VOLUME));
    }

    /**
     * @return Fluid in the tank
     */
    @NotNull
    Fluid getFluid();

    /**
     * Sets fluid in this container, shouldn't be null, if absent be sure to set
     * {@link net.minecraft.world.level.material.Fluids#EMPTY}
     * @param fluid
     */
    void setFluid(@NotNull Fluid fluid);

    /**
     * @return Current amount of fluid in the tank.
     */
    long getAmount();

    /**
     * Sets provided amount as this storage stored content
     * <b>TEST GAME ONLY</b>
     * @param amount to mark as stored
     */
    @TestOnly void setAmount(long amount);

    /**
     * @return Capacity of this fluid tank.
     */
    long getCapacity();

    /**
     * @param stack Fluidstack holding the Fluid to be queried.
     * @return If the tank can hold the fluid (EVER, not at the time of query).
     */
    boolean isFluidValid(LongFluidStack stack);

    /**
     * @param resource FluidStack attempting to fill the tank.
     * @param action   If SIMULATE, the fill will only be simulated.
     * @return Amount of fluid that was accepted (or would be, if simulated) by the tank.
     */
    long fill(LongFluidStack resource, IFluidHandler.FluidAction action);

    /**
     * @param maxDrain Maximum amount of fluid to be removed from the container.
     * @param action   If SIMULATE, the drain will only be simulated.
     * @return Amount of fluid that was removed (or would be, if simulated) from the tank.
     */
    @NotNull
    LongFluidStack drain(long maxDrain, IFluidHandler.FluidAction action);

    /**
     * @param resource Maximum amount of fluid to be removed from the container.
     * @param action   If SIMULATE, the drain will only be simulated.
     * @return FluidStack representing fluid that was removed (or would be, if simulated) from the tank.
     */
    @NotNull
    LongFluidStack drain(LongFluidStack resource, IFluidHandler.FluidAction action);
}