package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.TropicalFish;

public class TropicalFishType extends AbstractGUI {
    private final TropicalFish fish;
    private final Player player;
    private final FishBodyColor bodyColor;
    private final FishPatternColor patternColor;

    public TropicalFishType(TropicalFish fish, Player player) {
        super("Tropical Fish");
        this.fish = fish;
        this.player = player;
        this.bodyColor = new FishBodyColor(fish, player);
        this.patternColor = new FishPatternColor(fish, player);
    }

    @Override
    public void openMenu() {
        Utility.forEach(TropicalFish.Pattern.values(), pattern -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), pattern.name(), p -> {
                fish.setPattern(pattern);
                bodyColor.openMenu();
            });
            this.getSelector().nextSlot();
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }

    private final class FishBodyColor extends AbstractGUI {
        private final TropicalFish fish;
        private final Player player;

        public FishBodyColor(TropicalFish fish, Player player) {
            super("Body Color");
            this.fish = fish;
            this.player = player;
        }

        public void openMenu() {
            Utility.forEach(DyeColor.values(), dyeColor -> {
                if (this.getSelector().noMoreSlots()) return;
                this.createInjection(this.getSelector().getSlot(), dyeColor.name(), p -> {
                    fish.setBodyColor(dyeColor);
                    patternColor.openMenu();
                });
                this.getSelector().nextSlot();
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }

    private final class FishPatternColor extends AbstractGUI {
        private final TropicalFish fish;
        private final Player player;

        public FishPatternColor(TropicalFish fish, Player player) {
            super("Body Color");
            this.fish = fish;
            this.player = player;
        }

        public void openMenu() {
            Utility.forEach(DyeColor.values(), dyeColor -> {
                if (this.getSelector().noMoreSlots()) return;
                this.createInjection(this.getSelector().getSlot(), dyeColor.name(), p -> {
                    fish.setPatternColor(dyeColor);
                    p.closeInventory();
                });
                this.getSelector().nextSlot();
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }
}
