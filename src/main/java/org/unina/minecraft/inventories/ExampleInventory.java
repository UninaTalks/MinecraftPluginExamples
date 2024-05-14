package org.unina.minecraft.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.unina.minecraft.items.ItemBuilder;

//  Tutorial su come creare GUI: https://www.spigotmc.org/wiki/creating-a-gui-inventory/
public class ExampleInventory {
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, "Partite in corso");
        for (int i = 0; i < 5; i++) {
            ItemStack item = ItemBuilder.create()
                    .material(Material.ARROW)
                    .name("§e§lPartita §6§l" + (i + 1))
                    .lore("§f§lCLICK §7Unisciti alla partita")
                    .build();
            inventory.addItem(item);
        }
        return inventory;
    }
}
