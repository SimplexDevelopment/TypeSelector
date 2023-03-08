package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;

public class CatType extends AbstractGUI {
    private final Cat cat;
    private final Player player;

    public CatType(Cat cat, Player player) {
        super("Cat Type");
        this.cat = cat;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(Cat.Type.values(), type -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), type.name(), p -> {
                cat.setCatType(type);
                p.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
