import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;

import java.util.Arrays;

public class IngredientHashCountTesting {

    public String numberToBinary(int i) {
        return "0b" + "0".repeat(Integer.numberOfLeadingZeros(i != 0 ? i : 1)) + Integer.toBinaryString(i);
    }

    public void IngredientHashTest() {
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

    public static void main(String[] args) {
        final IngredientHashCountTesting ingredientHashCountTesting = new IngredientHashCountTesting();
        ingredientHashCountTesting.IngredientHashTest();
    }
}
