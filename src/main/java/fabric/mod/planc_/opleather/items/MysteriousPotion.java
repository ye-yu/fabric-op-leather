package fabric.mod.planc_.opleather.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

        final BlockPos blockPos = context.getBlockPos();
        final BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getBlock() == Blocks.CAULDRON) {
            world.setBlockState(blockPos, Blocks.WATER_CAULDRON.getDefaultState());
            world.playSound(null, blockPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.5f, 1);
        }
        return super.useOnBlock(context);
    }
}
