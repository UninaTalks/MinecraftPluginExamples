package org.unina.minecraft.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private Material material;

    private String name;
    private List<String> lore;
    private int amount = 1;

    private int customModelData = -1;
    private boolean glowing = false;

    private final Map<Enchantment, Integer> enchantments = new HashMap<>();

    /**
     * Questo metodo imposta il materiale dell'item
     * @param material Il materiale
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Questo metodo imposta il materiale dell'item
     * @param material Il materiale
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder material(String material) {
        this.material = Material.valueOf(material);
        return this;
    }

    /**
     * Questo metodo imposta il nome dell'item
     * @param name Il nome
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder name(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    /**
     * Questo metodo imposta la descrizione dell'item
     * @param lore La descrizione
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder lore(String... lore) {
        this.lore = new ArrayList<>();
        for (String s : lore) {
            this.lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return this;
    }

    /**
     * Questo metodo imposta la descrizione dell'item
     * @param lore La descrizione
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Questo metodo imposta la quantità dell'item
     * @param amount La quantità
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Questo metodo imposta il custom model data (nel caso in cui vengano usate texture pack)
     * @param customModelData Il custom model data
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder customModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    /**
     * Questo metodo imposta gli incantesimi dell'item
     * @param enchantment - L'incantesimo da applicare
     * @param level - I livelli dell'incantesimo
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    /**
     * Questo metodo imposta gli incantesimi dell'item da una lista
     * @param enchantments - Gli incantesimi dell'item (enchant:level)
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder enchant(List<String> enchantments) {
        if (enchantments.isEmpty()) return this;
        for (String s : enchantments) {
            String[] split = s.split("-");
            this.enchantments.put(Enchantment.getByName(split[0]), Integer.parseInt(split[1]));
        }

        return this;
    }

    /**
     * Questo metodo imposta la possibilità di far luccicare l'item
     * @param glowing True se l'item deve luccicare
     * @return L'istanza di questo ItemBuilder
     */
    public ItemBuilder glowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    /**
     * Questo metodo usa tutte le informazioni per costruire un nuovo ItemStack
     * @return The ItemStack object
     */
    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        if(name != null)
            meta.setDisplayName(name);

        if(lore != null)
            meta.setLore(lore);

        if (customModelData != -1)
            meta.setCustomModelData(customModelData);

        if (glowing) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (!enchantments.isEmpty())
            enchantments.forEach((enchant, level) -> meta.addEnchant(enchant, level, true));

        item.setItemMeta(meta);
        return item;
    }

    /**
     * Questo metodo crea una nuova istanza dell'ItemBuilde
     * @return L'istanza di questo ItemBuilder
     */
    public static ItemBuilder create() {
        return new ItemBuilder();
    }

}
