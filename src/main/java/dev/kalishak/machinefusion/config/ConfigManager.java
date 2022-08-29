package dev.kalishak.machinefusion.config;

import dev.kalishak.machinefusion.api.capability.temperature.TemperatureType;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigManager {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;
    public static class Common {

        public static ForgeConfigSpec.BooleanValue enableBatteryLifespan;
        public static ForgeConfigSpec.IntValue tinGenerationWeight;
        public static ForgeConfigSpec.IntValue smallTinGenerationWeight;
        public static ForgeConfigSpec.IntValue aluminumGenerationWeight;
        public static ForgeConfigSpec.IntValue smallAluminumGenerationWeight;
        public static ForgeConfigSpec.IntValue leadGenerationWeight;
        public static ForgeConfigSpec.IntValue smallLeadGenerationWeight;


        public Common(ForgeConfigSpec.Builder builder) {
            enableBatteryLifespan = builder.translation("config.machinefusion.enableBatteryLifespan").define("enableBatteryLifespan", false);

            builder.push("worldgen");
            tinGenerationWeight = builder.translation("config.machinefusion.tinGenerationWeight").defineInRange("tinGenerationWeight", 7, 7, 13);
            smallTinGenerationWeight = builder.translation("config.machinefusion.smallTinGenerationWeight").defineInRange("smallTinGenerationWeight", 3, 1, 7);
            aluminumGenerationWeight = builder.translation("config.machinefusion.aluminumGenerationWeight").defineInRange("aluminumGenerationWeight", 7, 7, 13);
            smallAluminumGenerationWeight = builder.translation("config.machinefusion.smallAluminumGenerationWeight").defineInRange("smallAluminumGenerationWeight", 3, 1, 7);
            leadGenerationWeight = builder.translation("config.machinefusion.leadGenerationWeight").defineInRange("leadGenerationWeight", 6, 6, 12);
            smallLeadGenerationWeight = builder.translation("config.machinefusion.smallLeadGenerationWeight").defineInRange("smallLeadGenerationWeight", 2, 1, 6);
            builder.pop();
        }
    }

    public static class Client {

        public static ForgeConfigSpec.BooleanValue disable3dModels;
        public static ForgeConfigSpec.BooleanValue disableAlphaWarning;
        public static ForgeConfigSpec.EnumValue<TemperatureType> preferredTemperature;

        public Client(ForgeConfigSpec.Builder builder) {
            disable3dModels = builder.translation("config.machinefusion.disable3dModels").worldRestart().define("disable3dModels", false);
            disableAlphaWarning = builder.translation("config.machinefusion.disableAlphaWarning").worldRestart().define("disableAlphaWarning", false);
            preferredTemperature = builder.translation("config.machinefusion.preferredTemperature").defineEnum("preferredTemperature", TemperatureType.KELVIN);
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
