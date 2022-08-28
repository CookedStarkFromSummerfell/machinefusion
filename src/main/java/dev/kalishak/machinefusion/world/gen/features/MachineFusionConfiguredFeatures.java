package dev.kalishak.machinefusion.world.gen.features;

import com.google.common.base.Suppliers;
import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.config.ConfigManager;
import dev.kalishak.machinefusion.world.block.MachineFusionBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class MachineFusionConfiguredFeatures {
    private static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, References.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> TIN_ORES = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MachineFusionBlocks.TIN_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MachineFusionBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ALUMINUM_ORES = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MachineFusionBlocks.ALUMINUM_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MachineFusionBlocks.DEEPSLATE_ALUMINUM_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEAD_ORES = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MachineFusionBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MachineFusionBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_TIN = CONFIGURED_FEATURES.register("ore_tin", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TIN_ORES.get(), 7)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_TIN_SMALL = CONFIGURED_FEATURES.register("ore_tin_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TIN_ORES.get(), 3)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_ALUMINUM = CONFIGURED_FEATURES.register("ore_aluminum", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_ORES.get(), 7)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_ALUMINUM_SMALL = CONFIGURED_FEATURES.register("ore_aluminum_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_ORES.get(), 3)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_LEAD_SMALL = CONFIGURED_FEATURES.register("ore_lead_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEAD_ORES.get(), 6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_LEAD = CONFIGURED_FEATURES.register("ore_lead", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEAD_ORES.get(), 2)));

    public static void register(final IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
    }
}
