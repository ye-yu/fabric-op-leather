package fabric.mod.planc_.opleather.blocks;

import fabric.mod.planc_.opleather.items.ModItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CursedCauldron extends LeveledCauldronBlock {
    public static final BooleanProperty INGREDIENT_ADDED = BooleanProperty.of("ingredient_added");

    public static final Logger LOGGER = LogManager.getLogger();

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> ENCHANTED_CAULDRON_BEHAVIOUR = CauldronBehavior.createMap();

    public static final CauldronBehavior ADD_CURSE_TO_CAULDRON = (state, world, pos, player, hand, stack) -> {
        if (state.get(INGREDIENT_ADDED)) {
            CursedCauldron.explodeOnIngredientWrong(world, pos, player, state.get(LEVEL));
            return ActionResult.CONSUME;
        }
        world.setBlockState(pos, state.with(INGREDIENT_ADDED, true));
        CursedCauldron.playSoundEffect(world, pos);
        world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        return ActionResult.CONSUME;
    };

    public static final CauldronBehavior INCREASE_CURSE_POWER = (state, world, pos, player, hand, stack) -> {
        stack.decrement(1);
        if (world.isClient()) return ActionResult.CONSUME;

        if (state.get(LEVEL) == 3) {
            CursedCauldron.explodeOnIngredientWrong(world, pos, player, state.get(LEVEL));
            return ActionResult.CONSUME;
        }

        player.incrementStat(Stats.FILL_CAULDRON);
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

        CursedCauldron.playSoundEffect(world, pos);
        world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        final var newLevel = Math.min(state.get(LEVEL) + 1, 3);
        world.setBlockState(pos, state.with(LEVEL, newLevel));
        return ActionResult.CONSUME;
    };

    public static void explodeOnIngredientWrong(World world, BlockPos pos, Entity entity, int power) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        final var middleX = (pos.getX() + .5 + entity.getPos().getX()) * 0.5;
        final var middleY = (pos.getY() + .5 + entity.getPos().getY()) * 0.5;
        final var middleZ = (pos.getZ() + .5 + entity.getPos().getZ()) * 0.5;
        world.createExplosion(null, middleX, middleY, middleZ, 1 + power * 0.5f, Explosion.DestructionType.DESTROY);
    }

    static {
        ENCHANTED_CAULDRON_BEHAVIOUR.put(Items.FERMENTED_SPIDER_EYE, ADD_CURSE_TO_CAULDRON);
        ENCHANTED_CAULDRON_BEHAVIOUR.put(ModItems.MYSTERIOUS_POTION.item, INCREASE_CURSE_POWER);
    }

    public CursedCauldron() {
        super(FabricBlockSettings.copy(Blocks.CAULDRON).luminance($ -> 7), $ -> false, ENCHANTED_CAULDRON_BEHAVIOUR);
        setDefaultState(getStateManager().getDefaultState().with(INGREDIENT_ADDED, false));
    }

    @Override
    protected boolean canBeFilledByDripstone(Fluid fluid) {
        // prevent drip stone behaviour
        return false;
    }

    public static void playSoundEffect(final World world, final BlockPos pos) {
        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.NEUTRAL, 0.5f, world.random.nextFloat(.9f, 1.1f));
        world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.1f, world.random.nextFloat(.9f, 1.1f));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(INGREDIENT_ADDED);
    }
}
