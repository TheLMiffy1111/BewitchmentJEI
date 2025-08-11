package thelm.bewitchmentjei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.OilRecipe;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class OilCategory extends AbstractRecipeCategory<OilRecipe> {

	public static final Text TITLE = Text.translatable("rei.bewitchment.oil_crafting");

	public OilCategory() {
		super(BewitchmentJEI.OIL, TITLE);
	}

	@Override
	public int getWidth() {
		return 96;
	}

	@Override
	public int getHeight() {
		return 36;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, OilRecipe recipe, IFocusGroup focuses) {
		for(int i = 0; i < recipe.input.size(); i++) {
			addItem(builder, RecipeIngredientRole.INPUT, 1 + i % 2 * 18, 1 + i / 2 * 18, recipe.input.get(i), JEIDrawables.SLOT);
		}
		addItem(builder, RecipeIngredientRole.OUTPUT, 75, 10, recipe.getOutput(), JEIDrawables.OUTPUT_SLOT);
	}

	@Override
	public void draw(OilRecipe recipe, IRecipeSlotsView recipeSlotsView, MatrixStack poseStack, double mouseX, double mouseY) {
		JEIDrawables.RECIPE_ARROW.draw(poseStack, 42, 9);
	}
}
