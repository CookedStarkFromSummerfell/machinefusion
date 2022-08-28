package dev.kalishak.machinefusion.world.block.machine.config;

import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.world.block.machine.RedstoneMode;
import net.minecraft.nbt.CompoundTag;

public class MachineConfig {

    public static final MachineConfig DEFAULT = new MachineConfig(RedstoneMode.IGNORE);

    protected RedstoneMode redstoneMode;

    public MachineConfig(RedstoneMode redstoneMode) {
        this.redstoneMode = redstoneMode;
    }

    public void setRedstoneMode(RedstoneMode redstoneMode) {
        this.redstoneMode = redstoneMode;
    }

    public CompoundTag saveAdditional(CompoundTag tag) {
        tag.putByte(References.Nbt.REDSTONE_MODE, (byte) this.redstoneMode.ordinal());
        return tag;
    }

    public void load(CompoundTag tag) {
        if (tag.contains(References.Nbt.REDSTONE_MODE)) this.redstoneMode = RedstoneMode.values()[tag.getByte(References.Nbt.REDSTONE_MODE)];
    }
}
