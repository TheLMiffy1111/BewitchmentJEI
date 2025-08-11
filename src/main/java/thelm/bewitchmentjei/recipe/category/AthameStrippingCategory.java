package thelm.bewitchmentjei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.AthameStrippingRecipe;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class AthameStrippingCategory extends AbstractRecipeCategory<AthameStrippingRecipe> {

	public static final Text TITLE = Text.translatable("rei.bewitchment.athame_stripping");

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
		addItem(builder, RecipeIngredientRole.OUTPUT, 57, 5, recipe.getOutput(), JEIDrawables.OUTPUT_SLOT);
		addItem(builder, RecipeIngredientRole.OUTPUT, 81, 5, new ItemStack(recipe.strippedLog), JEIDrawables.SLOT);
	}

	@Override
	public void draw(AthameStrippingRecipe recipe, IRecipeSlotsView recipeSlotsView, MatrixStack poseStack, double mouseX, double mouseY) {
		JEIDrawables.RECIPE_ARROW.draw(poseStack, 24, 4);
	}
}
