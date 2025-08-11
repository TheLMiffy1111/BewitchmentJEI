package thelm.bewitchmentjei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.AthameStrippingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class AthameStrippingCategory extends AbstractRecipeCategory<AthameStrippingRecipe> {

	public static final Text TITLE = Text.translatable("emi.category.bewitchment.athame_stripping");

	public AthameStrippingCategory() {
		super(BewitchmentJEI.ATHAME_STRIPPING, TITLE);
	}

	@Override
	public int getWidth() {
		return 98;
	}

	@Override
	public int getHeight() {
		return 26;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, AthameStrippingRecipe recipe, IFocusGroup focuses) {
		addItem(builder, RecipeIngredientRole.INPUT, 1, 5, new ItemStack(recipe.log), JEIDrawables.SLOT);
		addItem(builder, RecipeIngredientRole.OUTPUT, 57, 5, recipe.getOutput(registryAccess()), JEIDrawables.OUTPUT_SLOT);
		addItem(builder, RecipeIngredientRole.OUTPUT, 81, 5, new ItemStack(recipe.strippedLog), JEIDrawables.SLOT);
	}

	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, AthameStrippingRecipe recipe, IFocusGroup focuses) {
		builder.addDrawable(JEIDrawables.RECIPE_ARROW, 24, 4);
	}
}
