package dev.cnbetty.core.customitems;

import dev.cnbetty.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;


public class CustomItem {
    private String plainname;
    private Component name;
    private List<Component> lore;
    private Material base;
    private int stackcount;
    private CustomItemModel itemmodel;

    public CustomItem(String plainname, Component name, List<Component> lore, Material base, int stackcount, CustomItemModel itemmodel) {
        this.plainname = plainname;
        this.name = name;
        this.lore = lore;
        this.base = base;
        this.stackcount = stackcount;
        this.itemmodel = itemmodel;
    }

    public static CustomItem fromYAML(String yamlstring) {
        Yaml yaml = new Yaml();
        Map<String, Object> obj = yaml.load(yamlstring);
        Core.logger.info(obj.toString());
        return null;
    }

    public CustomItemModel getItemmodel() {
        return itemmodel;
    }

    public void setItemmodel(CustomItemModel itemmodel) {
        this.itemmodel = itemmodel;
    }

    public String getPlainname() {
        return plainname;
    }

    public void setPlainname(String plainname) {
        this.plainname = plainname;
    }

    public Component getName() {
        return name;
    }

    public void setName(Component name) {
        this.name = name;
    }

    public List<Component> getLore() {
        return lore;
    }

    public void setLore(List<Component> lore) {
        this.lore = lore;
    }

    public Material getBase() {
        return base;
    }

    public void setBase(Material base) {
        this.base = base;
    }

    public int getStackcount() {
        return stackcount;
    }

    public void setStackcount(int stackcount) {
        this.stackcount = stackcount;
    }

    public String toYAML() {
        String returncomponents = "";
        for (int i = 0; i < this.lore.size(); i++) {
            returncomponents += " -" + JSONComponentSerializer.json().serialize(lore.get(i)) + "";
        }

        return plainname + ": " +
                "name: " + JSONComponentSerializer.json().serialize(name) + " " +
                "lore:" + returncomponents + " " +
                "base: " + base.getKey() + " " +
                "stackcount: " + stackcount;

    }

    public void give(Player recipient, int amount) {
        ItemStack itemStack = new ItemStack(this.base);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(this.name);
        itemMeta.lore(lore);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        recipient.getInventory().addItem(itemStack);
    }
}
