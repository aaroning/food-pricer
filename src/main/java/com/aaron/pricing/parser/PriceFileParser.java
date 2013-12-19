package com.aaron.pricing.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aaron.pricing.FoodItem;
import com.aaron.pricing.PricedFoodItem;
import com.aaron.pricing.Restaurant;

/**
 * A parser for the price file
 * 
 * @author Aaron Ingber
 *
 */
public class PriceFileParser {

	private static final String TOKEN = ",";
	private static final int RESTAURANT_ID_INDEX = 0;
	private static final int PRICE_INDEX = 1;
	private static final int FOOD_ITEMS_INDEX = 2;
		
	/**
	 * Parses the price file, and returns a collection of restaurants
	 * 
	 * @param fileName
	 * @return
	 */
	public static Collection<Restaurant> parse(String fileName) {
		BufferedReader br = null;
		Map<Integer, Restaurant> restaurantMap = new HashMap<Integer, Restaurant>();
		try {
			String line;
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(TOKEN);
				Integer restaurantId = Integer.parseInt(values[RESTAURANT_ID_INDEX]);
				Restaurant restaraunt;
				if (!restaurantMap.containsKey(restaurantId)) {
					restaurantMap.put(restaurantId, new Restaurant(restaurantId));
				}
				restaraunt = restaurantMap.get(restaurantId);
				float price = Float.parseFloat(values[PRICE_INDEX]);
				// find the set of food items on this line. there should only be one unless this is a value meal
				Set<FoodItem> foodItems = new HashSet<FoodItem>();
				for (int i = FOOD_ITEMS_INDEX; i < values.length; i++) {
					foodItems.add(FoodItem.getInstance(values[i].trim()));
				}
				PricedFoodItem pricedItem = new PricedFoodItem(price, foodItems);
				restaraunt.addItemToMenu(pricedItem);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File " + fileName + " not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception connecting to file " + fileName );
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return restaurantMap.values(); 
	}
}
