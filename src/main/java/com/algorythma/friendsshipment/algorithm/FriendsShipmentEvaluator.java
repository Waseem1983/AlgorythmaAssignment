package com.algorythma.friendsshipment.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * @author waseem
 * 
 * This is the core engine of the app, since we are dealing with a Weighted Graph,
 * then the best way to get the least costly path is to run Djikastra algorithm, 
 * Multiple sources reviewed before coming with this implementation, mainly the Book "Data Structures and Algorithms Analysis in Java"
 * https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
 * https://www.baeldung.com/java-dijkstra
 *  
 */
public class FriendsShipmentEvaluator {

    public FriendsNetwork evaluateClosestFriendFrom(FriendsNetwork friendsNetwork, Friend firstFriend) {

        firstFriend.setHardness(0);

        Set<Friend> evaluatedFriends = new HashSet<>();
        Set<Friend> unevaluatedFriends = new HashSet<>();
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
            LinkedList<Friend> shipmentPath = new LinkedList<>(currentFriend.getFriendsShipmentPath());
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

