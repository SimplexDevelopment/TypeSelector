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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(DyeColor.values(), color -> {
            this.createInjection(x[0], color.name(), p -> {
                sheep.setColor(color);
                p.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
