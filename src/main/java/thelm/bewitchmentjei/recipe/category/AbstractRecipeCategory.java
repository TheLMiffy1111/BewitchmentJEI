package thelm.bewitchmentjei.recipe.category;

import java.util.List;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.text.Text;

public abstract class AbstractRecipeCategory<R> implements IRecipeCategory<R> {

	public final RecipeType<R> recipeType;
	public final Text title;

	public AbstractRecipeCategory(RecipeType<R> recipeType, Text title) {
		this.recipeType = recipeType;
		this.title = title;
	}

	@Override
	public Text getTitle() {
		return title;
	}

	@Override
	public RecipeType<R> getRecipeType() {
		return recipeType;
	}

	@Override
	public abstract int getWidth();

	@Override
	public abstract int getHeight();

	@Override
	public IDrawable getIcon() {
		return null;
	}

	public DynamicRegistryManager registryAccess() {
		return MinecraftClient.getInstance().world.getRegistryManager();
	}

	public TextRenderer font() {
		return MinecraftClient.getInstance().textRenderer;
	}

	public IRecipeSlotBuilder addItem(IRecipeLayoutBuilder builder, RecipeIngredientRole ingredientRole, int x, int y, IDrawable background) {
		return builder.addSlot(ingredientRole, x, y).setBackground(background, 8 - background.getWidth() / 2, 8 - background.getHeight() / 2);
	}

	public IRecipeSlotBuilder addItem(IRecipeLayoutBuilder builder, RecipeIngredientRole ingredientRole, int x, int y, List<ItemStack> itemStacks, IDrawable background) {
		return addItem(builder, ingredientRole, x, y, background).addItemStacks(itemStacks);
	}

	public IRecipeSlotBuilder addItem(IRecipeLayoutBuilder builder, RecipeIngredientRole ingredientRole, int x, int y, Ingredient ingredient, IDrawable background) {
		return addItem(builder, ingredientRole, x, y, background).addIngredients(ingredient);
	}

	public IRecipeSlotBuilder addItem(IRecipeLayoutBuilder builder, RecipeIngredientRole ingredientRole, int x, int y, ItemStack itemStack, IDrawable background) {
		return addItem(builder, ingredientRole, x, y, background).addItemStack(itemStack);
	}
}
