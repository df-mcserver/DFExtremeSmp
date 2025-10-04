package uk.co.nikodem.dfesmp.Items;

public class IDGuide {
    public enum IdItemType {
        CALCITE,
        OBSIDIAN,
        VEIN,
        AUTOSMELT,
        SILK,
        ETC,
        PLANTS,
        REQUESTED,
        ACCESSORY,
        PROGRESS_SWORD,
        GUN,
        COPPER,
        TOTEMS,
        SCULK,
        UNDEFINED;
    }

    public enum IdItemClass {
        SWORD,
        AXE,
        PICKAXE,
        SHOVEL,
        HOE,
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        MAGICMIRROR,
        ENTITYBUCKET,
        WARPEDWART,
        OTHERMELEE,
        BOW,
        UNDEFINED;
    }

    public static int toID(IdItemType iitype, IdItemClass iiclass) {
        int id = 10000000;
        switch (iitype) {
            case CALCITE -> id += 1000;
            case OBSIDIAN -> id += 1100;
            case VEIN -> id += 1200;
            case AUTOSMELT -> id += 1300;
            case SILK -> id += 1400;
            case ETC -> id += 1500;
            case PLANTS -> id += 1600;
            case REQUESTED -> id += 1700;
            case ACCESSORY -> id += 1800;
            case PROGRESS_SWORD -> id += 1900;
            case GUN -> id += 2000;
            case COPPER -> id += 2100;
            case TOTEMS -> id += 2200;
            case SCULK -> id += 2300;
            case UNDEFINED -> id += 9900;
        }
        switch (iiclass) {
            case SWORD -> id += 10;
            case AXE -> id += 11;
            case PICKAXE -> id += 12;
            case SHOVEL -> id += 13;
            case HOE -> id += 14;
            case HELMET -> id += 15;
            case CHESTPLATE -> id += 16;
            case LEGGINGS -> id += 17;
            case BOOTS -> id += 18;
            case MAGICMIRROR -> id += 19;
            case ENTITYBUCKET -> id += 20;
            case WARPEDWART -> id += 21;
            case OTHERMELEE -> id += 22;
            case BOW -> id += 23;
            case UNDEFINED -> id += 99;
        }
        return id;
    }
}
