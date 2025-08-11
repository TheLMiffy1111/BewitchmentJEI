package thelm.bewitchmentjei.ingredient.subtype;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.item.ItemStack;

public class ContractItemSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {

	@Override
	public String apply(ItemStack ingredient, UidContext context) {
		if(ingredient.hasNbt()) {
			return ingredient.getNbt().getString("Contract");
		}
		return NONE;
	}
}
