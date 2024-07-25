package dev.cnbetty.core.customitems;

import dev.cnbetty.core.Core;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class CustomItem {
    private String plainname;
    private Component name;
    private List<Component> lore;
    private Material base;
    private int stackcount;
    private Integer itemmodel;

    public CustomItem(String plainname, Component name, List<Component> lore, Material base, Integer itemmodel, int stackcount) {
        this.plainname = plainname;
        this.name = name;
        this.lore = lore;
        this.base = base;
        this.itemmodel = itemmodel;
        if (stackcount > 99) {
            this.stackcount = 99;
            Core.logger.warning("Maximum allowed stack size is 99!");
        } else this.stackcount = stackcount;

        CustomItemRegistry.register(this);
    }
    //TODO: move from constructor-based initialization to a builder pattern

    public void give(Player recipient, int amount) {
        if (amount > this.stackcount) {
            Core.logger.warning(amount + " is too high; Items of type " + this.plainname + " may only have a size of " + this.stackcount);
            return;
        }
        ItemStack itemStack = new ItemStack(this.base);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(this.name);
        itemMeta.lore(lore);
        itemMeta.setMaxStackSize(this.stackcount);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        recipient.getInventory().addItem(itemStack);
        Core.logger.info("Gave " + amount + " of " + this.plainname + " to player " + recipient.getName());
    }

    public Integer getItemmodel() {
        return itemmodel;
    }

    public void setItemmodel(Integer itemmodel) {
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

}
