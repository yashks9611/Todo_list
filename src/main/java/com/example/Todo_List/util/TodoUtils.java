package com.example.Todo_List.util;

import java.util.HashMap;
import java.util.Map;

public class TodoUtils {

	private TodoUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static Map<Integer, PriorityLevel> getPriorityMap() {
		HashMap<Integer, PriorityLevel> hashMap = new HashMap<>();
		hashMap.put(0, PriorityLevel.LOW);
		hashMap.put(1, PriorityLevel.HIGH);
		hashMap.put(2, PriorityLevel.HIGHEST);
		return hashMap;
	}
}
