package fabric.mod.planc_.opleather.blocks;

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
import net.minecraft.util.ActionResult;
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

    static {
        ENCHANTED_CAULDRON_BEHAVIOUR.put(Items.FERMENTED_SPIDER_EYE, ADD_CURSE_TO_CAULDRON);
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
