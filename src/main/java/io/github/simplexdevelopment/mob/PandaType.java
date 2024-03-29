package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;

public class PandaType extends AbstractGUI {
    private final Panda panda;
    private final Player player;
    private final PandaDiminutive diminutive;

    public PandaType(Panda panda, Player player) {
        super("Panda");
        this.panda = panda;
        this.player = player;
        this.diminutive = new PandaDiminutive(panda, player);
    }

    @Override
    public void openMenu() {
        Utility.forEach(Panda.Gene.values(), gene -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), gene.name(), p -> {
                panda.setMainGene(gene);
                diminutive.openMenu();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }
    private static final class PandaDiminutive extends AbstractGUI {
        private final Panda panda;
        private final Player player;

        public PandaDiminutive(Panda panda, Player player) {
            super("Panda Diminutive");
            this.panda = panda;
            this.player = player;
        }

        @Override
        public void openMenu() {
            Utility.forEach(Panda.Gene.values(), gene -> {
                if (this.getSelector().noMoreSlots()) return;
                this.createInjection(this.getSelector().getSlot(), gene.name(), p -> {
                    panda.setHiddenGene(gene);
                    p.closeInventory();
                });
                this.getSelector().nextSlot();
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }
}
