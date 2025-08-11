package thelm.bewitchmentjei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.CurseRecipe;
import moriyashiine.bewitchment.common.registry.BWRegistries;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class CurseCategory extends AbstractRecipeCategory<CurseRecipe> {

	public static final Text TITLE = Text.translatable("rei.bewitchment.curses");

	public CurseCategory() {
		super(BewitchmentJEI.CURSE, TITLE);
	}

	@Override
	public int getWidth() {
		return 72;
	}

	@Override
	public int getHeight() {
		return 38;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CurseRecipe recipe, IFocusGroup focuses) {
		int startX = 1 + getWidth() / 2 - recipe.input.size() * 9;
		for(int i = 0; i < recipe.input.size(); i++) {
			addItem(builder, RecipeIngredientRole.INPUT, startX + i * 18, 11, recipe.input.get(i), JEIDrawables.SLOT);
		}
	}

	@Override
	public void draw(CurseRecipe recipe, IRecipeSlotsView recipeSlotsView, MatrixStack poseStack, double mouseX, double mouseY) {
		TextRenderer font = font();
		Text nameComponent = Text.translatable("curse." + BWRegistries.CURSES.getId(recipe.curse).toString().replace(":", "."));
		Text costComponent = Text.translatable("bewitchment.tooltip.cost", recipe.cost);
		font.draw(poseStack, nameComponent, getWidth() / 2 - font.getWidth(nameComponent) / 2, 0, 0x3F3F3F);
		font.draw(poseStack, costComponent, getWidth() / 2 - font.getWidth(costComponent) / 2, getHeight() - font.fontHeight, 0x3F3F3F);
	}
}
