package uk.co.nikodem.dfesmp.Events;

import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class PerSecond {
    public static void run() {
        List<World> worlds = Bukkit.getWorlds();

        for (World world : worlds) {
            Chunk[] chunks = world.getLoadedChunks();
            for (Chunk chunk : chunks) {
                BlockState[] tiles = chunk.getTileEntities();
                for (BlockState blockState : tiles) {
                    if (blockState.getBlock().getType() == Material.CAMPFIRE) {
                        org.bukkit.block.Campfire campfire = (org.bukkit.block.Campfire) blockState;
                        org.bukkit.block.data.type.Campfire campfireData = (org.bukkit.block.data.type.Campfire) blockState.getBlockData();
                        if (campfireData.isLit()) {
                            TileState state = (TileState) blockState;
                            PersistentDataContainer container = state.getPersistentDataContainer();
                            if (container != null) {
                                Integer fuelLeft = container.get(
                                        new NamespacedKey("dfsmpplus",
                                                "campfire-fuel"),
                                        PersistentDataType.INTEGER
                                );
                                if (fuelLeft != null) {
                                    if (fuelLeft > 0) {
                                        container.set(new NamespacedKey("dfsmpplus",
                                                        "campfire-fuel"),
                                                PersistentDataType.INTEGER,
                                                fuelLeft - 1);
                                        state.update();
                                    } else {
                                        world.playSound(blockState.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
                                        campfireData.setSignalFire(false);
                                        campfireData.setLit(false);
                                        campfire.setBlockData(campfireData);
                                        campfire.update();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
