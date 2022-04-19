package fabric.mod.planc_.opleather.enchantments;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.OpLeather;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashSet;
import java.util.function.Supplier;

public abstract class CurseEnchantment extends Enchantment {
    public static final Supplier<ItemPredicate> LEATHER_PREDICATE = Suppliers.memoize(() -> ItemPredicate.Builder.create().tag(OpLeather.LEATHER_ARMORS.get()).build());

    protected CurseEnchantment(EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(Rarity.RARE, type, slotTypes);
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack) && LEATHER_PREDICATE.get().test(stack);
    }
    public void tick(ServerPlayerEntity player, int strength) {}

    public void onInitialize() {}

    public void onEquipOrRefresh(ServerPlayerEntity player, final int level) {}

    public boolean canStack() {
        return false;
    }

    public IngredientCount[] getIngredients() {
        return new IngredientCount[]{};
    }

    public HashSet<Long> possibleIngredientsStates(HashSet<Long> currentList, Ingredients...ignore) {
        return null;
    }

    public HashSet<Long> possibleIngredientsStates() {
        final HashSet<Long> currentSet = new HashSet<>();
        return this.possibleIngredientsStates(currentSet);
    }
}
