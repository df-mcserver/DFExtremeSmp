package uk.co.nikodem.dfesmp.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakBlock implements Listener {
    @EventHandler
    public void BreakBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player plr = event.getPlayer();
        if (block.getType() == Material.OAK_LOG) {
            event.setDropItems(false);
        } else if (block.getType() == Material.CAMPFIRE) {
            event.setDropItems(false);
            ItemStack drop = new ItemStack(Material.STICK);
            drop.setAmount(3);

            Campfire camp = (Campfire) block.getState();

            for (int i = 0; i < camp.getSize(); i++) {
                ItemStack item = camp.getItem(i);
                if (item != null) event.getPlayer().getWorld().dropItemNaturally(block.getLocation(), item);
            }

            event.getPlayer().getWorld().dropItemNaturally(block.getLocation(), drop);
        }
    }
}
