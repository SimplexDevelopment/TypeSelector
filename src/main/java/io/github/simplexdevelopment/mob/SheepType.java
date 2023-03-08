package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class SheepType extends AbstractGUI {
    private final Sheep sheep;
    private final Player player;

    public SheepType(Sheep sheep, Player player) {
        super("Rabbit Type");
        this.sheep = sheep;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(DyeColor.values(), color -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), color.name(), p -> {
                sheep.setColor(color);
                p.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
