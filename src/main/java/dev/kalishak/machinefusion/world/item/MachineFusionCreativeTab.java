package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.world.block.MachineFusionBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class MachineFusionCreativeTab {

    public static final CreativeModeTab ITEMS_GROUP = new CreativeModeTab("machinefusion.items") {
        @Override
        public ItemStack makeIcon() {
            return MachineFusionItems.BATTERY_ITEM.get().getDefaultInstance();
        }
    };

    public static final CreativeModeTab BLOCKS_GROUP = new CreativeModeTab("machinefusion.blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MachineFusionBlocks.DEEPSLATE_LEAD_ORE.get());
        }
    };

    public static final CreativeModeTab MACHINES_GROUP = new CreativeModeTab("machinefusion.machines") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.APPLE);
        }
    };
}
