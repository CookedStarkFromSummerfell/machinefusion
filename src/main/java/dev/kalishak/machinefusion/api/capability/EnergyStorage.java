package dev.kalishak.machinefusion.api.capability;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface EnergyStorage {
    /**
     * Adds energy to the storage. Returns quantity of energy that was accepted.
     *
     * @param maxReceive
     *            Maximum amount of energy to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
    long receiveEnergy(long maxReceive, boolean simulate);

    /**
     * Extracts energy to provided storage. Returns quantity of energy that was accepted.
     *
     * @param storage
     *            Extraction space
     * @param maxReceive
     *            Maximum amount of energy to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
    long receiveEnergy(EnergyStorage storage, long maxReceive, boolean simulate);

    /**
     * Removes energy from the storage. Returns quantity of energy that was removed.
     *
     * @param maxExtract
     *            Maximum amount of energy to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted from the storage.
     */
    long extractEnergy(long maxExtract, boolean simulate);

    /**
     * Removes energy from this storage and extracts it to provided one. Returns quantity of energy that was removed.
     *
     * @param storage
     *            Storage to receive
     * @param maxExtract
     *            Maximum amount of energy to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted from the storage.
     */
    long extractEnergy(EnergyStorage storage, long maxExtract, boolean simulate);

    /**
     * Returns the amount of energy currently stored.
     */
    long getStored();

    /**
     * Sets provided amount as this storage stored content
     * <b>TEST GAME ONLY</b>
     * @param amount to mark as stored
     */
    void setStored(long amount);

    /**
     * Returns the maximum amount of energy that can be stored.
     */
    long getCapacity();

    /**
     * Returns if this storage can have energy extracted.
     * If this is false, then any calls to extractEnergy will return 0.
     */
    boolean shouldExtract();

    /**
     * Used to determine if this storage can receive energy.
     * If this is false, then any calls to receiveEnergy will return 0.
     */
    boolean shouldReceive();

}