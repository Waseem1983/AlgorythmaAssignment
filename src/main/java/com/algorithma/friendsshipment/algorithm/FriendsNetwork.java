package com.algorithma.friendsshipment.algorithm;

import java.util.HashSet;
import java.util.Set;

public class FriendsNetwork {

	private Set<Friend> friends = new HashSet();

	public void addFriend(Friend friend) {
		friends.add(friend);
	}

	public Set<Friend> getFriends() {

		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	public Friend getFriend(String friendName) {

		for (Friend friend : friends) {
			if (friend.getName().equals(friendName))
				return friend;
		}

		return null;
	}
}