package com.algorithma.friendsshipment.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Friend {

	private String name;

	private LinkedList<Friend> friendsShipmentPath = new LinkedList();

	private Integer hardness = Integer.MAX_VALUE;

	private Map<Friend, Integer> closeByFriends = new HashMap();

	public Friend(String name) {
		this.name = name;
	}

	public void addFriend(Friend friend, int hardness) {
		closeByFriends.put(friend, hardness);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Friend, Integer> getCloseByFriends() {
		return closeByFriends;
	}

	public void setCloseByFriends(Map<Friend, Integer> closeByFriends) {
		this.closeByFriends = closeByFriends;
	}

	public Integer getHardness() {
		return hardness;
	}

	public void setHardness(Integer hardness) {
		this.hardness = hardness;
	}

	public List<Friend> getFriendsShipmentPath() {
		return friendsShipmentPath;
	}

	public void setFriendsShipmentPath(LinkedList<Friend> friendsShipmentPath) {
		this.friendsShipmentPath = friendsShipmentPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (!name.equals(((Friend)obj).name))
			return false;
		return true;
	}

}
