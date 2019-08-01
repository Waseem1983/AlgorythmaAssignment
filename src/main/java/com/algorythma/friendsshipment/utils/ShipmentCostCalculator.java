package com.algorythma.friendsshipment.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.algorythma.friendsshipment.algorithm.Friend;
import com.algorythma.friendsshipment.beans.ShipmentDetails;


/**
 * @author waseem
 * We calculate the cost following the formula provided in the readme file, initially we calculate the Normalized Volume Weight
 * second we multiply it by SQRT(Hard).
 *
 */
public class ShipmentCostCalculator {

	/**
	 * 
	 * @param shipment
	 * @param destinationFriend
	 * @return shipmentCost
	 * 
	 * following the cost equation provided in the readme.txt file
	 * shipment cost = normalizedWeight x SQRT(Hard)
	 */
	public static double calculateShipmentCost(ShipmentDetails shipment, Friend destinationFriend) {

		int hardness = destinationFriend.getHardness();
		double width = shipment.getWidth();
		double height = shipment.getHeight();
		double length = shipment.getLength();

		double normalizedWeight = calculateNormalizedWeight(width, height, length);
		double shippingCost = Math.sqrt(hardness) * normalizedWeight;
		System.out.println("Test Case:"+shipment.getTestCaseText());
		shippingCost = Math.round(shippingCost * 100.0) / 100.0;
		System.out.println("Calculated Cost :((w:" + width + "xh:" + height + "xl:" + length + ")/5000) x SQRT(" + hardness + ") = "
				+ shippingCost);
		
		System.out.println("Provided Cost:"+shipment.getCost());
		return shippingCost;

	}

	/**
	 * 
	 * @param width
	 * @param height
	 * @param length
	 * @return normalizedWeight
	 * 
	 * normalizedWeight = (width * height * length) / 5000
	 * rounded to the next .5 Volume K
	 * 
	 */
	private static double calculateNormalizedWeight(double width, double height, double length) {
		double normalizedWeight = (width * height * length) / 5000;

		NumberFormat formatter = new DecimalFormat("#00.00000000");
		String formattedWeight = formatter.format(normalizedWeight);
		int dotIndex = formattedWeight.indexOf('.');
		double fractions = Double.parseDouble(formattedWeight.substring(dotIndex));
		int integerPart = Integer.parseInt(formattedWeight.substring(0, dotIndex));

		if (fractions > 0.5) {
			fractions = 1;
		} else {
			fractions = 0.5;
		}

		return (integerPart + fractions);
	}

}
