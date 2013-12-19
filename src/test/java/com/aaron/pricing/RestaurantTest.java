package com.aaron.pricing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * A test class for a restaraunt object
 * 
 * @author Aaron Ingber
 *
 */
public class RestaurantTest {
	/**
	 * Tests the public methods of the restaurant object
	 */
	@Test public void testRestaraunt() {
		Restaurant restaraunt = new Restaurant(66);
		Assert.assertEquals(restaraunt.getId(), 66);
		restaraunt.addItemToMenu(createSingleItem());
		restaraunt.addItemToMenu(createValueMeal());
		FoodItem foodItem1 = FoodItem.getInstance("test instance 1");
		FoodItem foodItem2 = FoodItem.getInstance("test instance 2");
		FoodItem foodItem3 = FoodItem.getInstance("test instance 3");
		FoodItem foodItem4 = FoodItem.getInstance("test instance 4");
		Assert.assertTrue(restaraunt.hasFoodItem(foodItem1));
		Assert.assertTrue(restaraunt.hasFoodItem(foodItem2));
		Assert.assertTrue(restaraunt.hasFoodItem(foodItem3));
		Assert.assertFalse(restaraunt.hasFoodItem(foodItem4));
		Set<FoodItem> items1 = new HashSet<FoodItem>();
		Set<FoodItem> items2 = new HashSet<FoodItem>();
		items1.add(foodItem1);
		items1.add(foodItem3);
		items2.add(foodItem2);
		items2.add(foodItem4);
		Assert.assertTrue(restaraunt.hasAllFoodItems(items1));
		Assert.assertFalse(restaraunt.hasAllFoodItems(items2));
		Assert.assertEquals(restaraunt.priceFoodItems(items1),15.54F);
	}
	
	/**
	 * Test getting the cheapest price from the subset of menu items containing requested items
	 * 
	 */
	@Test public void testGetCheapestPriceFromSubsets() {
		Restaurant restaraunt = new Restaurant(66);
		restaraunt.addItemToMenu(createSingleItem());
		restaraunt.addItemToMenu(createSingleItem2());
		restaraunt.addItemToMenu(createValueMeal());
		restaraunt.addItemToMenu(createValueMeal2());
		restaraunt.addItemToMenu(createValueMeal3());
		FoodItem foodItem2 = FoodItem.getInstance("test instance 2");
		FoodItem foodItem4 = FoodItem.getInstance("test instance 4");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem2);
		items.add(foodItem4);
		float result = restaraunt.getCheapestPriceFromMenuContainingFoodItems(items);
		return;
	}
	
	private PricedFoodItem createSingleItem() {
		FoodItem foodItem = FoodItem.getInstance("test instance 1");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem);
		return new PricedFoodItem(6.66F, items);
	}
	
	private PricedFoodItem createSingleItem2() {
		FoodItem foodItem = FoodItem.getInstance("test instance 2");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem);
		return new PricedFoodItem(7.77F, items);
	}
	
	private PricedFoodItem createValueMeal() {
		FoodItem foodItem1 = FoodItem.getInstance("test instance 2");
		FoodItem foodItem2 = FoodItem.getInstance("test instance 3");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		return new PricedFoodItem(8.88F, items);
	}
	
	private PricedFoodItem createValueMeal2() {
		FoodItem foodItem1 = FoodItem.getInstance("test instance 3");
		FoodItem foodItem2 = FoodItem.getInstance("test instance 4");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		return new PricedFoodItem(9.99F, items);
	}
	
	private PricedFoodItem createValueMeal3() {
		FoodItem foodItem1 = FoodItem.getInstance("test instance 2");
		FoodItem foodItem2 = FoodItem.getInstance("test instance 4");
		Set<FoodItem> items = new HashSet<FoodItem>();
		items.add(foodItem1);
		items.add(foodItem2);
		return new PricedFoodItem(3.99F, items);
	}
}
