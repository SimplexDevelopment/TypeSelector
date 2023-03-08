package io.github.simplexdevelopment.util;

import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum DisplayableSlotEnum {
    ZERO(0, Material.WHITE_DYE),
    ONE(1, Material.LIGHT_GRAY_DYE),
    TWO(2, Material.GRAY_DYE),
    THREE(3, Material.BLACK_DYE),
    FOUR(4, Material.RED_DYE),
    FIVE(5, Material.ORANGE_DYE),
    SIX(6, Material.YELLOW_DYE),
    SEVEN(7, Material.GREEN_DYE),
    EIGHT(8, Material.LIME_DYE),
    NINE(9, Material.CYAN_DYE),
    TEN(10, Material.LIGHT_BLUE_DYE),
    ELEVEN(11, Material.BLUE_DYE),
    TWELVE(12, Material.PURPLE_DYE),
    THIRTEEN(13, Material.MAGENTA_DYE),
    FOURTEEN(14, Material.PINK_DYE),
    FIFTEEN(15, Material.BROWN_DYE),
    SIXTEEN(16, Material.WHITE_WOOL),
    SEVENTEEN(17, Material.LIGHT_GRAY_WOOL),
    EIGHTEEN(18, Material.GRAY_WOOL),
    NINETEEN(19, Material.BLACK_WOOL),
    TWENTY(20, Material.RED_WOOL),
    TWENTY_ONE(21, Material.ORANGE_WOOL),
    TWENTY_TWO(22, Material.YELLOW_WOOL),
    TWENTY_THREE(23, Material.GREEN_WOOL),
    TWENTY_FOUR(24, Material.LIME_WOOL),
    TWENTY_FIVE(25, Material.CYAN_WOOL),
    TWENTY_SIX(26, Material.LIGHT_BLUE_WOOL),
    TWENTY_SEVEN(27, Material.BLUE_WOOL),
    TWENTY_EIGHT(28, Material.PURPLE_WOOL),
    TWENTY_NINE(29, Material.MAGENTA_WOOL),
    THIRTY(30, Material.PINK_WOOL),
    THIRTY_ONE(31, Material.BROWN_WOOL),
    THIRTY_TWO(32, Material.WHITE_STAINED_GLASS),
    THIRTY_THREE(33, Material.LIGHT_GRAY_STAINED_GLASS),
    THIRTY_FOUR(34, Material.GRAY_STAINED_GLASS),
    THIRTY_FIVE(35, Material.BLACK_STAINED_GLASS),
    THIRTY_SIX(36, Material.RED_STAINED_GLASS),
    THIRTY_SEVEN(37, Material.ORANGE_STAINED_GLASS),
    THIRTY_EIGHT(38, Material.YELLOW_STAINED_GLASS),
    THIRTY_NINE(39, Material.GREEN_STAINED_GLASS),
    FORTY(40, Material.LIME_STAINED_GLASS),
    FORTY_ONE(41, Material.CYAN_STAINED_GLASS),
    FORTY_TWO(42, Material.LIGHT_BLUE_STAINED_GLASS),
    FORTY_THREE(43, Material.BLUE_STAINED_GLASS),
    FORTY_FOUR(44, Material.PURPLE_STAINED_GLASS);


    private final int slot;
    private final Material material;

    @Contract(pure = true)
    DisplayableSlotEnum(int slot, Material material) {
        this.slot = slot;
        this.material = material;
    }

    @Contract(pure = true)
    public int getSlot() {
        return slot;
    }

    @Contract(pure = true)
    public Material getMaterial() {
        return material;
    }

    public static @NotNull Map<Integer, Material> getSlotMaterialMap() {
        Map<Integer, Material> slotMaterialMap = new HashMap<>();
        for (DisplayableSlotEnum displayableSlotEnum : values()) {
            slotMaterialMap.put(displayableSlotEnum.getSlot(), displayableSlotEnum.getMaterial());
        }
        return slotMaterialMap;
    }
}
