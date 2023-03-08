package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Player;

public class FrogType extends AbstractGUI {
    private final Frog frog;
    private final Player player;

    public FrogType(Frog frog, Player player) {
        super("Frog Type");
        this.frog = frog;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(Frog.Variant.values(), type -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), type.name(), p -> {
                frog.setVariant(type);
                p.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
