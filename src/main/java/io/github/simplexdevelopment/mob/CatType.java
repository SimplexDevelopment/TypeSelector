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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Cat.Type.values(), type -> {
            this.createInjection(x[0], type.name(), p -> {
                cat.setCatType(type);
                p.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
