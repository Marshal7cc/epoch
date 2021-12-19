package org.epoch.starter.core.algorithm.structure;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>name:Pair</p>
 * <pre>
 *      description:A tuple of things.
 * </pre>
 *
 * @param <S> Type of the first thing.
 * @param <T> Type of the second thing.
 * @author Marshal
 * @date 2021/1/24
 */
@ToString
@EqualsAndHashCode
public final class Pair<S, T> {

    private S first;
    private T second;

    public Pair() {
    }

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new {@link Pair} for the given elements.
     *
     * @param first  must not be {@literal null}.
     * @param second must not be {@literal null}.
     * @return
     */
    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair<>(first, second);
    }

    /**
     * A collector to create a {@link Map} from a {@link Stream} of {@link Pair}s.
     *
     * @return
     */
    public static <S, T> Collector<Pair<S, T>, ?, Map<S, T>> toMap() {
        return Collectors.toMap(Pair::getFirst, Pair::getSecond);
    }

    /**
     * Returns the first element of the {@link Pair}.
     *
     * @return
     */
    public S getFirst() {
        return first;
    }

    /**
     * Returns the second element of the {@link Pair}.
     *
     * @return
     */
    public T getSecond() {
        return second;
    }
}
