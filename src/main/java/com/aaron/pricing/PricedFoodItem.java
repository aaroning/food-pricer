package com.aaron.pricing;

import java.math.BigDecimal;
import java.util.Set;

/**
 * A representation of a line item on a restaurant menu (either a single order of food or a value meal)
 * 
 * @author Aaron Ingber
 *
 */
public class PricedFoodItem {
	private final float price;
	//set contains multiple elements if this a value meal -- assumes no duplicates in meal (otherwise we couldn't use a set)
	private final Set<FoodItem> items; 
	
	/**
	 * Create a new line item with the given price, for the food item (or items, in a value meal)
	 * 
	 * @param price
	 * @param items
	 */
	public PricedFoodItem(float price, Set<FoodItem> items) {
		this.price = price;
		this.items = items;
	}

	/**
	 * Return the price of this line item
	 * 
	 * @return
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Return all the food items in this line item. The set has multiple items if this is a value meal
	 * @return
	 */
	public Set<FoodItem> getItems() {
		return items;
	}
	
	@Override public String toString() {
		String items = "Items: " + getItems().toString();
		String price = " Price: " + getPrice();
		return items + price;
	}
}
