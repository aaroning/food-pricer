package com.aaron.pricing.minimizer;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aaron.pricing.FoodItem;
import com.aaron.pricing.minimizer.PriceMinimizer.MinimizerResult;

/**
 * A test for the price minimizer
 * 
 * @author Aaron Ingber
 *
 */
public class PriceMinimizerTest {
	
	/**
	 * Test the price minimizer on data.csv
	 */
	@Test public void testPriceMinimizer1(){
		PriceMinimizer minimizer = new PriceMinimizer();
		FoodItem foodItem1 = FoodItem.getInstance("burger");
		FoodItem foodItem2 = FoodItem.getInstance("tofu_log");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		MinimizerResult result = minimizer.minimize("test-csv/data.csv", items);
		Assert.assertEquals(result.getRestaurantId(), 2);
		Assert.assertEquals(result.getTotalPrice(), 11.5F);
	}

	/**
	 * Test the price minimizer on data2.csv
	 */
	@Test public void testPriceMinimizer2(){
		PriceMinimizer minimizer = new PriceMinimizer();
		FoodItem foodItem1 = FoodItem.getInstance("chef_salad");
		FoodItem foodItem2 = FoodItem.getInstance("wine_spritzer");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		MinimizerResult result = minimizer.minimize("test-csv/data2.csv", items);
		Assert.assertEquals(result.getRestaurantId(), 0);
		Assert.assertEquals(result.getTotalPrice(), 0F);
	}
	
	/**
	 * Test the price minimizer on data3.csv
	 */
	@Test public void testPriceMinimizer3(){
		PriceMinimizer minimizer = new PriceMinimizer();
		FoodItem foodItem1 = FoodItem.getInstance("fancy_european_water");
		FoodItem foodItem2 = FoodItem.getInstance("extreme_fajita");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		MinimizerResult result = minimizer.minimize("test-csv/data3.csv", items);
		Assert.assertEquals(result.getRestaurantId(), 6);
		Assert.assertEquals(result.getTotalPrice(), 11F);
	}
	
	
	/**
	 * Test the price minimizer on data4.csv
	 */
	@Test public void testPriceMinimizer4(){
		PriceMinimizer minimizer = new PriceMinimizer();
		FoodItem foodItem1 = FoodItem.getInstance("fancy_european_water");
		FoodItem foodItem2 = FoodItem.getInstance("extreme_fajita");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		MinimizerResult result = minimizer.minimize("test-csv/data4.csv", items);
		Assert.assertEquals(result.getRestaurantId(), 6);
		Assert.assertEquals(result.getTotalPrice(), 7F);
	}
	
	
}
