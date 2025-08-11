package thelm.bewitchmentjei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import moriyashiine.bewitchment.common.recipe.AthameDropRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import thelm.bewitchmentjei.BewitchmentJEI;
import thelm.jeidrawables.JEIDrawables;

public class AthameDropCategory extends AbstractRecipeCategory<AthameDropRecipe> {

	public static final Text TITLE = Text.translatable("emi.category.bewitchment.athame_drops");

	public AthameDropCategory() {
		super(BewitchmentJEI.ATHAME_DROP, TITLE);
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
	public void setRecipe(IRecipeLayoutBuilder builder, AthameDropRecipe recipe, IFocusGroup focuses) {
		ItemStack input;
		Item spawnEgg = SpawnEggItem.forEntity(recipe.entity_type);
		if(spawnEgg != null) {
			input = new ItemStack(spawnEgg);
		}
		else {
			input = new ItemStack(Items.SPAWNER).setCustomName(recipe.entity_type.getName());
		}
		addItem(builder, RecipeIngredientRole.CATALYST, 1, 5, input, JEIDrawables.SLOT);
		addItem(builder, RecipeIngredientRole.OUTPUT, 57, 5, recipe.getOutput(registryAccess()), JEIDrawables.OUTPUT_SLOT);
	}

	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, AthameDropRecipe recipe, IFocusGroup focuses) {
		builder.addDrawable(JEIDrawables.RECIPE_ARROW, 24, 4);
	}
}
