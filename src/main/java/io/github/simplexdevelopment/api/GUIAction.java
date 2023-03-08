package io.github.simplexdevelopment.api;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface GUIAction {
    void onClick(Player player);
}
