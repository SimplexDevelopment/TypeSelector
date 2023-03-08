package io.github.simplexdevelopment.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

public class Utility {
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

    public static int getNextEmptySlot(int x) {
        int[][] grid = new int[5][9];  // initialize the grid

        int row = 0, col = 0;
        int dRow = 0, dCol = 1;
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        for (int i = 0; i < 5 * 9; i++) {
            if (grid[row][col] == 0) {
                if (i == x) {
                    return row * 9 + col;
                }
            }
            row += dRow;
            col += dCol;
            if (row < 0 || row >= 5 || col < 0 || col >= 9 || grid[row][col] != 0) {
                row -= dRow;
                col -= dCol;
                int dir = (dRow == 0) ? (dCol == 1 ? 1 : 3) : (dRow == 1 ? 2 : 0);
                dRow = dr[dir];
                dCol = dc[dir];
                row += dRow;
                col += dCol;
            }
        }

        return -1;  // no empty slot found
    }
}
