package io.github.simplexdevelopment;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TypeSelector extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new TypeListener(), this);
        Bukkit.getLogger().info("TypeSelector has been enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("TypeSelector has been disabled!");
    }
}
