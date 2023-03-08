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
        Utility.forEach(Villager.Type.values(), type -> {
            if (this.getSelector().noMoreSlots()) return;
            this.createInjection(this.getSelector().getSlot(), type.name(), p -> {
                villager.setVillagerType(type);
                villagerProfession.openMenu();
            });
            this.getSelector().nextSlot();
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
            Utility.forEach(Villager.Profession.values(), profession -> {
                if (this.getSelector().noMoreSlots()) return;
                this.createInjection(this.getSelector().getSlot(), profession.name(), p -> {
                    villager.setProfession(profession);
                    villager.setVillagerExperience(1); // This prevents loss of profession.
                    p.closeInventory();
                });
                this.getSelector().nextSlot();
            });
            this.dynamicSlotAssignment(this.getTupleList());
            this.open(player);
        }
    }
}