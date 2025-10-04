package uk.co.nikodem.dfesmp.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import uk.co.nikodem.dfesmp.DFExtremeSmp;

import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class CampfireFinish implements Listener {

    private final DFExtremeSmp plugin;

    public CampfireFinish(DFExtremeSmp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void CampfireFinish(BlockCookEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.CAMPFIRE) {
            // keep the cooked items on the campfire lol

            Campfire campfire = (Campfire) block.getState();

            event.setResult(new ItemStack(Material.AIR));

            event.setCancelled(true);

            for (int i = 0; i < campfire.getSize(); i++) {
                if (campfire.getItem(i) != null) {
                    if (campfire.getCookTime(i) >= campfire.getCookTimeTotal(i)) {
                        // cooked item
                        CampfireRecipe campfireRecipe = (CampfireRecipe) event.getRecipe();

                        if (campfireRecipe != null) {
                            campfire.setItem(i, campfireRecipe.getResult());
                            // hackiest solution that works :)
                            // this is gonna be a trend throughout this code isn't it
                            campfire.setCookTimeTotal(i, 999999999);
                            campfire.setCookTime(i, -999999999);
                            campfire.update();
                        }
                    }
                }
            }
        }
    }
}
