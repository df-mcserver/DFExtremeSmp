package uk.co.nikodem.dfesmp.Recipes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.components.ToolComponent;
import uk.co.nikodem.dfesmp.DFExtremeSmp;

import java.util.Iterator;

import static org.bukkit.Bukkit.getServer;

public class VanillaRecipes extends DFRecipeCreator {

    public VanillaRecipes(DFExtremeSmp plugin) {
        super(plugin);
    }

    @Override
    protected void createRecipes() {
        removeAllRecipes();

        survivalEssentials();
        campfireCooking();
    }

    private void removeAllRecipes() {
        Iterator<Recipe> it = getServer().recipeIterator();
        Recipe recipe;
        while(it.hasNext()) {
            recipe = it.next();
            if (recipe != null) {
                it.remove();
            }
        }
    }

    private void campfireCooking() {
        int cookingTime = 2 * 60 * 20; // 2 minutes

        RegisterRecipe(
                createCampfireRecipe(Material.BEEF, new ItemStack(Material.COOKED_BEEF), 10F, cookingTime)
        );

        RegisterRecipe(
                createCampfireRecipe(Material.PORKCHOP, new ItemStack(Material.COOKED_PORKCHOP), 10F, cookingTime)
        );

        RegisterRecipe(
                createCampfireRecipe(Material.MUTTON, new ItemStack(Material.COOKED_MUTTON), 10F, cookingTime)
        );

        RegisterRecipe(
                createCampfireRecipe(Material.CHICKEN, new ItemStack(Material.COOKED_CHICKEN), 10F, cookingTime)
        );

        RegisterRecipe(
                createCampfireRecipe(Material.RABBIT, new ItemStack(Material.COOKED_RABBIT), 10F, cookingTime)
        );

        RegisterRecipe(
                createCampfireRecipe(Material.COD, new ItemStack(Material.COOKED_COD), 10F, cookingTime)
        );

        RegisterRecipe(
                createCampfireRecipe(Material.SALMON, new ItemStack(Material.COOKED_SALMON), 10F, cookingTime)
        );
    }

    private void survivalEssentials() {
        ItemStack unlitCampfire = new ItemStack(Material.CAMPFIRE);
        BlockDataMeta meta = (BlockDataMeta) unlitCampfire.getItemMeta();
        meta.displayName(Component.text("Unlit Campfire"));
        Campfire camp = (Campfire) meta.getBlockData(Material.CAMPFIRE);
        camp.setLit(false);
        meta.setBlockData(camp);
        unlitCampfire.setItemMeta(meta);
        RegisterRecipe(
                createShapedRecipe(unlitCampfire)
                        .shape("XX", "XX")
                        .setIngredient('X', Material.STICK)
        );
    }
}