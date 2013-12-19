package com.aaron.pricing;

import java.util.Arrays;

import com.aaron.pricing.minimizer.PriceMinimizer;

/**
 * A command line interface for running the minimizer
 * 
 * @author Aaron Ingber
 *
 */
public class FoodPricerCLI {
	
	private static final int INPUT_FILE_INDEX = 0;
	private static final int REQUESTED_FOOD_INDEX = 1;
	private static PriceMinimizer minimizer = new PriceMinimizer();

	/**
	 * A main method for running the minimizer
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PriceMinimizer.MinimizerResult result = minimizer.minimize(args[INPUT_FILE_INDEX], FoodItem.asFoodItems(Arrays.copyOfRange(args, REQUESTED_FOOD_INDEX, args.length)));
		if (result.getRestaurantId() == 0) {
			System.out.println("nil");
		}
		else {
			System.out.println(result.getRestaurantId() + ", " + result.getTotalPrice());
		}
	}

}
