package dev.kalishak.machinefusion.world.item;

import dev.kalishak.machinefusion.References;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MFItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.Keys.ITEMS, References.MOD_ID);

    public static final RegistryObject<Item> BATTERY_ITEM = ITEMS.register("battery", () -> new SimpleBatteryItem(2500L, 150L, new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> INFINITE_BATTERY_ITEM = ITEMS.register("infinite_battery", () -> new InfiniteEnergyItem(new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));

    public static final RegistryObject<Item> EMPTY_CANISTER = ITEMS.register("empty_canister", () -> new GenericFluidCanisterItem(FluidType.BUCKET_VOLUME, () -> Fluids.EMPTY, MFItems.EMPTY_CANISTER, null, new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));
    public static final RegistryObject<Item> WATER_CANISTER = ITEMS.register("water_canister", () -> new GenericFluidCanisterItem(FluidType.BUCKET_VOLUME, () -> Fluids.WATER, MFItems.EMPTY_CANISTER, ChatFormatting.BLUE, new Item.Properties().tab(MachineFusionCreativeTab.ITEMS_GROUP)));


    public static void register(final IEventBus bus) {
        ITEMS.register(bus);
    }
}
