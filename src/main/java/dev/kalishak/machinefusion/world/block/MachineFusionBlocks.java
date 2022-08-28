package dev.kalishak.machinefusion.world.block;

import dev.kalishak.machinefusion.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MachineFusionBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.Keys.BLOCKS, References.MOD_ID);

    public static final RegistryObject<Block> TIN_ORE = BLOCKS.register(References.Block.TIN_ORE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = BLOCKS.register(References.Block.DEEPSLATE_TIN_ORE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register(References.Block.TIN_BLOCK, () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register(References.Block.ALUMINUM_ORE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = BLOCKS.register(References.Block.DEEPSLATE_ALUMINUM_ORE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> ALUMINUM_BLOCK = BLOCKS.register(References.Block.ALUMINUM_BLOCK, () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> LEAD_ORE = BLOCKS.register(References.Block.LEAD_ORE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = BLOCKS.register(References.Block.DEEPSLATE_LEAD_ORE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register(References.Block.LEAD_BLOCK, () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static void register(final IEventBus bus) {
        BLOCKS.register(bus);
    }
}
