package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;

public class FoxType extends AbstractGUI {
    private final Fox fox;
    private final Player player;

    public FoxType(Fox fox, Player player) {
        super("Fox Variant");
        this.fox = fox;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(Fox.Type.values(), type -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), type.name(), s -> {
                fox.setFoxType(type);
                s.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
