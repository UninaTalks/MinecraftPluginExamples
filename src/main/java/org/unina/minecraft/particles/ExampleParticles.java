package org.unina.minecraft.particles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.unina.minecraft.MinecraftExamplePlugin;

public class ExampleParticles {
    // Avvia una task ripetitiva (eseguita ogni secondo) che si occupa di spawnare particelle
    public void startSpawnParticles() {
        ExampleParticlesTask task = new ExampleParticlesTask(this);
        // Visiona come funziona lo scheduler: https://www.spigotmc.org/wiki/scheduler-programming/
        Bukkit.getScheduler().runTaskTimer(MinecraftExamplePlugin.getInstance(), task, 20L, 20L);
    }

    /**
     * Crea una nuova entità
     * @param particle Tipo di particella:
     *             https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Particle.html
     */
    protected void spawnParticles(Particle particle) {
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0, 64, 0);
        // https://hub.spigotmc.org/javadocs/spigot/org/bukkit/World.html#spawnParticle(org.bukkit.Particle,org.bukkit.Location,int)
        world.spawnParticle(particle, location, 5);
    }
}
