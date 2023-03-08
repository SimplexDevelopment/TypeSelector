package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Axolotl.Variant.values(), variant -> {
            this.createInjection(x[0], variant.name(), action -> {
                axolotl.setVariant(variant);
                action.closeInventory();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
}
