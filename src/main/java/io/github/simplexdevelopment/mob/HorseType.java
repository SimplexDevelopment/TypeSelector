package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class HorseType extends AbstractGUI {
    private final Horse horse;
    private final Player player;
    private final HorseStyle horseStyle;

    public HorseType(Horse horse, Player player) {
        super("Horse Type");
        this.horse = horse;
        this.player = player;
        this.horseStyle = new HorseStyle(horse, player);
    }

    @Override
    public void openMenu() {
        Utility.forEach(Horse.Color.values(), color -> {
            if (this.getSelector().noMoreSlots()) {
                return;
            }
            this.createInjection(this.getSelector().getSlot(), color.name(), p -> {
                horse.setColor(color);
                horseStyle.openMenu();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);

    }

    private final class HorseStyle extends AbstractGUI {
        private final Horse horse;
        private final Player player;

        public HorseStyle(Horse horse, Player player) {
            super("Horse Style");
            this.horse = horse;
            this.player = player;
        }

        @Override
        public void openMenu() {
            Utility.forEach(Horse.Style.values(), style -> {
                if (this.getSelector().noMoreSlots()) return;
                this.createInjection(this.getSelector().getSlot(), style.name(), p -> {
                    horse.setStyle(style);
                    p.closeInventory();
                });
                this.getSelector().nextSlot();
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }
}
