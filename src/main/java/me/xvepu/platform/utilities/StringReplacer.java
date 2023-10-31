package me.xvepu.platform.utilities;

import java.util.HashMap;
import java.util.Map;

public class StringReplacer {
    private String input;
    private Map<String, Object> replacements;

    private StringReplacer(String input) {
        this.input = input;
        this.replacements = new HashMap<>();
    }

    public static StringReplacer of(String input) {
        return new StringReplacer(input);
    }

    public StringReplacer with(Map<String, Object> map) {
        replacements.putAll(map);
        return this;
    }

    public String replace() {
        StringBuilder result = new StringBuilder(input);

        for (Map.Entry<String, Object> entry : replacements.entrySet()) {
            String placeholder = entry.getKey();
            Object replacement = entry.getValue();

            if (replacement != null) {
                int startIndex = result.indexOf(placeholder);
                int endIndex = startIndex + placeholder.length();

                while (startIndex != -1) {
                    result.replace(startIndex, endIndex, replacement.toString());
                    startIndex = result.indexOf(placeholder, endIndex);
                    endIndex = startIndex + placeholder.length();
                }
            }
        }
        return result.toString();
    }
}
