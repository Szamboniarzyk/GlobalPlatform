package me.xvepu.platform.utilities.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemBuilder {
    private final List<String> lore;

    private final ItemStack item;

    private final ItemMeta meta;

    public ItemBuilder(ItemStack item) {
        this.lore = new ArrayList<>();
        this.item = item;
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material mat) {
        this(mat, 1);
    }

    public ItemBuilder(Material mat, int amount) {
        this(mat, amount, (short)0);
    }

    public ItemBuilder(Material mat, short data) {
        this(mat, 1, data);
    }

    public ItemBuilder(Material mat, int amount, short data) {
        this.lore = new ArrayList<>();
        this.item = new ItemStack(mat, amount, data);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder addLores(String... lores) {
        this.lore.addAll(Arrays.asList(lores));
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchant, int level) {
        this.meta.removeEnchant(enchant);
        this.meta.addEnchant(enchant, level, true);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String title) {
        this.meta.setDisplayName(title);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        if (!this.item.getType().name().contains("LEATHER_"))
            throw new IllegalArgumentException("Can only dye leather armor!");
        ((LeatherArmorMeta)this.meta).setColor(color);
        return this;
    }

    public ItemBuilder setGlow() {
        this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        return this;
    }

    public ItemStack build() {
        if (!this.lore.isEmpty())
            this.meta.setLore(this.lore);
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}