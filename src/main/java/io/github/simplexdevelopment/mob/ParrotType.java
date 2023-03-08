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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Parrot.Variant.values(), variant -> {
            this.createInjection(x[0], variant.name(), action -> {
                parrot.setVariant(variant);
                action.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
