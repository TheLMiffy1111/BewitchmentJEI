package thelm.bewitchmentjei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.recipe.AthameDropRecipe;
import moriyashiine.bewitchment.common.recipe.AthameStrippingRecipe;
import moriyashiine.bewitchment.common.recipe.CauldronBrewingRecipe;
import moriyashiine.bewitchment.common.recipe.CurseRecipe;
import moriyashiine.bewitchment.common.recipe.IncenseRecipe;
import moriyashiine.bewitchment.common.recipe.OilRecipe;
import moriyashiine.bewitchment.common.recipe.RitualRecipe;
import moriyashiine.bewitchment.common.registry.BWObjects;
import moriyashiine.bewitchment.common.registry.BWRecipeTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import thelm.bewitchmentjei.ingredient.subtype.ContractItemSubtypeInterpreter;
import thelm.bewitchmentjei.recipe.category.AthameDropCategory;
import thelm.bewitchmentjei.recipe.category.AthameStrippingCategory;
import thelm.bewitchmentjei.recipe.category.CauldronBrewingCategory;
import thelm.bewitchmentjei.recipe.category.CurseCategory;
import thelm.bewitchmentjei.recipe.category.IncenseCategory;
import thelm.bewitchmentjei.recipe.category.OilCategory;
import thelm.bewitchmentjei.recipe.category.RitualCategory;

public class BewitchmentJEI implements IModPlugin {

	public static final Identifier UID = new Identifier("bewitchmentjei:bewitchment");
	public static final Logger LOGGER = LogManager.getLogger();

	public static IJeiHelpers jeiHelpers;
	public static IJeiRuntime jeiRuntime;

	public static final RecipeType<AthameStrippingRecipe> ATHAME_STRIPPING = new RecipeType<>(Bewitchment.id("athame_stripping"), AthameStrippingRecipe.class);
	public static final RecipeType<AthameDropRecipe> ATHAME_DROP = new RecipeType<>(Bewitchment.id("athame_drop"), AthameDropRecipe.class);
	public static final RecipeType<RitualRecipe> RITUAL = new RecipeType<>(Bewitchment.id("rituals"), RitualRecipe.class);
	public static final RecipeType<OilRecipe> OIL = new RecipeType<>(Bewitchment.id("oil_crafting"), OilRecipe.class);
	public static final RecipeType<CauldronBrewingRecipe> CAULDRON_BREWING = new RecipeType<>(Bewitchment.id("cauldron_brewing"), CauldronBrewingRecipe.class);
	public static final RecipeType<IncenseRecipe> INCENSE = new RecipeType<>(Bewitchment.id("incenses"), IncenseRecipe.class);
	public static final RecipeType<CurseRecipe> CURSE = new RecipeType<>(Bewitchment.id("curses"), CurseRecipe.class);

	@Override
	public Identifier getPluginUid() {
		return UID;
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		registration.registerSubtypeInterpreter(BWObjects.DEMONIC_CONTRACT, new ContractItemSubtypeInterpreter());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		jeiHelpers = registration.getJeiHelpers();

		if(checkDisabled()) {
			return;
		}

		registration.addRecipeCategories(new AthameStrippingCategory());
		registration.addRecipeCategories(new AthameDropCategory());
		registration.addRecipeCategories(new RitualCategory());
		registration.addRecipeCategories(new OilCategory());
		registration.addRecipeCategories(new CauldronBrewingCategory());
		registration.addRecipeCategories(new IncenseCategory());
		registration.addRecipeCategories(new CurseCategory());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
		registration.addRecipes(ATHAME_STRIPPING, recipeManager.listAllOfType(BWRecipeTypes.ATHAME_STRIPPING_RECIPE_TYPE));
		registration.addRecipes(ATHAME_DROP, recipeManager.listAllOfType(BWRecipeTypes.ATHAME_DROP_RECIPE_TYPE));
		registration.addRecipes(RITUAL, recipeManager.listAllOfType(BWRecipeTypes.RITUAL_RECIPE_TYPE));
		registration.addRecipes(OIL, recipeManager.listAllOfType(BWRecipeTypes.OIL_RECIPE_TYPE));
		registration.addRecipes(CAULDRON_BREWING, recipeManager.listAllOfType(BWRecipeTypes.CAULDRON_BREWING_RECIPE_TYPE));
		registration.addRecipes(INCENSE, recipeManager.listAllOfType(BWRecipeTypes.INCENSE_RECIPE_TYPE));
		registration.addRecipes(CURSE, recipeManager.listAllOfType(BWRecipeTypes.CURSE_RECIPE_TYPE));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		registration.addRecipeCatalyst(BWObjects.ATHAME, ATHAME_STRIPPING, ATHAME_DROP);
		registration.addRecipeCatalyst(BWObjects.GOLDEN_CHALK, RITUAL);
		registration.addRecipeCatalyst(BWObjects.WITCH_CAULDRON, OIL, CAULDRON_BREWING);
		registration.addRecipeCatalyst(BWObjects.BRAZIER, INCENSE, CURSE);
	}

	public boolean checkDisabled() {
		if(FabricLoader.getInstance().isModLoaded("emi")) {
			LOGGER.warn("BewitchmentJEI is disabled with EMI as Bewitchment has native EMI support");
			return true;
		}
		if(FabricLoader.getInstance().isModLoaded("bewitchment-rei") && FabricLoader.getInstance().isModLoaded("rei_plugin_compatibilities")) {
			LOGGER.warn("BewitchmentJEI is disabled with Bewitchment REI and REIPC");
			return true;
		}
		return false;
	}
}
