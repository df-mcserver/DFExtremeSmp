package uk.co.nikodem.dfesmp.Events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class BlockInteract implements Listener {
    public HashMap<ItemStack, Integer> fuel = new HashMap<>();

    public BlockInteract() {
        fuel.put(new ItemStack(Material.STICK), 3);
        fuel.put(new ItemStack(Material.BAMBOO), 1);

        fuel.put(new ItemStack(Material.BAMBOO_BLOCK), 15);

        fuel.put(new ItemStack(Material.OAK_PLANKS), 15);
        fuel.put(new ItemStack(Material.ACACIA_PLANKS), 15);
        fuel.put(new ItemStack(Material.BAMBOO_PLANKS), 15);
        fuel.put(new ItemStack(Material.BIRCH_PLANKS), 15);
        fuel.put(new ItemStack(Material.CHERRY_PLANKS), 15);
        fuel.put(new ItemStack(Material.CRIMSON_PLANKS), 15);
        fuel.put(new ItemStack(Material.WARPED_PLANKS), 15);
        fuel.put(new ItemStack(Material.DARK_OAK_PLANKS), 15);
        fuel.put(new ItemStack(Material.JUNGLE_PLANKS), 15);
        fuel.put(new ItemStack(Material.SPRUCE_PLANKS), 15);
        fuel.put(new ItemStack(Material.MANGROVE_PLANKS), 15);

        fuel.put(new ItemStack(Material.STRIPPED_OAK_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_ACACIA_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_BIRCH_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_CHERRY_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_CRIMSON_STEM), 18);
        fuel.put(new ItemStack(Material.STRIPPED_WARPED_STEM), 18);
        fuel.put(new ItemStack(Material.STRIPPED_DARK_OAK_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_JUNGLE_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_SPRUCE_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_MANGROVE_LOG), 18);
        fuel.put(new ItemStack(Material.STRIPPED_OAK_LOG), 18);

        fuel.put(new ItemStack(Material.ACACIA_LOG), 20);
        fuel.put(new ItemStack(Material.BIRCH_LOG), 20);
        fuel.put(new ItemStack(Material.CHERRY_LOG), 20);
        fuel.put(new ItemStack(Material.CRIMSON_STEM), 20);
        fuel.put(new ItemStack(Material.WARPED_STEM), 20);
        fuel.put(new ItemStack(Material.DARK_OAK_LOG), 20);
        fuel.put(new ItemStack(Material.JUNGLE_LOG), 20);
        fuel.put(new ItemStack(Material.SPRUCE_LOG), 20);
        fuel.put(new ItemStack(Material.MANGROVE_LOG), 20);
        fuel.put(new ItemStack(Material.OAK_LOG), 20);

        fuel.put(new ItemStack(Material.COAL), 30);
        fuel.put(new ItemStack(Material.CHARCOAL), 35);
        fuel.put(new ItemStack(Material.COAL_BLOCK), 30*90);
    }

    @EventHandler
    public void BlockInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player plr = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (block.getType() == Material.CAMPFIRE) {
                if (plr.getInventory().getItemInMainHand().getType() == Material.AIR) {
                    Campfire campfire = (Campfire) block.getState();

                    int lastI = 0;

                    for (int i = 0; i < campfire.getSize(); i++) {
                        if (campfire.getItem(i) != null) lastI = i;
                    }

                    ItemStack item = campfire.getItem(lastI);
                    if (item != null) {

                        campfire.startCooking(lastI);
                        campfire.setItem(lastI, null);
                        campfire.update();

                        plr.getInventory().setItemInMainHand(item);
                    }
                } else {
                    org.bukkit.block.data.type.Campfire campfireData = (org.bukkit.block.data.type.Campfire) block.getBlockData();
                    Campfire campfire = (Campfire) block.getState();
                    if (campfireData.isLit()) {
                        for (Map.Entry<ItemStack, Integer> item : fuel.entrySet()) {
                            if (plr.getInventory().getItemInMainHand().isSimilar(item.getKey())) {
                                event.setCancelled(true);
                                // is fuel

                                NamespacedKey key = new NamespacedKey("dfsmpplus",
                                        "campfire-fuel");

                                Integer fuelLeft = campfire.getPersistentDataContainer().get(
                                        key,
                                        PersistentDataType.INTEGER
                                );

                                if (fuelLeft != null) {
                                    fuelLeft += item.getValue();

                                    plr.getInventory().getItemInMainHand().setAmount(plr.getInventory().getItemInMainHand().getAmount()-1);

                                    campfire.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, fuelLeft);
                                    campfire.setBlockData(campfireData);
                                    campfire.update();
                                    plr.getWorld().playSound(campfire.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1F, 1F);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
