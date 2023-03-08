package io.github.simplexdevelopment.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class Utility {
    private Utility() {
        throw new AssertionError();
    }
    public static int[] range(int start, int end) {
        int[] range = new int[end - start];
        for (int i = 0; i < range.length; i++) {
            range[i] = start + i;
        }
        return range;
    }

    public static <T> void forEach(T @NotNull [] array, Consumer<T> consumer) {
        for (T t : array) {
            consumer.accept(t);
        }
    }

    public static int getNextEmptySlot(int x, ItemStack[][] grid) {
        // initialize the grid
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int row = 0, col = 0;
        int dRow = 0, dCol = 1;

        for (int i = 0; i < 5 * 9; i++) {
            if (isCurrentSlotEmpty(grid, row, col) && i == x) {
                return row * 9 + col;
            }
            int[] nextSlot = getNextSlot(grid, row, col, dRow, dCol, dr, dc);
            row = nextSlot[0];
            col = nextSlot[1];
            dRow = nextSlot[2];
            dCol = nextSlot[3];
        }

        return -1;  // no empty slot found
    }

    public static boolean isCurrentSlotEmpty(ItemStack[][] grid, int row, int col) {
        return grid[row][col].getType().equals(Material.AIR);
    }

    public static int[] getNextSlot(ItemStack[][] grid, int row, int col, int dRow, int dCol, int[] dr, int[] dc) {
        int nextRow = row + dRow;
        int nextCol = col + dCol;
        int dir = getDirection(dRow, dCol);

        if (isOutOfBounds(nextRow, nextCol, grid) || !isCurrentSlotEmpty(grid, nextRow, nextCol)) {
            dir = (dir + 1) % 4;
        }

        return new int[]{row + dr[dir], col + dc[dir], dr[dir], dc[dir]};
    }

    public static int getDirection(int dRow, int dCol) {
        if (dRow == 0) {
            return dCol == 1 ? 1 : 3;
        } else {
            return dRow == 1 ? 2 : 0;
        }
    }

    public static boolean isOutOfBounds(int row, int col, ItemStack[][] grid) {
        return row < 0 || row >= grid.length - 1 || col < 0 || col >= grid[row].length - 1;
    }

    public static ItemStack[][] new2dItemArray(int length, int width) {
        ItemStack[][] range = new ItemStack[length][width];

        ItemStack BASE = new ItemStack(Material.AIR,  1);
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < width - 1; j++) {
                range[i][j] = BASE;
            }
        }

        return range;
    }
}
