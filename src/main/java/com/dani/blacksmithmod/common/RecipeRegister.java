package com.dani.blacksmithmod.common;

import com.dani.blacksmithmod.recipes.AnvilRecipe;
import com.dani.blacksmithmod.recipes.RecipeTypeAnvil;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RecipeRegister {

    public static final IRecipeType<AnvilRecipe> ANVIL_RECIPE = new RecipeTypeAnvil();

    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(ANVIL_RECIPE.toString()), ANVIL_RECIPE);

        event.getRegistry().register(AnvilRecipe.SERIALIZER);

    }
}
