package com.aaron.pricing;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * A test class for FoodItem
 * 
 * @author Aaron Ingber
 *
 */
public class FoodItemTest {
	
	/**
	 * Test the getInstance method 
	 */
	@Test public void testGetInstance() {
		FoodItem item = FoodItem.getInstance("test instance");
		Assert.assertEquals(item.itemName(), "test instance");
		FoodItem item2 = FoodItem.getInstance("test instance");
		Assert.assertTrue(item == item2); // make sure this is the same reference
	}
	
	/**
	 * Test the asFoodItems method
	 */
	@Test public void testAsFoodItems() {
		Set<FoodItem> foodItems = FoodItem.asFoodItems(new String[]{"instance 1", "instance 2"});
		Assert.assertTrue(foodItems.size() == 2);
		FoodItem item1 = (FoodItem) foodItems.toArray()[0];
		FoodItem item2 = (FoodItem) foodItems.toArray()[1];
		Assert.assertTrue(item1.itemName().equals("instance 1") || item1.itemName().equals("instance 2"));
		Assert.assertTrue(item2.itemName().equals("instance 1") || item2.itemName().equals("instance 2"));
	}
}
