package uk.co.nikodem.dfesmp.Items;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.Material;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFMaterial {



    public static DFMaterial[] allDFMaterials = {

    };

    private final String namedId;
    private final TextComponent displayName;
    private final Material base;
    private final int id;
    private final boolean markedForUuid;
    private final List<TextComponent> lores;
    private final ItemStack item;

    public ItemStack toItemStack() {
        return item;
    }

    public boolean isSimilar(ItemStack comparison) {

        if (comparison.getType() != base) return false;

        ItemMeta meta = comparison.getItemMeta();
        return meta.hasCustomModelData() && meta.getCustomModelData() == id;
    }

    public Material getType() {
        return base;
    }

    public boolean isMarkedForUuid() {
        return markedForUuid;
    }

    public int getCustomId() {
        return id;
    }

    public String getNamedId() {
        return this.namedId;
    }

    public DFMaterial(
            Material base,
            String namedId,
            @Nullable TextComponent Name,
            int Id,
            @Nullable List<TextComponent> lores,
            @Nullable HashMap<Enchantment, Integer> Enchantments,
            @Nullable HashMap<org.bukkit.attribute.Attribute, AttributeModifier> Attributes,
            @Nullable HashMap<String, Map.Entry<PersistentDataType, Object>> PersistentData,
            boolean markedForUuid)
    {

        this.markedForUuid = markedForUuid;
        this.namedId = namedId;
        if (Name != null) this.displayName = Name;
        else this.displayName = null;
        this.base = base;
        this.id = Id;
        this.lores = lores;

        ItemStack newItem = new ItemStack(base);
        this.item = newItem;

        ItemMeta meta = newItem.getItemMeta();
        if (meta == null) return;
        meta.setCustomModelData(id);
        if (Name != null) meta.displayName(Name);
        if (lores != null && !lores.isEmpty()) meta.lore(lores);
        if (Enchantments != null && !Enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> ench : Enchantments.entrySet()) {
                meta.addEnchant(ench.getKey(), ench.getValue(), true);
            }
        }
        if (Attributes != null && !Attributes.isEmpty()) {
            for (Map.Entry<Attribute, AttributeModifier> attribute : Attributes.entrySet()) {
                meta.addAttributeModifier(attribute.getKey(), attribute.getValue());
            }
        }
        if (PersistentData != null && !PersistentData.isEmpty()) {
            for (Map.Entry<String, Map.Entry<PersistentDataType, Object>> data : PersistentData.entrySet()) {
                String key = data.getKey();
                PersistentDataType type = data.getValue().getKey();
                var val = data.getValue().getValue();

                meta.getPersistentDataContainer().set(
                        new NamespacedKey("dfsmpplus", key),
                        type,
                        val
                );
            }
        }
        newItem.setItemMeta(meta);
    }
}
