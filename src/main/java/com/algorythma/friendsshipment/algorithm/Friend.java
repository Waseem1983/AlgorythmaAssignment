package com.algorythma.friendsshipment.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author waseem
 *
 * Represent a Friend in the FriendsNetwork graph, and it maintains a hashmap of direct friends in the graph along with there ranks(Hard rank),
 * the frindsShipmentPath represent the optimal delivery path from the source of the deliver to this Friend.
 * hardness value change frequently based on the source of the search and the differnt hops weights in between the source and destination. 
 * 
 */
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
