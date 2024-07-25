package dev.cnbetty.core.customitems;

import dev.cnbetty.core.Core;

import java.util.ArrayList;

public class CustomItemRegistry {
    private static ArrayList<CustomItem> customitems = new ArrayList<>();

    public static void register(CustomItem newitem) {
        for (CustomItem item : customitems) {
            if (item.getPlainname().equals(newitem.getPlainname())) {
                Core.logger.warning("Double registration of item " + newitem.getPlainname());
            }
        }
    }

    public static CustomItem getByName(String name) {
        for (CustomItem item : customitems) {
            if (item.getPlainname().equals(name)) return item;
        }
        Core.logger.info("no item found with plain name " + name);
        return null;
    }
}
