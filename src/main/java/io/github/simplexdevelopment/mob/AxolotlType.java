package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Selector;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Player;

public class AxolotlType extends AbstractGUI {
    private final Axolotl axolotl;
    private final Player player;

    public AxolotlType(Axolotl axolotl, Player player) {
        super("Axolotl Type");
        this.axolotl = axolotl;
        this.player = player;
    }

    @Override
    public void openMenu() {
        Utility.forEach(Axolotl.Variant.values(), variant -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), variant.name(), action -> {
                axolotl.setVariant(variant);
                action.closeInventory();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
