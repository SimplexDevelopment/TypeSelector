package io.github.simplexdevelopment.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Context<S> {
    private final S state;

    protected Context(S state) {
        this.state = state;
    }

    public S getState() {
        return state;
    }

    @Contract(value = "_ -> new", pure = true)
    public static <S> @NotNull Context<S> of(S state) {
        return new Context<>(state);
    }
}
