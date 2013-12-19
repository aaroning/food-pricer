package com.aaron.pricing.parser;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aaron.pricing.FoodItem;
import com.aaron.pricing.Restaurant;

/**
 * A test class for the price file parser
 * 
 * @author Aaron Ingber
 *
 */
public class PriceFileParserTest {
	
	/**
	 * Test the parser 
	 */
	@Test public void testParser() {
		Collection<Restaurant> restaurants = PriceFileParser.parse("test-csv/data3.csv");
		Assert.assertEquals(restaurants.size(), 2);
		for (Restaurant restaurant: restaurants) {
			Assert.assertTrue(restaurant.getId() == 5 || restaurant.getId() == 6);
			if (restaurant.getId() == 5) {
				Assert.assertTrue(restaurant.hasFoodItem(FoodItem.getInstance("extreme_fajita")));
				Assert.assertTrue(restaurant.hasFoodItem(FoodItem.getInstance("fancy_european_water")));
			}
			if (restaurant.getId() == 6) {
				Assert.assertTrue(restaurant.hasFoodItem(FoodItem.getInstance("extreme_fajita")));
				Assert.assertTrue(restaurant.hasFoodItem(FoodItem.getInstance("fancy_european_water")));
				Assert.assertTrue(restaurant.hasFoodItem(FoodItem.getInstance("jalapeno_poppers")));
				Assert.assertTrue(restaurant.hasFoodItem(FoodItem.getInstance("extra_salsa")));
			}
			
		}
	}
}
