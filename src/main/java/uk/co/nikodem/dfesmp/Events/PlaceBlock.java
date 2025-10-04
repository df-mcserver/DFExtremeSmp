package uk.co.nikodem.dfesmp.Events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataType;

public class PlaceBlock implements Listener {
    @EventHandler
    public void PlaceBlock(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        if (block.getType() == Material.CAMPFIRE) {
            TileState state = (TileState) block.getState();
            state.getPersistentDataContainer().set(
                    new NamespacedKey("dfsmpplus",
                            "campfire-fuel"),
                    PersistentDataType.INTEGER,
                    50
            );
            state.update();
        }
    }
}
