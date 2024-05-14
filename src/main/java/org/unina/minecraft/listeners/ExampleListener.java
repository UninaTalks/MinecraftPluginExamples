package org.unina.minecraft.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ExampleListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("§e§lBenvenuto! §fSei pronto per giocare?");
        notifyOnlinePlayers(player.getName() + " si è unito al server.", ChatColor.GREEN, player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        notifyOnlinePlayers(player.getName() + " ha abbandonato il server.", ChatColor.GOLD, player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Controlla il tipo di inventario cliccato...
        if (event.getView().getType() != InventoryType.PLAYER) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        ItemStack clicked = event.getCurrentItem();
        if (inv == null || clicked == null) return;

        // Controlla che l'item cliccato sia una spada in diamante
        if (clicked.getType() == Material.DIAMOND_SWORD) {
            // Cancella l'evento
            event.setCancelled(true);
            player.sendMessage("§7§oHai cliccato su una spada in diamante.");
        }
    }

    private void notifyOnlinePlayers(String message, ChatColor color, Player... ignored) {
        // Trasforma la lista degli utenti da ignorare in una lista con gli UUID (id) degli utenti da ignorare
        List<UUID> ignoredUUIDs = Arrays.stream(ignored).map(Player::getUniqueId).toList();
        // Crea un messaggio impostando il contenuto e il colore
        BaseComponent[] components = new ComponentBuilder().append(message).color(color).create();
        // Itera tra i giocatori online
        for (Player other : Bukkit.getOnlinePlayers())
            if (!ignoredUUIDs.contains(other.getUniqueId()))
                // Invia un messaggio in ActionBar (sopra la barra degli item)
                other.spigot().sendMessage(ChatMessageType.ACTION_BAR, components);
    }
}
