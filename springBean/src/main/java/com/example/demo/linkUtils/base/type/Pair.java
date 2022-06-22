package com.example.demo.linkUtils.base.type;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

/**
 * 引入一个简简单单的Pair, 用于返回值返回两个元素.
 * <p>
 * copy from Twitter Common
 */
@EqualsAndHashCode
@ToString
public class Pair<L, R> {

    @Nullable
    private final L left;
    @Nullable
    private final R right;

    /**
     * Creates a new pair.
     */
    public Pair(@Nullable L left, @Nullable R right) {
        this.left = left;
        this.right = right;
    }

    @Nullable
    public L getLeft() {
        return left;
    }

    @Nullable
    public R getRight() {
        return right;
    }


    /**
     * 根据等号左边的泛型，自动构造合适的Pair
     */
    public static <L, R> Pair<L, R> of(@Nullable L left, @Nullable R right) {
        return new Pair<>(left, right);
    }
}
