package com.education.boot.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestEnumFilter {

    public static void main(String[] args) {
        Map<EnumExample, MapValue> testMap = new HashMap<>();
        testMap.put(EnumExample.FIRST, new MapValue("1"));
        testMap.put(EnumExample.SECOND, new MapValue("2"));
        testMap.put(EnumExample.THIRD, new MapValue("3"));
        MapValue value = testMap.keySet().stream().filter(v -> !Set.of(EnumExample.FIRST, EnumExample.THIRD).contains(v)).map(testMap::get)
                .findFirst().orElse(new MapValue("1"));
        System.out.println(value);


    }


}
