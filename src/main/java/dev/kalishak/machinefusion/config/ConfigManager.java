package dev.kalishak.machinefusion.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigManager {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;
    public static class Common {

        public static ForgeConfigSpec.BooleanValue enableBatteryLifespan;
        public static ForgeConfigSpec.BooleanValue enableTinGeneration;
        public static ForgeConfigSpec.IntValue tinGenerationWeight;
        public static ForgeConfigSpec.BooleanValue enableAluminumGeneration;
        public static ForgeConfigSpec.IntValue aluminumGenerationWeight;
        public static ForgeConfigSpec.BooleanValue enableLeadGeneration;
        public static ForgeConfigSpec.IntValue leadGenerationWeight;

        public Common(ForgeConfigSpec.Builder builder) {
            enableBatteryLifespan = builder.translation("config.machinefusion.enableBatteryLifespan").define("enableBatteryLifespan", false);

            builder.push("worldgen");
            enableTinGeneration = builder.translation("config.machinefusion.enableTinGeneration").define("enableTinGeneration", true);
            tinGenerationWeight = builder.translation("config.machinefusion.tinGenerationWeight").defineInRange("tinGenerationWeight", 3, 1, 7);

            enableAluminumGeneration = builder.translation("config.machinefusion.enableAluminumGeneration").define("enableAluminumGeneration", true);
            aluminumGenerationWeight = builder.translation("config.machinefusion.aluminumGenerationWeight").defineInRange("aluminumGenerationWeight", 3, 1, 7);

            enableLeadGeneration = builder.translation("config.machinefusion.enableLeadGeneration").define("enableLeadGeneration", true);
            leadGenerationWeight = builder.translation("config.machinefusion.leadGenerationWeight").defineInRange("leadGenerationWeight", 2, 1, 6);
            builder.pop();
        }
    }

    public static class Client {

        public static ForgeConfigSpec.BooleanValue disable3dModels;
        public static ForgeConfigSpec.BooleanValue disableAlphaWarning;

        public Client(ForgeConfigSpec.Builder builder) {
            disable3dModels = builder.translation("config.machinefusion.disable3dModels").define("disable3dModels", false);
            disableAlphaWarning = builder.translation("config.machinefusion.disableAlphaWarning").define("disableAlphaWarning", false);
        }
    }

    static {
        final Pair<Common, ForgeConfigSpec> COMMON_PAIR = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = COMMON_PAIR.getRight();
        COMMON = COMMON_PAIR.getLeft();

        final Pair<Client, ForgeConfigSpec> CLIENT_PAIR = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = CLIENT_PAIR.getRight();
        CLIENT = CLIENT_PAIR.getLeft();
    }
}
