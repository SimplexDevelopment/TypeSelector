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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Frog.Variant.values(), type -> {
            this.createInjection(x[0], type.name(), p -> {
                frog.setVariant(type);
                p.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
