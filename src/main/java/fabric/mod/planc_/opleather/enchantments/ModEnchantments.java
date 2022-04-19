package fabric.mod.planc_.opleather.enchantments;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import fabric.mod.planc_.opleather.ingredients.Ingredients;
import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Locale;
import java.util.function.Supplier;

public enum ModEnchantments {
    CURSE_OF_WEBBING(CurseOfWebbing::new),
    CURSE_OF_RAGE(CurseOfRage::new);

    public static final Logger LOGGER = LogManager.getLogger();

    public final CurseEnchantment enchantment;
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));


    ModEnchantments(Supplier<? extends CurseEnchantment> supplier) {
        this.enchantment = supplier.get();
    }

    public static final Supplier<HashMap<Long, IngredientCount[]>> ALL_POSSIBLE_INGREDIENT_STATES = Suppliers.memoize(() -> {
        final HashMap<Long, IngredientCount[]> map = new HashMap<>();
        for (ModEnchantments value : ModEnchantments.values()) {
            final HashMap<Long, IngredientCount[]> longHashMap = value.enchantment.possibleIngredientsStates();
            map.putAll(longHashMap);
        }

        return map;
    });

    public static Long getCurrentCauldronIngredientState(BlockState state) {
        long ingredientState = 0;
        for (Ingredients ingredients : Ingredients.values()) {
            final int count = state.get(ingredients.property);
            LOGGER.info("Cauldron has ingredient: {} {}x", ingredients.name(), count);
            ingredientState += IngredientCount.shiftForHashKey(ingredients, count);
        }
        return ingredientState;
    }
}
