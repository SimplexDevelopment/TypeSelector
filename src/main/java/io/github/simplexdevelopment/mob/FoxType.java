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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Fox.Type.values(), type -> {
            this.createInjection(x[0], type.name(), s -> {
                fox.setFoxType(type);
                s.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
