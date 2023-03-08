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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Llama.Color.values(), color -> {
            this.createInjection(x[0], color.name(), p-> {
                llama.setColor(color);
                p.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
