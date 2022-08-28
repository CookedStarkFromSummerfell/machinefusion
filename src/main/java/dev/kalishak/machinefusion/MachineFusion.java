package dev.kalishak.machinefusion;

import dev.kalishak.machinefusion.client.MachineFusionClient;
import dev.kalishak.machinefusion.world.item.MFItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(References.MOD_ID)
public class MachineFusion {

    public static final Logger LOGGER = LogManager.getLogger();

    public MachineFusion() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MachineFusionClient::new);
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MFItems.register(bus);
    }
}
