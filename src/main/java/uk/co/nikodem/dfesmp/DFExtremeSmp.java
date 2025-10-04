package uk.co.nikodem.dfesmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import uk.co.nikodem.dfesmp.Commands.GiveDF;
import uk.co.nikodem.dfesmp.Events.*;
import uk.co.nikodem.dfesmp.Recipes.VanillaRecipes;

import java.util.Objects;

public final class DFExtremeSmp extends JavaPlugin {

    private final VanillaRecipes vanillaRecipes = new VanillaRecipes(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("givedf")).setExecutor(new GiveDF());

        getServer().getPluginManager().registerEvents(new CampfireFinish(this), this);
        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new BlockInteract(), this);
        getServer().getPluginManager().registerEvents(new PlaceBlock(), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        // runs every second
        scheduler.runTaskTimer(this, PerSecond::run, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
