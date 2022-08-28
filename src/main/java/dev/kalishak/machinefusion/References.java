package dev.kalishak.machinefusion;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface References {

    String MOD_ID = "machinefusion";

    interface Nbt {
        String ENERGY_TAG = "EnergyStored";
        String FLUID_TAG = "FluidStored";
        String FLUID_AMOUNT_TAG = "FluidAmount";
        String MAX_TRANSFER = "MaxTransfer";
    }

    @OnlyIn(Dist.CLIENT)
    interface Color {
        Style DARK_GRAY_STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_GRAY);
        Style GOLD_STYLE = Style.EMPTY.withColor(ChatFormatting.GOLD);
        Style GREEN_STYLE = Style.EMPTY.withColor(ChatFormatting.GREEN);
        Style RED_STYLE = Style.EMPTY.withColor(ChatFormatting.RED);
        Style BLUE_STYLE = Style.EMPTY.withColor(ChatFormatting.BLUE);
        Style AQUA_STYLE = Style.EMPTY.withColor(ChatFormatting.AQUA);
        Style GRAY_STYLE = Style.EMPTY.withColor(ChatFormatting.GRAY);
        Style DARK_RED_STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_RED);
        Style LIGHT_PURPLE_STYLE = Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE);
        Style YELLOW_STYLE = Style.EMPTY.withColor(ChatFormatting.YELLOW);
        Style WHITE_STYLE = Style.EMPTY.withColor(ChatFormatting.WHITE);
        Style DARK_BLUE_STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_BLUE);

        static Style getStorageLevelColor(double scale) {
            return Style.EMPTY.withColor(TextColor.fromRgb(((int) (255 * scale) << 16) + (((int) (255 * (1.0 - scale))) << 8)));
        }

        static Style getRainbow(float ticks) {
            return Style.EMPTY.withColor(TextColor.fromRgb(Mth.hsvToRgb(ticks / 1000.0f, 1, 1)));
        }
    }
}
