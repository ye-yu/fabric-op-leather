package fabric.mod.planc_.opleather.ingredients;

import com.google.common.base.Suppliers;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.property.IntProperty;

import java.util.HashMap;
import java.util.Locale;
import java.util.function.Supplier;

public enum Ingredients {
    FERMENTED_SPIDER_EYE(Suppliers.memoize(() -> Items.FERMENTED_SPIDER_EYE), 1),
    WITHER_SKULL(Suppliers.memoize(() -> Items.WITHER_SKELETON_SKULL), 1),
    GLOWSTONE_DUST(Suppliers.memoize(() -> Items.GLOWSTONE_DUST), 3),
    ;

    public final Supplier<Item> item;
    public final int maxAmount;

    public final IntProperty property;

    Ingredients(Supplier<Item> item, int maxAmount) {
        this.item = item;
        this.maxAmount = maxAmount;
        this.property = IntProperty.of(String.format("cauldron_ingredient_%s", this.name().toLowerCase(Locale.ENGLISH)), 0, this.maxAmount);
    }

    public static final HashMap<Integer, Ingredients> INT_2_INGREDIENT_MAP = new HashMap<>() {{
        final Ingredients[] ingredients = Ingredients.values();
        for (int i = 0; i < ingredients.length; i++) {
            put(i + 1, ingredients[i]);
        }
    }};
}
