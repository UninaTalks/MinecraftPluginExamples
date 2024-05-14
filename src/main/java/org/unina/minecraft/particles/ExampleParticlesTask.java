package org.unina.minecraft.particles;

import org.bukkit.Particle;

public class ExampleParticlesTask implements Runnable {
    private final ExampleParticles particles;

    public ExampleParticlesTask(ExampleParticles particles) {
        this.particles = particles;
    }

    @Override
    public void run() {
        particles.spawnParticles(Particle.VILLAGER_HAPPY);
    }
}
