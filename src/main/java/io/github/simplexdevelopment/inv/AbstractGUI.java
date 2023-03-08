package io.github.simplexdevelopment.inv;

import io.github.simplexdevelopment.api.GUIAction;
import io.github.simplexdevelopment.api.GUIWindow;
import io.github.simplexdevelopment.util.DisplayableSlotEnum;
import io.github.simplexdevelopment.util.Tuple;
import io.github.simplexdevelopment.util.Utility;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractGUI implements GUIWindow {
    private final Inventory INV;
    private final Map<Integer, GUIAction> ACTIONS;
    private final UUID uuid;
    private final int[] slots = Utility.range(0, 44);
    private final Map<Integer, Material> slotMap = DisplayableSlotEnum.getSlotMaterialMap();
    private final Set<Tuple<Integer, String, GUIAction>> tupleList = new HashSet<>();

    protected AbstractGUI(String name) {
        this.uuid = UUID.randomUUID();
        this.INV = Bukkit.createInventory(null, 45, Component.text(name));
        this.ACTIONS = new HashMap<>();
        GUIWindow.getInvByUUID().put(getUUID(), this);
    }

    @Override
    public Map<Integer, GUIAction> getActions() {
        return ACTIONS;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Inventory getInventory() {
        return INV;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack itemStack, GUIAction action) {
        INV.setItem(slot, itemStack);
        if (action != null) {
            ACTIONS.put(slot, action);
        }
    }

    @Override
    public void clear() {
        INV.clear();
    }

    @Override
    public void open(@NotNull Player p) {
        p.openInventory(INV);
        openInventories.put(p.getUniqueId(), getUUID());
    }

    public void createInjection(int slot, String name, GUIAction action) {
        tupleList.add(new Tuple<>(slot, name, action));
    }

    public void dynamicSlotAssignment(Set<Tuple<Integer, String, GUIAction>> integerGUIActionTupleList) {
        Set<Integer> availableSlots = Arrays.stream(slots).boxed().collect(Collectors.toSet());
        int[] slotMapSelector = {0};
        integerGUIActionTupleList.forEach((tuple) -> {
            int slot = tuple.getA();
            GUIAction action = tuple.getC();
            if (availableSlots.contains(slot)) {
                setItem(slot, newItem(slotMap.get(slotMapSelector[0]), tuple.getB()), action);
                availableSlots.remove(slot);
                slotMapSelector[0]++;
            }
        });
    }

    public Set<Tuple<Integer, String, GUIAction>> getTupleList() {
        return tupleList;
    }
}
