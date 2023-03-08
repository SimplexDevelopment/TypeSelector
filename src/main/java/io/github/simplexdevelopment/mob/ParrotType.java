package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;

public class ParrotType extends AbstractGUI {
    private final Parrot parrot;
    private final Player player;

    public ParrotType(Parrot parrot, Player player) {
        super("Parrot Type");
        this.parrot = parrot;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(Parrot.Variant.values(), variant -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), variant.name(), action -> {
                parrot.setVariant(variant);
                action.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
