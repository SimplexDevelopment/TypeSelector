package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;

public class RabbitType extends AbstractGUI {
    private final Rabbit rabbit;
    private final Player player;

    public RabbitType(Rabbit rabbit, Player player) {
        super("Rabbit Type");
        this.rabbit = rabbit;
        this.player = player;
    }

    @Override
    public void openMenu() {
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Rabbit.Type.values(), type -> {
            this.createInjection(x[0], type.name(), p -> {
                rabbit.setRabbitType(type);
                p.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
