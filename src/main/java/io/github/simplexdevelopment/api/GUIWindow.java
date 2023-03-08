package io.github.simplexdevelopment.api;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface GUIWindow extends InventoryHolder, Listener {
    Map<UUID, GUIWindow> invByUUID = new HashMap<>();
    Map<UUID, UUID> openInventories = new HashMap<>();

    static Map<UUID, GUIWindow> getInvByUUID() {
        return invByUUID;
    }

    static Map<UUID, UUID> getOpenInvs() {
        return openInventories;
    }

    void openMenu();

    UUID getUUID();

    Map<Integer, GUIAction> getActions();

    void setItem(int slot, @NotNull ItemStack itemStack, @Nullable GUIAction action);

    void clear();

    void open(@NotNull Player p);

    default void delete() {
        Bukkit.getOnlinePlayers().forEach((p) -> {
            UUID u = openInventories.get(p.getUniqueId());
            if (u.equals(getUUID())) {
                p.closeInventory();
            }
        });
        invByUUID.remove(getUUID());
    }

    default void setItem(int slot, @NotNull ItemStack itemStack) {
        setItem(slot, itemStack, null);
    }

    default ItemStack newItem(@NotNull Material mat, String name) {
        return newItem(mat, name, "");
    }

    default ItemStack newItem(@NotNull Material mat, String name, String... lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text(name));
            ArrayList<Component> lores = new ArrayList<>();
            for (String s : lore) {
                lores.add(Component.text(s));
            }
            meta.lore(lores);
            item.setItemMeta(meta);
        }
        return item;
    }
}
