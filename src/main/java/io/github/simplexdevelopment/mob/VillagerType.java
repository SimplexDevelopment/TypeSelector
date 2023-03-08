package io.github.simplexdevelopment.mob;

import io.github.simplexdevelopment.inv.AbstractGUI;
import io.github.simplexdevelopment.util.Utility;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class VillagerType extends AbstractGUI {
    private final Villager villager;
    private final Player player;
    private final VillagerProfession villagerProfession;

    public VillagerType(Villager villager, Player player) {
        super("Rabbit Type");
        this.villager = villager;
        this.player = player;
        this.villagerProfession = new VillagerProfession(villager, player);
    }

    @Override
    public void openMenu() {
        int[] x = {Utility.getNextEmptySlot(22)};
        Utility.forEach(Villager.Type.values(), type -> {
            this.createInjection(x[0], type.name(), p -> {
                villager.setVillagerType(type);
                villagerProfession.openMenu();
            });
            x[0] = Utility.getNextEmptySlot(x[0]);
        });
        this.dynamicSlotAssignment(this.getTupleList());
        this.open(player);
    }

    private final class VillagerProfession extends AbstractGUI {
        private final Villager villager;
        private final Player player;

        public VillagerProfession(Villager villager, Player player) {
            super("Body Color");
            this.villager = villager;
            this.player = player;
        }

        public void openMenu() {
            int[] x = {Utility.getNextEmptySlot(22)};
            Utility.forEach(Villager.Profession.values(), profession -> {
                this.createInjection(x[0], profession.name(), p -> {
                    villager.setProfession(profession);
                    villager.setVillagerExperience(1); // This prevents loss of profession.
                    p.closeInventory();
                });
                x[0] = Utility.getNextEmptySlot(x[0]);
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }
}