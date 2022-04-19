import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;

import java.util.Arrays;
import java.util.HashMap;

public class IngredientHashCountTesting {

    public String numberToBinary(int i) {
        return "0b" + "0".repeat(Integer.numberOfLeadingZeros(i != 0 ? i : 1)) + Integer.toBinaryString(i);
    }

    public void IngredientHashTest() {
        System.out.printf("%n%n:: IngredientHashTest:%n");
        for (ModEnchantments value : ModEnchantments.values()) {
            final long hash = Arrays.stream(value.enchantment.getIngredients()).mapToLong(IngredientCount::shiftForHashKey).reduce(0, Long::sum);
            System.out.printf(":: Hash binary value: %s%n", numberToBinary((int)hash));
            System.out.printf(":: Hash back to ingredients: %n");

            for (Ingredients ingredients : Ingredients.values()) {
                final int amount = IngredientCount.getAmountForHashKey(ingredients, hash);
                if (amount > 0) {
                    System.out.printf(":: %s: %dx%n", ingredients.name(), amount);
                }
            }

        }

    }

    public void allPossibleStatesTest() {
        System.out.printf("%n%n:: allPossibleStatesTest:%n");
        for (ModEnchantments value : ModEnchantments.values()) {
            System.out.printf("%n:: Enchantment name: %s%n", value.name());
            final HashMap<Long, IngredientCount[]> longHashMap = value.enchantment.possibleIngredientsStates();
            longHashMap.forEach((key, ingredientCounts) -> {
                System.out.printf(":: Combination of %s:%n", numberToBinary(key.intValue()));
                for (IngredientCount ingredientCount : ingredientCounts) {
                    System.out.printf(":: %s: %dx%n", ingredientCount.left().name(), ingredientCount.rightInt());
                }
            });
        }
    }

    public static void main(String[] args) {
        final IngredientHashCountTesting ingredientHashCountTesting = new IngredientHashCountTesting();
        ingredientHashCountTesting.IngredientHashTest();
        ingredientHashCountTesting.allPossibleStatesTest();
    }
}
