package org.unina.minecraft.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Classe di esempio di un evento non presente nella libreria di Spigot.
 * Puoi chiamare questo evento quando vengono eseguite determinate dinamiche
 * all'interno del tuo plugin. Inoltre puoi registrare un event listener
 * che ascolta questo tipo di eventi.
 * Per chiamare l'evento usa:
 * Bukkit.getPluginManager().callEvent(new ExampleCustomEvent(player, message));
 */
public class ExampleCustomEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final String message;
    private boolean cancelled = false;

    public ExampleCustomEvent(Player player, String message) {
        super(!Bukkit.isPrimaryThread());
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
