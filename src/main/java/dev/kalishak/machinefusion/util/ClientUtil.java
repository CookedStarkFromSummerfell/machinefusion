package dev.kalishak.machinefusion.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class ClientUtil {

    public static MutableComponent getEnergyDisplay(long amount) {
        return Component.literal(String.valueOf(amount));
    }
}
