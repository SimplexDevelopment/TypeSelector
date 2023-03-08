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
        Utility.forEach(Rabbit.Type.values(), type -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), type.name(), p -> {
                rabbit.setRabbitType(type);
                p.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
