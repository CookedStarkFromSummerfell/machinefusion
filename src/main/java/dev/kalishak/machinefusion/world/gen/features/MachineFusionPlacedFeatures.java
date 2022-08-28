package dev.kalishak.machinefusion.world.gen.features;

import dev.kalishak.machinefusion.References;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class MachineFusionPlacedFeatures {
    private static final DeferredRegister<PlacedFeature> FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, References.MOD_ID);

    public static final RegistryObject<PlacedFeature> ORE_TIN = FEATURES.register("ore_tin", () -> new PlacedFeature(
            MachineFusionConfiguredFeatures.ORE_TIN.getHolder().get(),
            countSettings(99, HeightRangePlacement.triangle(VerticalAnchor.absolute(90), VerticalAnchor.absolute(384))))
    );
    public static final RegistryObject<PlacedFeature> ORE_TIN_SMALL = FEATURES.register("ore_tin_small", () -> new PlacedFeature(
            MachineFusionConfiguredFeatures.ORE_TIN_SMALL.getHolder().get(),
            countSettings(11, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(80))))
    );

    public static final RegistryObject<PlacedFeature> ORE_ALUMINUM = FEATURES.register("ore_aluminum", () -> new PlacedFeature(
            MachineFusionConfiguredFeatures.ORE_ALUMINUM.getHolder().get(),
            countSettings(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-96), VerticalAnchor.absolute(0))))
    );
    public static final RegistryObject<PlacedFeature> ORE_ALUMINUM_SMALL = FEATURES.register("ore_aluminum_small", () -> new PlacedFeature(
            MachineFusionConfiguredFeatures.ORE_ALUMINUM_SMALL.getHolder().get(),
            countSettings(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))))
    );

    public static final RegistryObject<PlacedFeature> ORE_LEAD = FEATURES.register("ore_lead", () -> new PlacedFeature(
            MachineFusionConfiguredFeatures.ORE_LEAD.getHolder().get(),
            countSettings(5, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-53), VerticalAnchor.aboveBottom(82))))
    );
    public static final RegistryObject<PlacedFeature> ORE_LEAD_SMALL = FEATURES.register("ore_lead_small", () -> new PlacedFeature(
            MachineFusionConfiguredFeatures.ORE_LEAD_SMALL.getHolder().get(),
            rareSettings(11, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-82), VerticalAnchor.aboveBottom(53))))
    );

    public static void register(final IEventBus bus) {
        FEATURES.register(bus);
    }

    private static List<PlacementModifier> settings(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> countSettings(int count, PlacementModifier heightModfier) {
        return settings(CountPlacement.of(count), heightModfier);
    }

    private static List<PlacementModifier> rareSettings(int chance, PlacementModifier heightModifier) {
        return settings(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }
}