package com.algorythma.friendsshipment.beans;

import com.algorythma.friendsshipment.algorithm.Friend;


/**
 * @author waseem
 * This class represent a single Test case provided in the CSV file.
 * each CSV record is analyzed to the corresponding fields below, please also note
 * that always source Friend is equal to 'ME'.
 */
public class ShipmentDetails {
		
	private Friend sourceFriend;
	private Friend destinationFriend;
	private double weight;
	private double width;
	private double height;
	private double length;
	private double cost;
	private String testCaseText;
	
	public String getTestCaseText() {
		return testCaseText;
	}
	public void setTestCaseText(String testCaseText) {
		this.testCaseText = testCaseText;
	}
	public Friend getSourceFriend() {
		return sourceFriend;
	}
	public void setSourceFriend(Friend source) {
		this.sourceFriend = source;
	}
	public Friend getDestinationFriend() {
		return destinationFriend;
	}
	public void setDestinationFriend(Friend friend) {
		this.destinationFriend = friend;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}	
	
}
