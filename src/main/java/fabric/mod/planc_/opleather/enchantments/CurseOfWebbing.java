package fabric.mod.planc_.opleather.enchantments;

import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;
import fabric.mod.planc_.opleather.interfaces.CurseOfWebbingMessenger;
import fabric.mod.planc_.opleather.utils.LimitedLogging;
import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;
import java.util.UUID;

public class CurseOfWebbing extends CurseEnchantment {

    public static final LimitedLogging LOGGER = new LimitedLogging(LogManager.getLogger(CurseOfWebbing.class));

    public CurseOfWebbing() {
        super(EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public static final Identifier SNEAKING_VAR = Utils.identifier("sneaking_var");
    public static final int SNEAKING_TICK_COUNT = 5;

    private static final UUID CURSE_OF_WEBBING_BOOST_ID = UUID.fromString("d229da59-3cdd-41f6-aac5-a27808f5169f");

    @Override
    public void tick(ServerPlayerEntity player, int strength) {
        super.tick(player, strength);
        final ServerWorld world = player.getWorld();

        // block collision tick
        boolean hasCobweb = false;
        Box box = player.getBoundingBox();
        BlockPos blockPos = new BlockPos(box.minX + 0.001, box.minY + 0.001, box.minZ + 0.001);
        BlockPos blockPos2 = new BlockPos(box.maxX - 0.001, box.maxY - 0.001, box.maxZ - 0.001);
        //noinspection deprecation
        if (world.isRegionLoaded(blockPos, blockPos2)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int i = blockPos.getX(); i <= blockPos2.getX(); ++i) {
                for (int j = blockPos.getY(); j <= blockPos2.getY(); ++j) {
                    for (int k = blockPos.getZ(); k <= blockPos2.getZ(); ++k) {
                        mutable.set(i, j, k);
                        hasCobweb |= world.getBlockState(mutable).getBlock() == Blocks.COBWEB;
                    }
                }
            }
        }

        EntityAttributeInstance entityAttributeInstance = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (Objects.nonNull(entityAttributeInstance) && player instanceof CurseOfWebbingMessenger messenger) {
            final var level = messenger.getSpeedLevel();
            final var modifier = new EntityAttributeModifier(CURSE_OF_WEBBING_BOOST_ID, "Curse of webbing boost", 0.03f * (1.0f + level * 0.35f), EntityAttributeModifier.Operation.ADDITION);

            if (hasCobweb) {
                if (!entityAttributeInstance.hasModifier(modifier)) {
                    entityAttributeInstance.addTemporaryModifier(modifier);
                }
            } else entityAttributeInstance.removeModifier(CURSE_OF_WEBBING_BOOST_ID);
        }

        // sneaking tick
        final boolean sneaking = player.isSneaking();
        final var sneakingStat = Stats.CUSTOM.getOrCreateStat(SNEAKING_VAR);
        if (sneaking) player.incrementStat(sneakingStat);
        else player.resetStat(sneakingStat);
        final int stat = player.getStatHandler().getStat(sneakingStat);
        if (stat < SNEAKING_TICK_COUNT) return;
        player.resetStat(sneakingStat);
        world.setBlockState(player.getBlockPos(), Blocks.COBWEB.getDefaultState());
    }

    @Override
    public void onInitialize() {
        Registry.register(Registry.CUSTOM_STAT, SNEAKING_VAR.getPath(), SNEAKING_VAR);
    }

    private static final IngredientCount[] INGREDIENTS = new IngredientCount[] {
            new IngredientCount(Ingredients.FERMENTED_SPIDER_EYE, 1)
    };
    @Override
    public IngredientCount[] getIngredients() {
        return INGREDIENTS;
    }
}
