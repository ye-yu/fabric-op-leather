package fabric.mod.planc_.opleather.enchantments;

import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;
import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;
import java.util.UUID;

public class CurseOfRage extends CurseEnchantment {

    public static final Identifier HEALTH_TAKEN_LEVEL_VAR = Utils.identifier("health_taken_level_var");
    public static final UUID HEALTH_TAKEN_STRENGTH_BOOST_ID = UUID.fromString("d229da59-3cdd-41f6-aac5-a27808f5169e");

    protected CurseOfRage() {
        super(EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{ EquipmentSlot.CHEST });
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public void onEquipOrRefresh(ServerPlayerEntity player, int level) {
        final var healthTakenLevelStat = Stats.CUSTOM.getOrCreateStat(HEALTH_TAKEN_LEVEL_VAR);

        final var healthTaken = player.getStatHandler().getStat(healthTakenLevelStat);

        if (level == healthTaken) return;

        player.resetStat(healthTakenLevelStat);
        EntityAttributeInstance healthAttributeInstance = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        EntityAttributeInstance strengthAttributeInstance = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (Objects.isNull(healthAttributeInstance) || Objects.isNull(strengthAttributeInstance)) return;
        final var healthModifier = new EntityAttributeModifier(HEALTH_TAKEN_STRENGTH_BOOST_ID, "Health taken for strength boost", -(0.03f * (1.0f + level * 0.35f)), EntityAttributeModifier.Operation.ADDITION);
        final var strengthModifier = new EntityAttributeModifier(HEALTH_TAKEN_STRENGTH_BOOST_ID, "Health taken for strength boost", 0.03f * (1.0f + level * 0.35f), EntityAttributeModifier.Operation.ADDITION);
        healthAttributeInstance.removeModifier(HEALTH_TAKEN_STRENGTH_BOOST_ID);
        strengthAttributeInstance.removeModifier(HEALTH_TAKEN_STRENGTH_BOOST_ID);
        healthAttributeInstance.addTemporaryModifier(healthModifier);
        strengthAttributeInstance.addTemporaryModifier(strengthModifier);
    }

    @Override
    public void onInitialize() {
        Registry.register(Registry.CUSTOM_STAT, HEALTH_TAKEN_LEVEL_VAR.getPath(), HEALTH_TAKEN_LEVEL_VAR);
    }

    private static final IngredientCount[] INGREDIENTS = new IngredientCount[] {
            new IngredientCount(Ingredients.WITHER_SKULL, 2),
            new IngredientCount(Ingredients.FERMENTED_SPIDER_EYE, 3),
    };
    @Override
    public IngredientCount[] getIngredients() {
        return INGREDIENTS;
    }
}
