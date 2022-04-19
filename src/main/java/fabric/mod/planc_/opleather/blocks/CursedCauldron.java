package fabric.mod.planc_.opleather.blocks;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.ingredients.Ingredients;
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

import java.util.function.Function;
import java.util.function.Supplier;

public class CursedCauldron extends LeveledCauldronBlock {
    public static final BooleanProperty INGREDIENT_ADDED = BooleanProperty.of("ingredient_added");
    public static final BooleanProperty BAD_INGREDIENT = BooleanProperty.of("bad_ingredient");

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
    public static final Function<Ingredients, CauldronBehavior> ADD_INGREDIENT_TO_CAULDRON = ingredients -> (state, world, pos, player, hand, stack) -> {
        world.setBlockState(pos, state.with(INGREDIENT_ADDED, true));
        CursedCauldron.playSoundEffect(world, pos);

        if (state.get(BAD_INGREDIENT)) {
            CursedCauldron.explodeOnIngredientWrong(world, pos, player, state.get(LEVEL));
            return ActionResult.CONSUME;
        }

        // check if current ingredient exceeds the maximum allowed
        final Integer currentAmount = state.get(ingredients.property);
        if (currentAmount >= ingredients.maxAmount) {
            // TODO: trigger bad ingredient first and then explode after a few seconds
            CursedCauldron.explodeOnIngredientWrong(world, pos, player, state.get(LEVEL));
            return ActionResult.CONSUME;
        }

        // check if there is any matching curse

        world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        return ActionResult.CONSUME;
    };

    public static final Supplier<Object2ObjectOpenHashMap<Item, CauldronBehavior>> ENCHANTED_CAULDRON_BEHAVIOUR = Suppliers.memoize(() -> {
        final Object2ObjectOpenHashMap<Item, CauldronBehavior> map = CauldronBehavior.createMap();
        for (Ingredients ingredient : Ingredients.values()) {
            final Item item = ingredient.item.get();
            map.put(item, ADD_INGREDIENT_TO_CAULDRON.apply(ingredient));
        }
        map.put(ModItems.MYSTERIOUS_POTION.item.get(), INCREASE_CURSE_POWER);

        return map;
    });

    public static void explodeOnIngredientWrong(World world, BlockPos pos, Entity entity, int power) {
        world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
        final var middleX = (pos.getX() + .5 + entity.getPos().getX()) * 0.5;
        final var middleY = (pos.getY() + .5 + entity.getPos().getY()) * 0.5;
        final var middleZ = (pos.getZ() + .5 + entity.getPos().getZ()) * 0.5;
        world.createExplosion(null, middleX, middleY, middleZ, power * 0.5f, Explosion.DestructionType.DESTROY);
    }

    public CursedCauldron() {
        super(FabricBlockSettings.copy(Blocks.CAULDRON).luminance($ -> 7), $ -> false, ENCHANTED_CAULDRON_BEHAVIOUR.get());
        BlockState defaultState = getStateManager()
                .getDefaultState()
                .with(INGREDIENT_ADDED, false)
                .with(BAD_INGREDIENT, false);
        for (Ingredients ingredients : Ingredients.values()) {
            defaultState = defaultState.with(ingredients.property, 0);
        }
        setDefaultState(defaultState);
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
        builder.add(BAD_INGREDIENT);
        for (Ingredients ingredients : Ingredients.values()) {
            builder.add(ingredients.property);
        }
    }
}
