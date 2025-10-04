package uk.co.nikodem.dfesmp.Items;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class DFMaterialBuilder {

    private final String namedId;
    private final Material base;

    private TextComponent displayName;
    private int Id;
    private boolean markedForUuid;

    private final List<TextComponent> lores = new ArrayList<>();
    private final HashMap<Enchantment, Integer> enchants = new HashMap<>();
    private final HashMap<Attribute, AttributeModifier> attributes = new HashMap<>();

    private final HashMap<String, Map.Entry<PersistentDataType, Object>> persistentData = new HashMap<>();

    public DFMaterialBuilder(Material base, String namedId) {
        this.base = base;
        this.namedId = namedId;
    }

    public DFMaterialBuilder setDisplayName(TextComponent name) {
        this.displayName = name;
        return this;
    }

    public DFMaterialBuilder setDisplayName(String name) {
        MiniMessage mm = MiniMessage.miniMessage();
        this.displayName = (TextComponent) mm.deserialize("<reset>"+name);
        return this;
    }

    public DFMaterialBuilder markForUUID() {
        this.markedForUuid = true;
        return this;
    }

    public DFMaterialBuilder addPersistentData(String key, PersistentDataType type, Object val) {
        persistentData.put(key, new AbstractMap.SimpleEntry<>(
                type,
                val
        ));
        return this;
    }

    public DFMaterialBuilder setID(int id) {
        this.Id = id;
        return this;
    }

    public DFMaterialBuilder setID(IDGuide.IdItemType type, IDGuide.IdItemClass clss) {
        this.Id = IDGuide.toID(type, clss);
        return this;
    }

    public DFMaterialBuilder addLore(String lore) {
        MiniMessage mm = MiniMessage.miniMessage();
        lores.add((TextComponent) mm.deserialize("<reset>"+lore));
        return this;
    }

    public DFMaterialBuilder addLore(TextComponent lore) {
        this.lores.add(lore);
        return this;
    }

    public DFMaterialBuilder addEnchantment(Enchantment ench) {
        this.enchants.put(ench, 1);
        return this;
    }

    public DFMaterialBuilder addEnchantment(Enchantment ench, int level) {
        this.enchants.put(ench, level);
        return this;
    }

    public DFMaterialBuilder addAttribute(Attribute attribute, AttributeModifier modifier) {
        this.attributes.put(attribute, modifier);
        return this;
    }

    public DFMaterial create() {
        return new DFMaterial(base, namedId, displayName, Id, lores, enchants, attributes, persistentData, markedForUuid);
    }
}
