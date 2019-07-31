package com.algorithma.friendsshipment.beans;

import com.algorithma.friendsshipment.algorithm.Friend;

public class ShipmentDetails {
		
	private Friend sourceFriend;
	private Friend destinationFriend;
	private double weight;
	private double width;
	private double height;
	private double length;
	private double cost;
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
