package uk.co.nikodem.dfesmp.SetBonuses;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public class DFArmourSets {
    // Vanilla armour sets

    public static final DFArmourSets Leather = new DFArmourSetBuilder("Leather")
            .setHelmet(Material.LEATHER_HELMET)
            .setChestplate(Material.LEATHER_CHESTPLATE)
            .setLeggings(Material.LEATHER_LEGGINGS)
            .setBoots(Material.LEATHER_BOOTS)
            .setSetBonus("Stops hunger from naturally depleting")
            .create();

    public static final DFArmourSets Chainmail = new DFArmourSetBuilder("Chainmail")
            .setHelmet(Material.CHAINMAIL_HELMET)
            .setChestplate(Material.CHAINMAIL_CHESTPLATE)
            .setLeggings(Material.CHAINMAIL_LEGGINGS)
            .setBoots(Material.CHAINMAIL_BOOTS)
            .setSetBonus("Allows you to mine faster")
            .create();

    public static final DFArmourSets Iron = new DFArmourSetBuilder("Iron")
            .setHelmet(Material.IRON_HELMET)
            .setChestplate(Material.IRON_CHESTPLATE)
            .setLeggings(Material.IRON_LEGGINGS)
            .setBoots(Material.IRON_BOOTS)
            .create();

    public static final DFArmourSets Golden = new DFArmourSetBuilder("Golden")
            .setHelmet(Material.GOLDEN_HELMET)
            .setChestplate(Material.GOLDEN_CHESTPLATE)
            .setLeggings(Material.GOLDEN_LEGGINGS)
            .setBoots(Material.GOLDEN_BOOTS)
            .setSetBonus("Allows you to move faster")
            .create();

    public static final DFArmourSets Diamond = new DFArmourSetBuilder("Diamond")
            .setHelmet(Material.DIAMOND_HELMET)
            .setChestplate(Material.DIAMOND_CHESTPLATE)
            .setLeggings(Material.DIAMOND_LEGGINGS)
            .setBoots(Material.DIAMOND_BOOTS)
            .setSetBonus("Gives you the power of the conduits")
            .create();

    public static final DFArmourSets Netherite = new DFArmourSetBuilder("Netherite")
            .setHelmet(Material.NETHERITE_HELMET)
            .setChestplate(Material.NETHERITE_CHESTPLATE)
            .setLeggings(Material.NETHERITE_LEGGINGS)
            .setBoots(Material.NETHERITE_BOOTS)
            .setSetBonus("Makes you immune to fire")
            .create();

    // Custom armour sets

    public final static DFArmourSets[] armourSets = {
            Leather,
            Chainmail,
            Iron,
            Golden,
            Diamond,
            Netherite,
    };

    public static boolean hasArmourSetEquipped(Player plr, DFArmourSets set) {
        return hasArmourSetEquipped(plr.getInventory(), set);
    }

    public static boolean hasArmourSetEquipped(PlayerInventory inv, DFArmourSets set) {
        return compareArmourPieces(inv.getHelmet(), set.getHelmet())
                && compareArmourPieces(inv.getChestplate(), set.getChestplate())
                && compareArmourPieces(inv.getLeggings(), set.getLeggings())
                && compareArmourPieces(inv.getBoots(), set.getBoots());
    }

    public static boolean compareArmourPieces(ItemStack item1, ItemStack item2) {
        if (item1 == null) return false;
        if (item2 == null) return false;

        ItemMeta im1 = item1.getItemMeta();
        ItemMeta im2 = item2.getItemMeta();

        if (im1 == null) return false;
        if (im2 == null) return false;


        if (item1.getType() == item2.getType()) {
            if (im1.hasCustomModelData() && im2.hasCustomModelData()) {
                return im1.getCustomModelData() == im2.getCustomModelData();
            } else return (!im1.hasCustomModelData() || im2.hasCustomModelData())
                    && (im1.hasCustomModelData() || !im2.hasCustomModelData());
        } else {
            return false;
        }
    }

    public static boolean hasArmourSetEquippedWithSetBonus(Player plr) {
        return hasArmourSetEquippedWithSetBonus(plr.getInventory());
    }

    public static boolean hasArmourSetEquippedWithSetBonus(PlayerInventory inv) {
        for (DFArmourSets set : DFArmourSets.armourSets) {
            if (!set.hasSetBonus()) continue;
            if (set.playerHasEquipped(inv)) {
                return true;
            }
        }
        return false;
    }

    public static DFArmourSets getArmourSetEquipped(Player plr) {
        return getArmourSetEquipped(plr.getInventory());
    }

    @Nullable
    public static DFArmourSets getArmourSetEquipped(PlayerInventory inv) {
        for (DFArmourSets set : armourSets) {
            if (hasArmourSetEquipped(inv, set)) return set;
        }
        return null;
    }

    private final String name;

    private final ItemStack helmet;
    private final ItemStack chestplate;
    private final ItemStack leggings;
    private final ItemStack boots;
    private final String base;

    private final String setBonus;
    private final boolean hasSetBonus;

    public DFArmourSets(
            String name,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            String base,
            String setBonusText,
            boolean hasSetBonus)
    {
        this.name = name;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.base = base;
        this.hasSetBonus = hasSetBonus;
        this.setBonus = setBonusText;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public ItemStack getHelmet() {
        return this.helmet;
    }

    @Nullable
    public ItemStack getChestplate() {
        return this.chestplate;
    }

    @Nullable
    public ItemStack getLeggings() {
        return this.leggings;
    }

    @Nullable
    public ItemStack getBoots() {
        return this.boots;
    }

    @Nullable
    public String getBase() {
        return this.base;
    }

    @Nullable
    public String getSetBonusText() {
        return this.setBonus;
    }

    public boolean hasSetBonus() {
        return this.hasSetBonus;
    }

    public boolean isCustom() {
        return this.getBase() != null;
    }

    public boolean playerHasEquipped(Player plr) {
        return playerHasEquipped(plr.getInventory());
    }

    public boolean playerHasEquipped(PlayerInventory inv) {
        return hasArmourSetEquipped(inv, this);
    }

    public boolean itemInSet(ItemStack item) {
        return compareArmourPieces(item, this.getHelmet())
                || compareArmourPieces(item, this.getChestplate())
                || compareArmourPieces(item, this.getLeggings())
                || compareArmourPieces(item, this.getBoots());
    }

    public String toString() {
        // this was for debugging
        String customString = getBase() == null ? "vanilla" : getBase();
        return this.getName()+" set: ("+customString+")";
    }
}
