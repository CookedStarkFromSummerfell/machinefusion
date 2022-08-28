package dev.kalishak.machinefusion.world.fluid;

import dev.kalishak.machinefusion.MachineFusion;
import dev.kalishak.machinefusion.util.GenericHelper;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class LongFluidStack {

    public static final LongFluidStack EMPTY = new LongFluidStack(Fluids.EMPTY, 0L);

    private boolean isEmpty;
    private long amount;
    private long capacity = FluidType.BUCKET_VOLUME;
    private CompoundTag tag;
    private Holder.Reference<Fluid> delegate;

    public LongFluidStack(Fluid fluid, long amount) {
        if (fluid == null) {
            throw new IllegalArgumentException("Cannot create a LongFluidStack from a null fluid");
        } else if (ForgeRegistries.FLUIDS.getKey(fluid) == null) {
            throw new IllegalArgumentException("Cannot create a LongFluidStack from an unregistered fluid");
        }
        this.delegate = ForgeRegistries.FLUIDS.getDelegateOrThrow(fluid);
        this.amount = amount;

        updateEmpty();
    }

    public LongFluidStack(Fluid fluid, long amount, CompoundTag nbt) {
        this(fluid, amount);

        if (nbt != null) {
            tag = nbt.copy();
        }
    }

    public LongFluidStack copyOf(LongFluidStack original, long amount) {
        return new LongFluidStack(original.getFluid(), amount, original.tag);
    }

    public LongFluidStack copyOf(FluidStack original) {
        return new LongFluidStack(original.getFluid(), amount, original.getTag());
    }

    public static LongFluidStack loadFluidStackFromNBT(CompoundTag nbt)
    {
        if (nbt == null) {
            return EMPTY;
        }
        if (!nbt.contains("FluidName", Tag.TAG_STRING)) {
            return EMPTY;
        }

        ResourceLocation fluidName = new ResourceLocation(nbt.getString("FluidName"));
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
        if (fluid == null) {
            return EMPTY;
        }
        LongFluidStack stack = new LongFluidStack(fluid, nbt.getLong("Amount"));

        if (nbt.contains("Tag", Tag.TAG_COMPOUND)) {
            stack.tag = nbt.getCompound("Tag");
        } return stack;
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        nbt.putString("FluidName", ForgeRegistries.FLUIDS.getKey(getFluid()).toString());
        nbt.putLong("Amount", amount);

        if (tag != null) {
            nbt.put("Tag", tag);
        } return nbt;
    }

    public void writeToPacket(FriendlyByteBuf buf)
    {
        buf.writeRegistryId(ForgeRegistries.FLUIDS, getFluid());
        buf.writeLong(getAmount());
        buf.writeNbt(tag);
    }

    public static LongFluidStack readFromPacket(FriendlyByteBuf buf)
    {
        Fluid fluid = buf.readRegistryId();
        int amount = buf.readVarInt();
        CompoundTag tag = buf.readNbt();
        if (fluid == Fluids.EMPTY) return EMPTY;
        return new LongFluidStack(fluid, amount, tag);
    }

    public final Fluid getFluid()
    {
        return isEmpty ? Fluids.EMPTY : delegate.get();
    }

    public final Fluid getRawFluid()
    {
        return delegate.get();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    protected void updateEmpty() {
        isEmpty = getRawFluid() == Fluids.EMPTY || amount <= 0L;
    }

    public long getAmount()
    {
        return isEmpty ? 0L : amount;
    }

    public void setAmount(long amount) {
        if (getRawFluid() == Fluids.EMPTY) throw new IllegalStateException("Can't modify the empty stack.");
        this.amount = amount;
        updateEmpty();
    }

    public void grow(int amount) {
        setAmount(this.amount + amount);
    }

    public void shrink(int amount) {
        setAmount(this.amount - amount);
    }

    public boolean hasTag() {
        return tag != null;
    }

    public CompoundTag getTag() {
        return tag;
    }

    public void setTag(CompoundTag tag) {
        if (getRawFluid() == Fluids.EMPTY) throw new IllegalStateException("Can't modify the empty stack.");
        this.tag = tag;
    }

    public CompoundTag getOrCreateTag() {
        if (tag == null)
            setTag(new CompoundTag());
        return tag;
    }

    public CompoundTag getChildTag(String childName) {
        if (tag == null)
            return null;
        return tag.getCompound(childName);
    }

    public CompoundTag getOrCreateChildTag(String childName) {
        getOrCreateTag();
        CompoundTag child = tag.getCompound(childName);
        if (!tag.contains(childName, Tag.TAG_COMPOUND))
        {
            tag.put(childName, child);
        }
        return child;
    }

    public void removeChildTag(String childName) {
        if (tag != null)
            tag.remove(childName);
    }

    public boolean isFluidEqual(@NotNull LongFluidStack other) {
        return getFluid() == other.getFluid() && isFluidStackTagEqual(other);
    }

    private boolean isFluidStackTagEqual(LongFluidStack other) {
        return tag == null ? other.tag == null : other.tag != null && tag.equals(other.tag);
    }

    /**
     * Determines if the NBT Tags are equal. Useful if the FluidIDs are known to be equal.
     */
    public static boolean areFluidStackTagsEqual(@NotNull LongFluidStack stack1, @NotNull LongFluidStack stack2) {
        return stack1.isFluidStackTagEqual(stack2);
    }

    /**
     * Determines if the Fluids are equal and this stack is larger.
     *
     * @return true if this FluidStack contains the other FluidStack (same fluid and >= amount)
     */
    public boolean containsFluid(@NotNull LongFluidStack other) {
        return isFluidEqual(other) && amount >= other.amount;
    }

    /**
     * Determines if the FluidIDs, Amounts, and NBT Tags are all equal.
     *
     * @param other
     *            - the FluidStack for comparison
     * @return true if the two FluidStacks are exactly the same
     */
    public boolean isFluidStackIdentical(LongFluidStack other) {
        return isFluidEqual(other) && amount == other.amount;
    }

    /**
     * Determines if the FluidIDs and NBT Tags are equal compared to a registered container
     * ItemStack. This does not check amounts.
     *
     * @param other
     *            The ItemStack for comparison
     * @return true if the Fluids (IDs and NBT Tags) are the same
     */
    public boolean isFluidEqual(@NotNull ItemStack other) {
        return GenericHelper.getFluidContained(other).map(this::isFluidEqual).orElse(false);
    }

    @Override
    public final int hashCode() {
        long code = 1;
        code = 31*code + getFluid().hashCode();
        code = 31*code + amount;
        if (tag != null)
            code = 31*code + tag.hashCode();
        return (int)code;
    }

    /**
     * Default equality comparison for a FluidStack. Same functionality as isFluidEqual().
     *
     * This is included for use in data structures.
     */
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof FluidStack)) {
            return false;
        }
        return isFluidEqual((LongFluidStack) o);
    }
}
