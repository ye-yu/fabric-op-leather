package fabric.mod.planc_.opleather.blocks;

import fabric.mod.planc_.opleather.items.ModItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CursedCauldron extends LeveledCauldronBlock {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> ENCHANTED_CAULDRON_BEHAVIOUR = CauldronBehavior.createMap();

    public static final CauldronBehavior ADD_CURSE_TO_CAULDRON = (state, world, pos, player, hand, stack) -> {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 0.5f, .5f);
        LOGGER.info("EnchantedCauldronBlock: ADD_CURSE_RECIPE");
        return ActionResult.CONSUME;
    };

    public static final CauldronBehavior INCREASE_CURSE_POWER = (state, world, pos, player, hand, stack) -> {
        stack.decrement(1);
        if (world.isClient()) return ActionResult.CONSUME;

        if (state.get(LEVEL) == 3) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            final var middleX = (pos.getX() + .5 + player.getPos().getX()) * 0.5;
            final var middleY = (pos.getY() + .5 + player.getPos().getY()) * 0.5;
            final var middleZ = (pos.getZ() + .5 + player.getPos().getZ()) * 0.5;
            world.createExplosion(null, middleX, middleY, middleZ, 3, Explosion.DestructionType.DESTROY);
            return ActionResult.CONSUME;
        }

        player.incrementStat(Stats.FILL_CAULDRON);
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

        world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.5f, 0.3f - (world.random.nextFloat() * 0.2f));
        world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

        final var newLevel = Math.min(state.get(LEVEL) + 1, 3);
        world.setBlockState(pos, state.with(LEVEL, newLevel));
        return ActionResult.CONSUME;
    };

    static {
        ENCHANTED_CAULDRON_BEHAVIOUR.put(Items.FERMENTED_SPIDER_EYE, ADD_CURSE_TO_CAULDRON);
        ENCHANTED_CAULDRON_BEHAVIOUR.put(ModItems.MYSTERIOUS_POTION.item, INCREASE_CURSE_POWER);
    }

    public CursedCauldron() {
        super(FabricBlockSettings.copy(Blocks.CAULDRON).luminance($ -> 7), $ -> false, ENCHANTED_CAULDRON_BEHAVIOUR);
    }

    @Override
    protected boolean canBeFilledByDripstone(Fluid fluid) {
        // prevent drip stone behaviour
        return false;
    }
}
