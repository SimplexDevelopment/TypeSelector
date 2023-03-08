package io.github.simplexdevelopment.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Selector {
    private int slot;
    private ItemStack[][] slots;

    public Selector(int slot, int length, int width) {
        this.slot = slot;
        this.slots = Utility.new2dItemArray(length, width);
    }

    public int getSlot() {
        return slot;
    }

    public void nextSlot() {
        dropSlot();
        this.slot = Utility.getNextEmptySlot(this.slot, this.slots);
    }

    public boolean noMoreSlots() {
        return this.slot == -1;
    }

    public void dropSlot() {
        int xPos = slot / slots.length;
        int yPos = slot / slots[xPos].length;
        if (Utility.isOutOfBounds(xPos, yPos, slots) || !Utility.isCurrentSlotEmpty(slots, xPos, yPos)) {
            return;
        }
        slots[xPos][yPos] = new ItemStack(Material.BARRIER,  1); // Since we only use this for logic purposes, this can be any block other than air.
    }
}