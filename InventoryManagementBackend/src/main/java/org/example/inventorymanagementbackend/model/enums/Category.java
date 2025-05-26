package org.example.inventorymanagementbackend.model.enums;

import lombok.Data;

import java.util.Arrays;

public enum Category {
    FOOD("Food"),
    HOUSEHOLD("Household"),
    PERSONAL_CARE("Personal Care"),
    SPORTS("Sports"),
    PET_SUPPLIES("Pet supplies");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromDisplayName(String displayName) {
        return Arrays.stream(Category.values())
                .filter(c -> c.displayName.equalsIgnoreCase(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid category: " + displayName));
    }
}
