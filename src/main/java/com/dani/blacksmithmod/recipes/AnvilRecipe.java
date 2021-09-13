package com.dani.blacksmithmod.recipes;

import com.dani.blacksmithmod.common.RecipeRegister;
import com.dani.blacksmithmod.inventory.AnvilInventory;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Map;
import java.util.Set;

public class AnvilRecipe implements IRecipe<AnvilInventory> {

    static int MAX_WIDTH = 3;
    static int MAX_HEIGHT = 3;

    public static final Serializer SERIALIZER = new Serializer();

    //Recipe requirements
    private final NonNullList<Ingredient> recipeItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int recipeWidth;
    private final int recipeHeight;
    private final int experience;
    private final int temperature;

    public AnvilRecipe(ResourceLocation id, NonNullList<Ingredient> recipeItemsIn, ItemStack output,int recipeWidthIn, int recipeHeightIn,int experience, int temperature){
        this.id = id;
        this.recipeItems = recipeItemsIn;
        this.output = output;
        this.recipeWidth = recipeWidthIn;
        this.recipeHeight = recipeHeightIn;
        this.experience = experience;
        this.temperature= temperature;
    }

    //show the recipe
    @Override
    public String toString () {
        return "AnvilBlock Recipe";
    }

    public int getTemperature(){
        return this.temperature;
    }

    public int getExperience(){
        return this.experience;
    }

    @Override
    public boolean matches(AnvilInventory inv, World worldIn) {
        for (short i = 0 ; i < this.recipeHeight*this.recipeWidth; i++){
            Item item = inv.getStackInSlot(i).getItem();
            if(this.recipeItems.get(i).getMatchingStacks().length == 0){
                if(item != Items.AIR){
                    return false;
                }
            }else{
                if(item != this.recipeItems.get(i).getMatchingStacks()[0].getItem()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(AnvilInventory inv) {
        return this.getRecipeOutput();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width <= MAX_HEIGHT && height <= MAX_HEIGHT;
    }

    @Override
    public ItemStack getRecipeOutput () {
        return this.output;
    }

    @Override
    public ResourceLocation getId () {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer () {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType () {
        return RecipeRegister.ANVIL_RECIPE;
    }

    @Override
    public ItemStack getIcon () {
        return new ItemStack(Blocks.STONE);
    }

    /**
     * Returns a key json object as a Java HashMap.
     */
    private static Map<String, Ingredient> deserializeKey(JsonObject json) {
        Map<String, Ingredient> map = Maps.newHashMap();
        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put(entry.getKey(), Ingredient.deserialize(entry.getValue()));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    @VisibleForTesting
    static String[] shrink(String... toShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int i1 = 0; i1 < toShrink.length; ++i1) {
            String s = toShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (toShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[toShrink.length - l - k];

            for(int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = toShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    private static int firstNonSpace(String str) {
        int i;
        for(i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
            ;
        }

        return i;
    }

    private static int lastNonSpace(String str) {
        int i;
        for(i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
            ;
        }

        return i;
    }

    private static String[] patternFromJson(JsonArray jsonArr) {
        String[] astring = new String[jsonArr.size()];
        if (astring.length > MAX_HEIGHT) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for(int i = 0; i < astring.length; ++i) {
                String s = JSONUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
                if (s.length() > MAX_WIDTH) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            return astring;
        }
    }

    private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight) {
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");

        for(int i = 0; i < pattern.length; ++i) {
            for(int j = 0; j < pattern[i].length(); ++j) {
                String s = pattern[i].substring(j, j + 1);
                Ingredient ingredient = keys.get(s);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(j + patternWidth * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return nonnulllist;
        }
    }

    public static ItemStack deserializeItem(JsonObject json) {
        String itemResult = JSONUtils.getString(json, "item");
        Item item = Registry.ITEM.getValue(new ResourceLocation(itemResult)).orElseThrow(() -> {
            return  new JsonSyntaxException("Unknown item '" + itemResult + "'");
        });
        if (json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        } else {
            int itemResultCount = JSONUtils.getInt(json, "count", 1);
            int experience = JSONUtils.getInt(json, "experience", 1);
            return new ItemStack(item,itemResultCount);
        }
    }

    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AnvilRecipe> {

        Serializer() {
            this.setRegistryName(new ResourceLocation("blacksmithmod", "anvil"));
        }

        @Override
        public AnvilRecipe read (ResourceLocation recipeId, JsonObject json) {
            Map<String, Ingredient> key = AnvilRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));

            String[] pattern = AnvilRecipe.shrink(AnvilRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
            int X = pattern[0].length();
            int Y = pattern.length;

            NonNullList<Ingredient> ingredients = AnvilRecipe.deserializeIngredients(pattern, key, X, Y);
            ItemStack result = AnvilRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));

            int temperature = JSONUtils.getInt(json, "temperature", 1);
            int experience = JSONUtils.getInt(JSONUtils.getJsonObject(json, "result"), "experience", 1);

            return new AnvilRecipe(recipeId,ingredients,result,X,Y,experience,temperature);
        }

        @Override
        public AnvilRecipe read (ResourceLocation recipeId, PacketBuffer buffer) {
            int i = buffer.readVarInt();
            int j = buffer.readVarInt();
            int temperature = buffer.readVarInt();
            int experience = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(MAX_WIDTH*MAX_HEIGHT, Ingredient.EMPTY);
            for(int k = 0; k < nonnulllist.size(); ++k) {
                nonnulllist.set(k, Ingredient.read(buffer));
            }
            ItemStack itemstack = buffer.readItemStack();
            return new AnvilRecipe(recipeId,nonnulllist, itemstack,i,j,experience,temperature);
        }

        @Override
        public void write (PacketBuffer buffer, AnvilRecipe recipe) {
            buffer.writeVarInt(recipe.recipeWidth);
            buffer.writeVarInt(recipe.recipeHeight);
            buffer.writeVarInt(recipe.experience);
            buffer.writeVarInt(recipe.temperature);
            for(Ingredient ingredient : recipe.recipeItems) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.output);
        }
    }
}
