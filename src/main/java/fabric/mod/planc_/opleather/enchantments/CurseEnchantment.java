package fabric.mod.planc_.opleather.enchantments;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.OpLeather;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

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


    /**
     * All allowed states are when cauldron ingredient state <= key.
     * @return map of long to ingredient count
     */
    public HashMap<Long, IngredientCount[]> possibleIngredientsStates() {
        final HashMap<Long, IngredientCount[]> currentSet = new HashMap<>();
        final IngredientCount[] ingredients = getIngredients();
        for (int[] combination : Utils.allCombinations(ingredients.length)) {
            final IngredientCount[] allPossibleIngredientCounts = Arrays.stream(combination).mapToObj(i -> ingredients[i]).flatMap(ingredientCount -> {
                final int[] possibleCounts = IntStream.range(1, ingredientCount.rightInt() + 1).toArray();
                return Arrays.stream(possibleCounts).mapToObj(count -> new IngredientCount(ingredientCount.left(), count));
            }).toArray(IngredientCount[]::new);

            final List<int[]> combinations = Utils.generateCombinations(allPossibleIngredientCounts.length, combination.length);
            for (int[] countCombination : combinations) {
                final List<String> allItemNames = Arrays.stream(countCombination).mapToObj(e -> allPossibleIngredientCounts[e].left().name()).toList();
                final HashSet<String> uniqueItemNames = new HashSet<>(allItemNames);

                // there is a duplicate of ingredients, skip
                if (uniqueItemNames.size() != allItemNames.size()) continue;

                Arrays.stream(countCombination).mapToObj(e -> allPossibleIngredientCounts[e]).forEach(ingredientCount -> {
                    final Long key = Arrays.stream(countCombination).mapToObj(i -> allPossibleIngredientCounts[i]).map(IngredientCount::shiftForHashKey).reduce(0L, Long::sum);
                    currentSet.computeIfAbsent(key, $ -> Arrays.stream(countCombination).mapToObj(i -> allPossibleIngredientCounts[i]).toArray(IngredientCount[]::new));
                });
            }
        }

        return currentSet;
    }
}
