package org.epoch.core.constant;

/**
 * Enumerable Interface.
 */
public interface Enumerable {

    static String getValueByName(Enumerable[] enums, String name) {
        if (name == null) {
            return null;
        }
        for (Enumerable obj : enums) {
            if (name.equals(obj.getName())) {
                return obj.getValue();
            }
        }
        return null;
    }

    static String getNameByValue(Enumerable[] enums, String value) {
        if (value == null) {
            return null;
        }
        for (Enumerable obj : enums) {
            if (value.equals(obj.getValue())) {
                return obj.getName();
            }
        }
        return null;
    }

    String getName();

    String getValue();
}
