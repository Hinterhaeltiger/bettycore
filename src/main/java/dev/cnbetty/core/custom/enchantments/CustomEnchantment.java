package dev.cnbetty.core.custom.enchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;

public abstract class CustomEnchantment extends Enchantment {

    public static List<Enchantment> enchantments = new ArrayList<>();

    String name;
    int maxLevel;
    int minLevel;
    EnchantmentTarget enchantmentTarget;
    List<Enchantment> conflicts;
    String translationKey;
    NamespacedKey namespacedKey;

    public CustomEnchantment(String name, int maxLevel, int minLevel, EnchantmentTarget enchantmentTarget, @Nullable List<Enchantment> conflicts, String translationKey, NamespacedKey namespacedKey) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
        this.conflicts = conflicts;
        this.translationKey = translationKey;
        this.namespacedKey = namespacedKey;
    }
/*
    public static CustomEnchantment register(CustomEnchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        enchantments.add(enchantment);
        return enchantment;
    }
*/
    public static void unregister(Enchantment enchantment) {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            byKey.remove(enchantment.getKey());
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            byName.remove(enchantment.getName());
        } catch (Exception ignored) {
        }
    }

    public static void unregister() {
        for (Enchantment enchantment : enchantments) {
            unregister(enchantment);
        }
    }

    public static Enchantment getEnchantment(Enchantment enchantment) {
        return Enchantment.getByKey(enchantment.getKey());
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return minLevel;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return enchantmentTarget;
    }

    @Override
    public boolean isTreasure() {
        return false;
    } //Not used

    @Override
    public boolean isCursed() {
        return false;
    } //Not used

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        if (conflicts == null) {
            return false;
        }
        return conflicts.contains(other);
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return false;
    } //Not used

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text(name + " " + switch (level) {
            case 0 -> "";
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> level;
        });
    }

    @Override
    public boolean isTradeable() {
        return false;
    } //Not used

    @Override
    public boolean isDiscoverable() {
        return false;
    } //Not used

    @Override
    public int getMinModifiedCost(int level) {
        return 0;
    } //Not used

    @Override
    public int getMaxModifiedCost(int level) {
        return 0;
    } //Not used

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return EnchantmentRarity.COMMON;
    } //Not used

    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    } //Not used

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return new HashSet<>();
    } //Not used

    @Override
    public @NotNull String translationKey() {
        return translationKey;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return namespacedKey;
    }
}
