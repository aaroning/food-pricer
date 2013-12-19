package com.aaron.pricing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A class representing a food or drink item. Uses a factory pattern to get new food items, so we return the same reference each team the name of the food item is parsed from input
 * 
 * @author Aaron Ingber
 *
 */
public class FoodItem {
	private final String itemName;
	// a static map of names of food items to the corresponding objects
	private final static Map<String, FoodItem> foodItemMap = new  HashMap<String, FoodItem>();

	// Private constructor - only create these using the static factory methods
	private FoodItem(String name) {
		itemName = name;
	}
	
	/**
	 * Returns the name of the food item
	 * 
	 * @return
	 */
	public String itemName() {
		return itemName;
	}
	
	/**
	 * Return an instance of a food item with this name. This is not thread safe, but since there should only be one thread accessing it from the command line app this is not a problem
	 * 
	 * @param itemName
	 * @return
	 */
	public static FoodItem getInstance(String itemName) {
		if (!foodItemMap.containsKey(itemName)) {
			foodItemMap.put(itemName, new FoodItem(itemName));
		}
		return foodItemMap.get(itemName);
	}
	
	/**
	 * Return instances of food items with these names
	 * 
	 * @param itemNames
	 * @return
	 */
	public static Set<FoodItem> asFoodItems(String[] itemNames) {
		Set<FoodItem> result = new HashSet<FoodItem>();
		for (String name : itemNames) {
			result.add(getInstance(name));
		}
		return result;
	}
	
	@Override public String toString() {
		return itemName;
	}
	
}
