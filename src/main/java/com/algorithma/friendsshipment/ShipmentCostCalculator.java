package com.algorithma.friendsshipment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.algorithma.friendsshipment.algorithm.Friend;
import com.algorithma.friendsshipment.beans.ShipmentDetails;

public class ShipmentCostCalculator {

	public static double calculateShipmentCost(ShipmentDetails shipment, Friend destinationFriend) {

		int hardness = destinationFriend.getHardness();
		double width = shipment.getWidth();
		double height = shipment.getHeight();
		double length = shipment.getLength();

		double normalizedWeight = calculateNormalizedWeight(width, height, length);
		double shippingCost = Math.sqrt(hardness) * normalizedWeight;

		shippingCost = Math.round(shippingCost * 100.0) / 100.0;
		System.out.println("((w:" + width + "xh:" + height + "xl:" + length + ")/5000) x SQRT(" + hardness + ") = "
				+ shippingCost);
		
		System.out.println("Provided Cost:"+shipment.getCost());
		return shippingCost;

	}

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
