package uk.co.nikodem.dfesmp.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemChecks {
    public static boolean isRealTool(ItemStack item) {
        return isAxe(item)
                || isPickaxe(item)
                || isShovel(item)
                || isHoe(item);
    }

    public static boolean isTool(ItemStack item) {
        return isSword(item)
                || isAxe(item)
                || isPickaxe(item)
                || isShovel(item)
                || isHoe(item);
    }

    public enum ToolLevel {
        NONE,
        WOODEN,
        STONE,
        IRON,
        GOLDEN,
        DIAMOND,
        NETHERITE,
        CREATIVE
    }

    public static boolean isLevelOrAbove(ItemStack item, ToolLevel level) {

        boolean res = false;
        switch (level) {
            case NONE -> res = true;
            case WOODEN -> {
                res = isWooden(item)
                        || isStone(item)
                        || isIron(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case STONE -> {
                res = isStone(item)
                        || isIron(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case IRON -> {
                res = isIron(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case GOLDEN -> {
                res = isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case DIAMOND -> {
                res = isDiamond(item)
                        || isNetherite(item);
            }
            case NETHERITE -> {
                res = isNetherite(item);
            }
        };

        return res;
    }

    public static boolean isWooden(ItemStack item) {
        return item.getType() == Material.WOODEN_SWORD
                || item.getType() == Material.WOODEN_AXE
                || item.getType() == Material.WOODEN_PICKAXE
                || item.getType() == Material.WOODEN_SHOVEL
                || item.getType() == Material.WOODEN_HOE;
    }

    public static boolean isStone(ItemStack item) {
        return item.getType() == Material.STONE_SWORD
                || item.getType() == Material.STONE_AXE
                || item.getType() == Material.STONE_PICKAXE
                || item.getType() == Material.STONE_SHOVEL
                || item.getType() == Material.STONE_HOE;
    }

    public static boolean isIron(ItemStack item) {
        return item.getType() == Material.IRON_SWORD
                || item.getType() == Material.IRON_AXE
                || item.getType() == Material.IRON_PICKAXE
                || item.getType() == Material.IRON_SHOVEL
                || item.getType() == Material.IRON_HOE;
    }

    public static boolean isGolden(ItemStack item) {
        return item.getType() == Material.GOLDEN_SWORD
                || item.getType() == Material.GOLDEN_AXE
                || item.getType() == Material.GOLDEN_PICKAXE
                || item.getType() == Material.GOLDEN_SHOVEL
                || item.getType() == Material.GOLDEN_HOE;
    }

    public static boolean isDiamond(ItemStack item) {
        return item.getType() == Material.DIAMOND_SWORD
                || item.getType() == Material.DIAMOND_AXE
                || item.getType() == Material.DIAMOND_PICKAXE
                || item.getType() == Material.DIAMOND_SHOVEL
                || item.getType() == Material.DIAMOND_HOE;
    }

    public static boolean isNetherite(ItemStack item) {
        return item.getType() == Material.NETHERITE_SWORD
                || item.getType() == Material.NETHERITE_AXE
                || item.getType() == Material.NETHERITE_PICKAXE
                || item.getType() == Material.NETHERITE_SHOVEL
                || item.getType() == Material.NETHERITE_HOE;
    }

    public static boolean isSword(ItemStack item) {
        return item.getType() == Material.WOODEN_SWORD
                || item.getType() == Material.STONE_SWORD
                || item.getType() == Material.IRON_SWORD
                || item.getType() == Material.GOLDEN_SWORD
                || item.getType() == Material.DIAMOND_SWORD
                || item.getType() == Material.NETHERITE_SWORD;
    }

    public static boolean isAxe(ItemStack item) {
        return item.getType() == Material.WOODEN_AXE
                || item.getType() == Material.STONE_AXE
                || item.getType() == Material.IRON_AXE
                || item.getType() == Material.GOLDEN_AXE
                || item.getType() == Material.DIAMOND_AXE
                || item.getType() == Material.NETHERITE_AXE;
    }

    public static boolean isPickaxe(ItemStack item) {
        return item.getType() == Material.WOODEN_PICKAXE
                || item.getType() == Material.STONE_PICKAXE
                || item.getType() == Material.IRON_PICKAXE
                || item.getType() == Material.GOLDEN_PICKAXE
                || item.getType() == Material.DIAMOND_PICKAXE
                || item.getType() == Material.NETHERITE_PICKAXE;
    }

    public static boolean isShovel(ItemStack item) {
        return item.getType() == Material.WOODEN_SHOVEL
                || item.getType() == Material.STONE_SHOVEL
                || item.getType() == Material.IRON_SHOVEL
                || item.getType() == Material.GOLDEN_SHOVEL
                || item.getType() == Material.DIAMOND_SHOVEL
                || item.getType() == Material.NETHERITE_SHOVEL;
    }

    public static boolean isHoe(ItemStack item) {
        return item.getType() == Material.WOODEN_HOE
                || item.getType() == Material.STONE_HOE
                || item.getType() == Material.IRON_HOE
                || item.getType() == Material.GOLDEN_HOE
                || item.getType() == Material.DIAMOND_HOE
                || item.getType() == Material.NETHERITE_HOE;
    }
}
