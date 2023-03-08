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
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(TropicalFish.Pattern.values(), pattern -> {
            this.createInjection(x[0], pattern.name(), p -> {
                fish.setPattern(pattern);
                bodyColor.openMenu();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
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
            int[] x = {Utility.getNextEmptySlot(22)};
            Utility.forEach(DyeColor.values(), dyeColor -> {
                this.createInjection(x[0], dyeColor.name(), p -> {
                    fish.setBodyColor(dyeColor);
                    patternColor.openMenu();
                });
                x[0] = Utility.getNextEmptySlot(x[0]);
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
            int[] x = {Utility.getNextEmptySlot(22)};
            Utility.forEach(DyeColor.values(), dyeColor -> {
                this.createInjection(x[0], dyeColor.name(), p -> {
                    fish.setPatternColor(dyeColor);
                    p.closeInventory();
                });
                x[0] = Utility.getNextEmptySlot(x[0]);
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }
}
