package dev.kalishak.machinefusion.client;

import dev.kalishak.machinefusion.References;
import dev.kalishak.machinefusion.api.capability.MachineFusionCapabilities;
import dev.kalishak.machinefusion.world.item.MachineFusionItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = References.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MachineFusionClient {

    private static final ResourceLocation PARTIAL_ID = new ResourceLocation(References.MOD_ID, "partial");

    public MachineFusionClient(final IEventBus bus) {
        bus.addListener(this::setupClient);
    }

    @SubscribeEvent
    public void setupClient(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(MachineFusionItems.WATER_CANISTER.get(), PARTIAL_ID,
                    (stack, level, entity, id) -> stack.getCapability(MachineFusionCapabilities.ITEM_FLUID_STORAGE).map(
                            handler -> (float) handler.getAmount() / (float) handler.getCapacity()).orElse(0.0F));
        });
    }
}
