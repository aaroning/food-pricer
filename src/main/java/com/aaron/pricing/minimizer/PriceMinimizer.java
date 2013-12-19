package com.aaron.pricing.minimizer;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.aaron.pricing.FoodItem;
import com.aaron.pricing.PricedFoodItem;
import com.aaron.pricing.Restaurant;
import com.aaron.pricing.parser.PriceFileParser;

/**
 * A price minimizer that finds the restaurant with the lowest price for the requested food
 * 
 * @author Aaron Ingber
 *
 */
public class PriceMinimizer {
	
	/**
	 * Find the restaurant with the lowest price for the requested food. This uses a brute force method, iterating through all the restaurants and finding the one that can
	 * satisfy the order at the lowest price  
	 * 
	 * @param fileName
	 * @param requestedFood
	 * @return
	 */
	public MinimizerResult minimize(String fileName, Set<FoodItem> requestedFood) {
		Collection<Restaurant> restaurants = PriceFileParser.parse(fileName);
		MinimizerResult result = new MinimizerResult();
		float price = 0;		
		for (Restaurant restaurant: restaurants) {
			if (restaurant.hasAllFoodItems(requestedFood)) {
				// this is the old method - just finding the price of the first set found on the menu containing the requested items
				//price = restaurant.priceFoodItems(requestedFood);
				price = restaurant.getCheapestPriceFromMenuContainingFoodItems(requestedFood);
				if (result.getRestaurantId() == 0 || result.getTotalPrice() > price) {
					result.setRestaurantId(restaurant.getId());
					result.setTotalPrice(price);
				}
			}
		}
		return result;
	}
	
	/**
	 * A static inner class representing a result object identifying the restaurant with the lowest price, and what the lowest price is
	 * 
	 * @author Aaron Ingber
	 *
	 */
	public static class MinimizerResult {
		private int restaurantId;
		private float totalPrice;		
		
		public int getRestaurantId() {
			return restaurantId;
		}
		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}
		public float getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(float totalPrice) {
			this.totalPrice = totalPrice;
		}

	}
}
