package com.example.demo.linkUtils.base.type;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

/**
 * 引入一个简简单单的Triple, 用于返回值返回三个元素.
 * <p>
 * copy from Twitter Common
 */
@EqualsAndHashCode
@ToString
public class Triple<L, M, R> {

    @Nullable
    private final L left;
    @Nullable
    private final M middle;
    @Nullable
    private final R right;

    /**
     * Creates a new Triple.
     */
    public Triple(@Nullable L left, @Nullable M middle, @Nullable R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    @Nullable
    public L getLeft() {
        return left;
    }

    @Nullable
    public M getMiddle() {
        return middle;
    }

    @Nullable
    public R getRight() {
        return right;
    }

    /**
     * 根据等号左边的泛型，自动构造合适的Triple
     */
    public static <L, M, R> Triple<L, M, R> of(@Nullable L left, @Nullable M middle, @Nullable R right) {
        return new Triple<>(left, middle, right);
    }
}
