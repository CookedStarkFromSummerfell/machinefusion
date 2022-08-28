package dev.kalishak.machinefusion;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface References {

    String MOD_ID = "machinefusion";

    interface Item {
        String BATTERY = "battery";
        String INFINITE_BATTERY = "infinite_battery";
        String EMPTY_CANISTER = "empty_canister";
        String WATER_CANISTER = "water_canister";

        String TIN_INGOT = "tin_ingot";
        String TIN_NUGGET = "tin_nugget";
        String TIN_PLATE = "tin_plate";

        String ALUMINUM_INGOT = "aluminum_ingot";
        String ALUMINUM_NUGGET = "aluminum_nugget";
        String ALUMINUM_PLATE = "aluminum_plate";

        String LEAD_INGOT = "lead_ingot";
        String LEAD_NUGGET = "lead_nugget";
        String LEAD_PLATE = "lead_plate";
    }

    interface Block {
        String TIN_ORE = "tin_ore";
        String DEEPSLATE_TIN_ORE = "deepslate_tin_ore";
        String TIN_BLOCK = "tin_block";

        String ALUMINUM_ORE = "aluminum_ore";
        String DEEPSLATE_ALUMINUM_ORE = "deepslate_aluminum_ore";
        String ALUMINUM_BLOCK = "aluminum_block";

        String LEAD_ORE = "lead_ore";
        String DEEPSLATE_LEAD_ORE = "deepslate_lead_ore";
        String LEAD_BLOCK = "lead_block";
    }

    interface Nbt {
        String ENERGY_TAG = "EnergyStored";
        String FLUID_TAG = "FluidStored";
        String FLUID_AMOUNT_TAG = "FluidAmount";
        String MAX_TRANSFER = "MaxTransfer";
        String REDSTONE_MODE = "RedstoneMode";
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
