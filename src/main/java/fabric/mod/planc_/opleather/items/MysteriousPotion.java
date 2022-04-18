package fabric.mod.planc_.opleather.items;

import fabric.mod.planc_.opleather.blocks.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MysteriousPotion extends Item {
    public MysteriousPotion(Settings settings) {
        super(settings);
    }

    public MysteriousPotion() {
        this(new FabricItemSettings().group(ItemGroup.BREWING).maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final World world = context.getWorld();
        if (world.isClient()) return super.useOnBlock(context);

        final BlockPos pos = context.getBlockPos();
        final BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() == Blocks.CAULDRON) {
            context.getStack().decrement(1);
            world.setBlockState(pos, ModBlocks.CURSED_CAULDRON.block.getDefaultState().with(LeveledCauldronBlock.LEVEL, 1));
            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.NEUTRAL, 0.5f, world.random.nextFloat(.9f, 1.1f));
            world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.1f, world.random.nextFloat(.9f, 1.1f));
            return ActionResult.CONSUME;
        }
        return super.useOnBlock(context);
    }
}
