package uk.co.nikodem.dfesmp.SetBonuses;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DFArmourSetBuilder {

    private final String name;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    private String setBonusText;
    private String base;

    public DFArmourSetBuilder(String name) {
        this.name = name;
    }

    public DFArmourSetBuilder setHelmet(ItemStack i) {
        this.helmet = i;
        return this;
    }

    public DFArmourSetBuilder setHelmet(Material m) {
        this.helmet = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setChestplate(ItemStack i) {
        this.chestplate = i;
        return this;
    }

    public DFArmourSetBuilder setChestplate(Material m) {
        this.chestplate = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setLeggings(ItemStack i) {
        this.leggings = i;
        return this;
    }

    public DFArmourSetBuilder setLeggings(Material m) {
        this.leggings = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setBoots(ItemStack i) {
        this.boots = i;
        return this;
    }

    public DFArmourSetBuilder setBoots(Material m) {
        this.boots = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setBase(String name) {
        this.base = base;
        return this;
    }

    public DFArmourSetBuilder setSetBase(DFArmourSets set) {
        this.base = set.getName();
        return this;
    }

    public DFArmourSetBuilder setSetBonus(String setBonusText) {
        this.setBonusText = setBonusText;

        return this;
    }

    public DFArmourSets create() {
        return new DFArmourSets(
                this.name,
                this.helmet,
                this.chestplate,
                this.leggings,
                this.boots,
                this.base,
                this.setBonusText,
                this.setBonusText != null
        );
    }
}
