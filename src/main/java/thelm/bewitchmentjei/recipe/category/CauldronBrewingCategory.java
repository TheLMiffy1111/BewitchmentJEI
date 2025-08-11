package thelm.bewitchmentjei.recipe.category;

import java.util.List;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.CauldronBrewingRecipe;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class CauldronBrewingCategory extends AbstractRecipeCategory<CauldronBrewingRecipe> {

	public static final Text TITLE = Text.translatable("rei.bewitchment.cauldron_brewing");

	public CauldronBrewingCategory() {
		super(BewitchmentJEI.CAULDRON_BREWING, TITLE);
	}

	@Override
	public int getWidth() {
		return 78;
	}

	@Override
	public int getHeight() {
		return 26;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CauldronBrewingRecipe recipe, IFocusGroup focuses) {
		addItem(builder, RecipeIngredientRole.INPUT, 1, 5, recipe.input, JEIDrawables.SLOT);
		List<StatusEffectInstance> effects = List.of(new StatusEffectInstance(recipe.output, recipe.time));
		ItemStack potion = PotionUtil.setCustomPotionEffects(new ItemStack(Items.POTION), effects);
		potion.getOrCreateNbt().putInt("CustomPotionColor", PotionUtil.getColor(effects));
		potion.getOrCreateNbt().putBoolean("BewitchmentBrew", true);
		addItem(builder, RecipeIngredientRole.OUTPUT, 57, 5, potion, JEIDrawables.OUTPUT_SLOT);
	}

	@Override
	public void draw(CauldronBrewingRecipe recipe, IRecipeSlotsView recipeSlotsView, MatrixStack poseStack, double mouseX, double mouseY) {
		JEIDrawables.RECIPE_ARROW.draw(poseStack, 24, 4);
	}
}
