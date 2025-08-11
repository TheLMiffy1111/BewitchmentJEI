package thelm.bewitchmentjei.recipe.category;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.RitualRecipe;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;
import thelm.jeidrawables.gui.render.BlankDrawable;
import thelm.jeidrawables.gui.render.CyclingDrawable;
import thelm.jeidrawables.gui.render.ResourceDrawable;
import thelm.jeidrawables.gui.render.ScaledDrawable;

public class RitualCategory extends AbstractRecipeCategory<RitualRecipe> {

	public static final Text TITLE = new TranslatableText("rei.bewitchment.rituals");

	public static final Identifier CHALK_NORMAL = new Identifier("bewitchment:textures/gui/patchouli/chalk/normal.png");
	public static final Identifier CHALK_FIERY = new Identifier("bewitchment:textures/gui/patchouli/chalk/fiery.png");
	public static final Identifier CHALK_ELDRITCH = new Identifier("bewitchment:textures/gui/patchouli/chalk/eldritch.png");
	public static final IDrawable CHALK_NORMAL_OUTER = new ScaledDrawable(new ResourceDrawable(CHALK_NORMAL, 2, 2, 11, 11, 16, 16), 2);
	public static final IDrawable CHALK_NORMAL_INNER = new ScaledDrawable(new ResourceDrawable(CHALK_NORMAL, 4, 4, 7, 7, 16, 16), 2);
	public static final IDrawable CHALK_FIERY_OUTER = new ScaledDrawable(new ResourceDrawable(CHALK_FIERY, 2, 2, 11, 11, 16, 16), 2);
	public static final IDrawable CHALK_FIERY_INNER = new ScaledDrawable(new ResourceDrawable(CHALK_FIERY, 4, 4, 7, 7, 16, 16), 2);
	public static final IDrawable CHALK_ELDRITCH_OUTER = new ScaledDrawable(new ResourceDrawable(CHALK_ELDRITCH, 2, 2, 11, 11, 16, 16), 2);
	public static final IDrawable CHALK_ELDRITCH_INNER = new ScaledDrawable(new ResourceDrawable(CHALK_ELDRITCH, 4, 4, 7, 7, 16, 16), 2);
	public static final IDrawable CHALK_ANY_OUTER = new CyclingDrawable(1000, CHALK_NORMAL_OUTER, CHALK_FIERY_OUTER, CHALK_ELDRITCH_OUTER);
	public static final IDrawable CHALK_ANY_INNER = new CyclingDrawable(1000, CHALK_NORMAL_INNER, CHALK_FIERY_INNER, CHALK_ELDRITCH_INNER);
	public static final IDrawable CHALK_UNKNOWN_OUTER = new BlankDrawable(22, 22);
	public static final IDrawable CHALK_UNKNOWN_INNER = new BlankDrawable(14, 14);

	public RitualCategory() {
		super(BewitchmentJEI.RITUAL, TITLE);
	}

	@Override
	public int getWidth() {
		return 144;
	}

	@Override
	public int getHeight() {
		return 78;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, RitualRecipe recipe, IFocusGroup focuses) {
		int startX = 1 + getWidth() / 2 - recipe.input.size() * 9;
		for(int i = 0; i < recipe.input.size(); i++) {
			addItem(builder, RecipeIngredientRole.INPUT, startX + i * 18, 11, recipe.input.get(i), JEIDrawables.SLOT);
		}
	}

	@Override
	public void draw(RitualRecipe recipe, IRecipeSlotsView recipeSlotsView, MatrixStack poseStack, double mouseX, double mouseY) {
		TextRenderer font = font();
		Text nameComponent = new TranslatableText("ritual." + recipe.getId().toString().replaceAll("[:/]", "."));
		Text costComponent = new TranslatableText("bewitchment.tooltip.cost", recipe.cost);
		font.draw(poseStack, nameComponent, getWidth() / 2 - font.getWidth(nameComponent) / 2, 0, 0x3F3F3F);
		font.draw(poseStack, costComponent, getWidth() / 2 - font.getWidth(costComponent) / 2, getHeight() - 2 * font.fontHeight, 0x3F3F3F);
		if(recipe.runningTime > 0) {
			Text timeComponent = new TranslatableText("bewitchment.tooltip.running_time", recipe.runningTime);
			font.draw(poseStack, timeComponent, getWidth() / 2 - font.getWidth(timeComponent) / 2, getHeight() - font.fontHeight, 0x3F3F3F);
		}
		if(!recipe.outer.isEmpty()) {
			IDrawable chalkOuter = switch(recipe.outer) {
			case "normal" -> CHALK_NORMAL_OUTER;
			case "fiery" -> CHALK_FIERY_OUTER;
			case "eldritch" -> CHALK_ELDRITCH_OUTER;
			case "any" -> CHALK_ANY_OUTER;
			default -> CHALK_UNKNOWN_OUTER;
			};
			chalkOuter.draw(poseStack, getWidth() / 2 - 11, 32);
		}
		IDrawable chalkInner = switch(recipe.inner) {
		case "normal" -> CHALK_NORMAL_INNER;
		case "fiery" -> CHALK_FIERY_INNER;
		case "eldritch" -> CHALK_ELDRITCH_INNER;
		case "any" -> CHALK_ANY_INNER;
		default -> CHALK_UNKNOWN_INNER;
		};
		chalkInner.draw(poseStack, getWidth() / 2 - 7, 36);
	}

	@Override
	public List<Text> getTooltipStrings(RitualRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		if(mouseX >= getWidth() / 2 - 11 && mouseX < getWidth() / 2 + 11 && mouseY >= 32 && mouseY < 54) {
			List<Text> tooltip = new ArrayList<>(2);
			tooltip.add(new TranslatableText("bewitchment.tooltip.inner_circle", new TranslatableText("chalk.bewitchment." + recipe.inner)));
			if(!recipe.outer.isEmpty()) {
				tooltip.add(new TranslatableText("bewitchment.tooltip.outer_circle", new TranslatableText("chalk.bewitchment." + recipe.outer)));
			}
			return tooltip;
		}
		return List.of();
	}
}
