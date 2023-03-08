package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;

public class LlamaType extends AbstractGUI {
    private final Llama llama;
    private final Player player;

    public LlamaType(Llama llama, Player player) {
        super("Llama Type");
        this.llama = llama;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(Llama.Color.values(), color -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), color.name(), p-> {
                llama.setColor(color);
                p.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
