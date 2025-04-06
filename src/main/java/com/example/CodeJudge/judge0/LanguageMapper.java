package com.example.CodeJudge.judge0;

import java.util.Map;

public class LanguageMapper {
    private static final Map<String, String> map = Map.of(
            "java", "62",
            "python", "71",
            "cpp", "54"
    );

    public static String getLanguageId(String lang) {
        return map.getOrDefault(lang.toLowerCase(), "62"); // default to Java
    }
}
