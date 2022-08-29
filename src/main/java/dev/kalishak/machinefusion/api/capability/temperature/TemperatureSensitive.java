package dev.kalishak.machinefusion.api.capability.temperature;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface TemperatureSensitive {

    int DEFAULT_TEMPERATURE = 293;

    /**
     * @return Current's object temperature
     */
    int getCurrentTemperature();

    /**
     * Sets temperature
     * @param temperature Designated value
     */
    void setTemperature(int temperature);

    /**
     * Sets temperature
     * @param scale Scale of smooth temperature changing, lower values slows while higher accelerates
     * @param ticks World's ticks
     * @param temperature Designated value
     */
    void changeTemperature(float scale, float ticks, int temperature);

    /**
     * @return Whether the temperature should be visible in Player's gui
     */
    @OnlyIn(Dist.CLIENT)
    boolean isVisible();

    /**
     * This method is fired once the maximum temperature is reached
     * @param stack This Capability handler
     * @param ticks World's ticks
     * @param entity Item's use
     * @param simulate If false, item won't be destroyed in current tick
     */
    void onMaxTemperature(ItemStack stack, float ticks, LivingEntity entity, boolean simulate);
}
