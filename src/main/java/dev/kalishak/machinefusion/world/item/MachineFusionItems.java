package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.world.block.MachineFusionBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MachineFusionItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.Keys.ITEMS, References.MOD_ID);

    /** BLOCKS */
    public static final RegistryObject<Item> TIN_ORE = ITEMS.register(References.Block.TIN_ORE, () -> new BlockItem(MachineFusionBlocks.TIN_ORE.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = ITEMS.register(References.Block.DEEPSLATE_TIN_ORE, () -> new BlockItem(MachineFusionBlocks.DEEPSLATE_TIN_ORE.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register(References.Block.TIN_BLOCK, () -> new BlockItem(MachineFusionBlocks.TIN_BLOCK.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> ALUMINUM_ORE = ITEMS.register(References.Block.ALUMINUM_ORE, () -> new BlockItem(MachineFusionBlocks.ALUMINUM_ORE.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> DEEPSLATE_ALUMINUM_ORE = ITEMS.register(References.Block.DEEPSLATE_ALUMINUM_ORE, () -> new BlockItem(MachineFusionBlocks.DEEPSLATE_ALUMINUM_ORE.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> ALUMINUM_BLOCK = ITEMS.register(References.Block.ALUMINUM_BLOCK, () -> new BlockItem(MachineFusionBlocks.ALUMINUM_BLOCK.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> LEAD_ORE = ITEMS.register(References.Block.LEAD_ORE, () -> new BlockItem(MachineFusionBlocks.LEAD_ORE.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> DEEPSLATE_LEAD_ORE = ITEMS.register(References.Block.DEEPSLATE_LEAD_ORE, () -> new BlockItem(MachineFusionBlocks.DEEPSLATE_LEAD_ORE.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));
    public static final RegistryObject<Item> LEAD_BLOCK = ITEMS.register(References.Block.LEAD_BLOCK, () -> new BlockItem(MachineFusionBlocks.LEAD_BLOCK.get(), new Item.Properties().tab(MachineFusionCreativeTab.BLOCKS_GROUP)));

    /** ITEMS */
    public static final RegistryObject<Item> BATTERY_ITEM = ITEMS.register(References.Item.BATTERY, () -> new SimpleBatteryItem(2500L, 150L, new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> INFINITE_BATTERY_ITEM = ITEMS.register(References.Item.INFINITE_BATTERY, () -> new InfiniteEnergyItem(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> EMPTY_CANISTER = ITEMS.register(References.Item.EMPTY_CANISTER, () -> new GenericFluidCanisterItem(FluidType.BUCKET_VOLUME, () -> Fluids.EMPTY, MachineFusionItems.EMPTY_CANISTER, null, new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> WATER_CANISTER = ITEMS.register(References.Item.WATER_CANISTER, () -> new GenericFluidCanisterItem(FluidType.BUCKET_VOLUME, () -> Fluids.WATER, MachineFusionItems.EMPTY_CANISTER, ChatFormatting.BLUE, new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register(References.Item.TIN_INGOT, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register(References.Item.TIN_NUGGET, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register(References.Item.TIN_PLATE, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register(References.Item.ALUMINUM_INGOT, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register(References.Item.ALUMINUM_NUGGET, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> ALUMINUM_PLATE = ITEMS.register(References.Item.ALUMINUM_PLATE, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register(References.Item.LEAD_INGOT, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register(References.Item.LEAD_NUGGET, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> LEAD_PLATE = ITEMS.register(References.Item.LEAD_PLATE, () -> new Item(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static void register(final IEventBus bus) {
        ITEMS.register(bus);
    }
}
