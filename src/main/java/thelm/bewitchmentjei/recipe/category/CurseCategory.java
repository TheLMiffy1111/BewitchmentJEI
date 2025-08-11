package thelm.bewitchmentjei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.CurseRecipe;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class CurseCategory extends AbstractRecipeCategory<CurseRecipe> {

	public static final Text TITLE = Text.translatable("emi.category.bewitchment.curses");

	public CurseCategory() {
		super(BewitchmentJEI.CURSE, TITLE);
	}

	@Override
	public int getWidth() {
		return 108;
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
	public void draw(CurseRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
		TextRenderer font = font();
		Text nameComponent = Text.translatable("curse." + recipe.getId().toString().replaceAll("curses.", "").replaceAll("[:/]", "."));
		Text costComponent = Text.translatable("bewitchment.tooltip.cost", recipe.cost);
		guiGraphics.drawText(font, nameComponent, getWidth() / 2 - font.getWidth(nameComponent) / 2, 0, 0x3F3F3F, false);
		guiGraphics.drawText(font, costComponent, getWidth() / 2 - font.getWidth(costComponent) / 2, getHeight() - font.fontHeight, 0x3F3F3F, false);
	}
}
