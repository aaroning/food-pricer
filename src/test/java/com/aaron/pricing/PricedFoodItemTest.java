package com.aaron.pricing;

import java.util.HashSet;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * A test class for creating a priced food item
 * 
 * @author user
 *
 */
public class PricedFoodItemTest {
	/**
	 * Test the PricedFoodItem constructor
	 */
	@Test public void testCreatePricedItem() {
		FoodItem foodItem1 = FoodItem.getInstance("test instance 1");
		FoodItem foodItem2 = FoodItem.getInstance("test instance 2");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		PricedFoodItem pricedItem = new PricedFoodItem(10.64F, items);
		Assert.assertEquals(pricedItem.getPrice(), 10.64F);
		Assert.assertEquals(pricedItem.getItems(), items);
	}
}
