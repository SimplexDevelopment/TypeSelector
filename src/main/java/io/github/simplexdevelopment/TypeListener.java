package io.github.simplexdevelopment;

import io.github.simplexdevelopment.api.GUIAction;
import io.github.simplexdevelopment.api.GUIWindow;
import io.github.simplexdevelopment.mob.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TypeListener implements Listener {
    @EventHandler
    public void interact(@NotNull PlayerInteractEntityEvent e) {
        if (!e.getPlayer().hasPermission("selector.menu")) {
            return;
        }
        //
        final Entity ent = e.getRightClicked();
        final EntityType type = ent.getType();
        final Player p = e.getPlayer();
        //
        if (p.isSneaking()) {
            switch (type) {
                case AXOLOTL -> {
                    Axolotl axolotl = (Axolotl) ent;
                    //
                    new AxolotlType(axolotl, p).openMenu();
                }
                case CAT -> {
                    Cat cat = (Cat) ent;
                    //
                    new CatType(cat, p).openMenu();
                }
                case FOX -> {
                    Fox fox = (Fox) ent;
                    //
                    new FoxType(fox, p);
                }
                case FROG -> {
                    Frog frog = (Frog) ent;
                    //
                    new FrogType(frog, p).openMenu();
                }
                case HORSE -> {
                    Horse horse = (Horse) ent;
                    //
                    new HorseType(horse, p).openMenu();
                }
                case LLAMA -> {
                    Llama llama = (Llama) ent;
                    //
                    new LlamaType(llama, p).openMenu();
                }
                case PANDA -> {
                    Panda panda = (Panda) ent;
                    //
                    new PandaType(panda, p).openMenu();
                }
                case PARROT -> {
                    Parrot parrot = (Parrot) ent;
                    //
                    new ParrotType(parrot, p).openMenu();
                }
                case RABBIT -> {
                    Rabbit rabbit = (Rabbit) ent;
                    //
                    new RabbitType(rabbit, p).openMenu();
                }
                case SHEEP -> {
                    Sheep sheep = (Sheep) ent;
                    //
                    new SheepType(sheep, p).openMenu();
                }
                case TROPICAL_FISH -> {
                    TropicalFish fish = (TropicalFish) ent;
                    //
                    new TropicalFishType(fish, p).openMenu();
                }
                case VILLAGER -> {
                    Villager villager = (Villager) ent;
                    //
                    new VillagerType(villager, p).openMenu();
                }
                default -> {
                    // We don't need anything here, because we don't want to do anything if it doesn't have a variant.
                }
            }
        }
    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) { return; }
        //
        UUID pUUID = player.getUniqueId();
        UUID invUUID = GUIWindow.getOpenInvs().get(pUUID);
        if (invUUID != null) {
            e.setCancelled(true);
            GUIWindow menu = GUIWindow.getInvByUUID().get(invUUID);
            GUIAction action = menu.getActions().get(e.getSlot());
            if (action != null) {
                action.onClick(player);
            }
        }
    }

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        UUID pUUID = player.getUniqueId();
        GUIWindow.getOpenInvs().remove(pUUID);
    }
}
