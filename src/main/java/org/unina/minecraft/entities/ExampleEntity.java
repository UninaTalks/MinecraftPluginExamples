package org.unina.minecraft.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class ExampleEntity {
    /**
     * Crea una nuova entità
     * @param type Tipo di entità:
     *             https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/EntityType.html
     * @return Ritorna la nuova entità
     */
    public Entity createZombie(EntityType type) {
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0, 64, 0);
        Entity entity = world.spawnEntity(location, EntityType.ZOMBIE);
        setEntityDetails(entity);
        return entity;
    }

    /**
     * Imposta i comportamenti e le caratteristiche di un'entità
     * @param entity Entità da modificare
     */
    private void setEntityDetails(Entity entity) {
        // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/Entity.html
        entity.setCustomName("Gianni");
        entity.setCustomNameVisible(true);
        entity.setSilent(true);
        if (entity instanceof LivingEntity livingEntity) {
            // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/LivingEntity.html
            livingEntity.setAI(false);
            livingEntity.setCanPickupItems(false);
        }
    }
}
