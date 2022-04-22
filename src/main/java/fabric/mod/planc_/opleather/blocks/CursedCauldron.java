package fabric.mod.planc_.opleather.blocks;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;
import fabric.mod.planc_.opleather.items.ModItems;
import fabric.mod.planc_.opleather.schedulers.EndWorldTickScheduler;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class CursedCauldron extends LeveledCauldronBlock {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final BooleanProperty INGREDIENT_ADDED = BooleanProperty.of("ingredient_added");
    public static final BooleanProperty BAD_INGREDIENT = BooleanProperty.of("bad_ingredient");

    public static final CauldronBehavior INCREASE_CURSE_POWER = (state, world, pos, player, hand, stack) -> {
        if (world.isClient()) return ActionResult.CONSUME;

        if (state.get(BAD_INGREDIENT)) {
            return ActionResult.FAIL;
        }

        if (state.get(LEVEL) == 3) {
            CursedCauldron.explodeOnIngredientWrong(world, pos, player, state.get(LEVEL));
            return ActionResult.CONSUME;
        }

        player.incrementStat(Stats.FILL_CAULDRON);
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

        CursedCauldron.playFilledSoundEffect(world, pos);
        world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        final var newLevel = Math.min(state.get(LEVEL) + 1, 3);
        world.setBlockState(pos, state.with(LEVEL, newLevel));
        return ActionResult.CONSUME;
    };
    public static final Function<Ingredients, CauldronBehavior> ADD_INGREDIENT_TO_CAULDRON = ingredients -> (state, world, pos, player, hand, stack) -> {
        if (world.isClient()) return ActionResult.PASS;
        if (state.get(BAD_INGREDIENT)) {
            return ActionResult.FAIL;
        }

        state = state.with(INGREDIENT_ADDED, true);
        world.setBlockState(pos, state);

        // check if current ingredient exceeds the maximum allowed
        final Integer currentAmount = state.get(ingredients.property);
        if (currentAmount >= ingredients.maxAmount) {
            final BlockState newState = ModBlocks.CURSED_CAULDRON.block.getDefaultState().with(BAD_INGREDIENT, true);
            world.setBlockState(pos, newState);
            badIngredientEffect(state, world, pos, player);
            return ActionResult.CONSUME;
        }

        // check if there is any matching curse
        state = state.with(ingredients.property, Math.min(state.get(ingredients.property) + 1, ingredients.maxAmount));
        final HashMap<Long, IngredientCount[]> allPossibleIngredientStates = ModEnchantments.ALL_POSSIBLE_INGREDIENT_STATES.get();
        final Long currentCauldronIngredientState = ModEnchantments.getCurrentCauldronIngredientState(state);
        LOGGER.info("ADD_INGREDIENT_TO_CAULDRON: {}", currentCauldronIngredientState);
        final IngredientCount[] ingredientCounts = allPossibleIngredientStates.get(currentCauldronIngredientState);
        LOGGER.info("ADD_INGREDIENT_TO_CAULDRON: Matched ingredients: {}", Objects.isNull(ingredientCounts) ? "null" : Arrays.stream(ingredientCounts).map(Object::toString).toString());

        if (Objects.isNull(ingredientCounts)) {
            final BlockState newState = ModBlocks.CURSED_CAULDRON.block.getDefaultState().with(BAD_INGREDIENT, true);
            world.setBlockState(pos, newState);
            badIngredientEffect(state, world, pos, player);
            return ActionResult.CONSUME;
        }

        world.setBlockState(pos, state);
        world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        CursedCauldron.playSoundEffect(world, pos);
        return ActionResult.CONSUME;
    };

    private static void badIngredientEffect(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.5f, .2f);
        final int strength = state.get(LEVEL);
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SMOKE,
                    pos.getX() + .5,
                    pos.getY(),
                    pos.getZ() + .5,
                    100,
                    0.5,
                    1,
                    0.5,
                    .01);
        }
        final Runnable playParticleAnimation = () -> {
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.POOF,
                        pos.getX() + .5,
                        pos.getY(),
                        pos.getZ() + .5,
                        100,
                        0.5,
                        1,
                        0.5,
                        .01);
            }
        };
        EndWorldTickScheduler.addDelayedTask(10, playParticleAnimation);
        EndWorldTickScheduler.addDelayedTask(15, playParticleAnimation);
        EndWorldTickScheduler.addDelayedTask(20, playParticleAnimation);
        EndWorldTickScheduler.addDelayedTask(35, () -> {
            if (world instanceof ServerWorld serverWorld) {
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1f, .001f);
                serverWorld.spawnParticles(ParticleTypes.POOF,
                        pos.getX() + .5,
                        pos.getY(),
                        pos.getZ() + .5,
                        150,
                        0.5,
                        1,
                        0.5,
                        .01);

                for (PlayerEntity playerEntity : serverWorld.getEntitiesByType(EntityType.PLAYER, new Box(pos).expand(25), $ -> true)) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 10, Math.max(0, strength - 1)));
                }

                final BlockState blockState = world.getBlockState(pos);
                if (blockState.getBlock() == ModBlocks.CURSED_CAULDRON.block) {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }
        });
    }


    public static final Supplier<Map<Item, CauldronBehavior>> CURSED_CAULDRON_BEHAVIOUR = Suppliers.memoize(() -> {
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
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.POOF,
                    pos.getX() + .5,
                    pos.getY(),
                    pos.getZ() + .5,
                    150,
                    0.5,
                    1,
                    0.5,
                    .01);
        }
    }

    public CursedCauldron() {
        super(FabricBlockSettings.copy(Blocks.CAULDRON).luminance($ -> 7), $ -> false, CURSED_CAULDRON_BEHAVIOUR.get());
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
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.POOF,
                    pos.getX() + .5,
                    pos.getY(),
                    pos.getZ() + .5,
                    150,
                    0.5,
                    1,
                    0.5,
                    .01);
        }
    }

    public static void playFilledSoundEffect(final World world, final BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1f, world.random.nextFloat(.9f, 1.1f));
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.POOF,
                    pos.getX() + .5,
                    pos.getY(),
                    pos.getZ() + .5,
                    50,
                    0.5,
                    1,
                    0.5,
                    .01);
        }
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
