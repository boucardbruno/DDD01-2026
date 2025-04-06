package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PricingCategory {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    IGNORED(4);

    private static final Map<Integer, PricingCategory> map;

    static {
        map = Stream.of(PricingCategory.values())
                .collect(Collectors.toUnmodifiableMap(PricingCategory::getValue, category -> category));
    }

    private final int value;

    PricingCategory(int value) {
        this.value = value;
    }

    public static PricingCategory valueOf(int pageType) {
        var category = map.get(pageType);
        if (category == null) {
            throw new IllegalArgumentException("No enum constant with value " + pageType);
        }
        return category;
    }

    public int getValue() {
        return value;
    }
}
