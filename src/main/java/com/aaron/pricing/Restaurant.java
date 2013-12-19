package com.aaron.pricing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A representation of a restaurant with a menu of items
 * 
 * @author Aaron Ingber
 *
 */
public class Restaurant {
	private final Set<PricedFoodItem> menu = new HashSet<PricedFoodItem>();
	private final int id;
	private boolean hasValueMeals;
	
	/**
	 * Creates a new restaurant with the given id
	 * @param id
	 */
	public Restaurant(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of this restaurant
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Adds a priced food item to the menu
	 * @param item
	 */
	public void addItemToMenu(PricedFoodItem item) {
		menu.add(item);
		if (item.getItems().size() > 1) {
			hasValueMeals = true;
		}
	}
	
	/**
	 * Returns true if this restaurant has this food item on the menu
	 * 
	 * @param foodItem
	 * @return
	 */
	public boolean hasFoodItem(FoodItem foodItem) {
		return hasFoodItem(foodItem, menu);
	}
	
	
	/**
	 * Returns true if this restaurant has this food item on the priced item list
	 * 
	 * @param foodItem
	 * @return
	 */
	public boolean hasFoodItem(FoodItem foodItem, Set<PricedFoodItem> pricedItems) {
		for (PricedFoodItem item: pricedItems) {
			if (item.getItems().contains(foodItem)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if this restaurant has all the food items in this set on the menu
	 * 
	 * @param foodItem
	 * @return
	 */
	public boolean hasAllFoodItems(Set<FoodItem> items) {
		return hasAllFoodItems(items, menu);
	}
	
	/**
	 * Returns true if this restaurant has all the food items in this set in the priced item set
	 * 
	 * @param items
	 * @param pricedItems
	 * @return
	 */
	public boolean hasAllFoodItems(Set<FoodItem> items, Set<PricedFoodItem> pricedItems) {
		for (FoodItem item: items) {
			if (!hasFoodItem(item, pricedItems)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the price of this set of food items
	 * 
	 * @param requestedFood
	 * @return
	 */
	public float priceFoodItems(Set<FoodItem> requestedFood) {
		float price = 0;
		for (FoodItem foodItem: requestedFood) {
			for (PricedFoodItem item: menu) {
				if (item.getItems().contains(foodItem)) {
					price += item.getPrice();
					break;
				}
			}
		}
		return price;
	}
	
	/**
	 * Searchest all subsets of possible menu items (including value meals) to find the cheapest subset of requested food
	 * 
	 * @param requestedFood
	 * @return
	 */
	public float getCheapestPriceFromMenuContainingFoodItems(Set<FoodItem> requestedFood) {
		ArrayList<ArrayList<PricedFoodItem>> subsets = getSubsets(new ArrayList<PricedFoodItem>(menu));
		return getCheapestPriceContainingRequestedFood(subsets, requestedFood);
	}
	
	private ArrayList<ArrayList<PricedFoodItem>> getSubsets(ArrayList<PricedFoodItem> set) {
		ArrayList<ArrayList<PricedFoodItem>> subsetCollection = new ArrayList<ArrayList<PricedFoodItem>>();
		ArrayList<PricedFoodItem> reducedSet = new ArrayList<PricedFoodItem>();
		if (set.size() == 0) {
			subsetCollection.add(new ArrayList<PricedFoodItem>());
		}
		else {
			reducedSet.addAll(set);
			PricedFoodItem first = reducedSet.remove(0);
			ArrayList<ArrayList<PricedFoodItem>> subsets = getSubsets(reducedSet);
			subsetCollection.addAll(subsets);
			subsets = getSubsets(reducedSet);
			for (ArrayList<PricedFoodItem> subset : subsets) {
				subset.add(0, first);
			}
			subsetCollection.addAll(subsets);
		}
		return subsetCollection;
	}
	
	private float getCheapestPriceContainingRequestedFood(ArrayList<ArrayList<PricedFoodItem>> subsetCollection, Set<FoodItem> requestedFood) {
		float cheapestPrice = 0;
		for (ArrayList<PricedFoodItem> set: subsetCollection) {
			float currentPrice = 0;
			if (pricedFoodItemsContainsRequestedFood(set, requestedFood)) {
				for (PricedFoodItem item : set) {
					currentPrice += item.getPrice();
				}
				if (cheapestPrice == 0 || currentPrice < cheapestPrice) {
					cheapestPrice = currentPrice;
				}
			}
		}
		
		return cheapestPrice;
	}
	
	private boolean pricedFoodItemsContainsRequestedFood(ArrayList<PricedFoodItem> pricedItems, Set<FoodItem> requestedFood) {
		return hasAllFoodItems(requestedFood, new HashSet<PricedFoodItem>(pricedItems));
	}

}
