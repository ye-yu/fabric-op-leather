package fabric.mod.planc_.opleather.ingredients;

import it.unimi.dsi.fastutil.objects.ObjectIntImmutablePair;

public class IngredientCount extends ObjectIntImmutablePair<Ingredients> {

    public static final int MAX_INGREDIENT = 4;
    public IngredientCount(Ingredients left, Integer right) {
        super(left, right);
        if (right >= MAX_INGREDIENT) throw new RuntimeException("Ingredient amount cannot be more than");
        if (right <= 0) throw new RuntimeException("Ingredient amount cannot be empty");
    }

    public long shiftForHashKey() {
        return IngredientCount.shiftForHashKey(left, right);
    }

    public static long shiftForHashKey(Ingredients ingredients, int amount) {
        return (long) amount << (ingredients.ordinal() * 2);
    }

    public static int getAmountForHashKey(final Ingredients ingredients, final long hash) {
        return (int) (hash >> (ingredients.ordinal() * 2)) & 0b11;
    }
}
