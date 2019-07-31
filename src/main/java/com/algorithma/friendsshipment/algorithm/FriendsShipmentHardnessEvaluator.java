package com.algorithma.friendsshipment.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class FriendsShipmentHardnessEvaluator {

    public FriendsNetwork evaluateClosestFriendFrom(FriendsNetwork friendsNetwork, Friend firstFriend) {

        firstFriend.setHardness(0);

        Set<Friend> evaluatedFriends = new HashSet();
        Set<Friend> unevaluatedFriends = new HashSet();
        unevaluatedFriends.add(firstFriend);

        while (unevaluatedFriends.size() != 0) {
            Friend currentFriend = getClosestFriend(unevaluatedFriends);
            unevaluatedFriends.remove(currentFriend);
            for (Map.Entry<Friend, Integer> friendEntry : currentFriend.getCloseByFriends().entrySet()) {
                Friend evaluationFriend = friendEntry.getKey();
                Integer providedFriendHardness = friendEntry.getValue();

                if (!evaluatedFriends.contains(evaluationFriend)) {
                    calculateMinimumHardness(evaluationFriend, providedFriendHardness, currentFriend);
                    unevaluatedFriends.add(evaluationFriend);
                }
            }
            evaluatedFriends.add(currentFriend);
        }
        return friendsNetwork;
    }

    private void calculateMinimumHardness(Friend evaluationFriend, Integer evaluationFriendHardness, Friend currentFriend) {
        Integer currentFriendHardness = currentFriend.getHardness();
        if (currentFriendHardness + evaluationFriendHardness < evaluationFriend.getHardness()) {
            evaluationFriend.setHardness(currentFriendHardness + evaluationFriendHardness);
            LinkedList<Friend> shipmentPath = new LinkedList(currentFriend.getFriendsShipmentPath());
            shipmentPath.add(currentFriend);
            evaluationFriend.setFriendsShipmentPath(shipmentPath);
        }
    }

    private Friend getClosestFriend(Set<Friend> unevaluatedFriends) {
        Friend closestFriend = null;
        int minimumHardness = Integer.MAX_VALUE;
        for (Friend friend : unevaluatedFriends) {
            int friendHardness = friend.getHardness();
            if (friendHardness < minimumHardness) {
                minimumHardness = friendHardness;
                closestFriend = friend;
            }
        }
        return closestFriend;
    }
}

