package com.algorithma.friendsshipment;

import com.algorithma.friendsshipment.algorithm.Friend;
import com.algorithma.friendsshipment.algorithm.FriendsNetwork;
import com.algorithma.friendsshipment.algorithm.FriendsShipmentHardnessEvaluator;
import com.algorithma.friendsshipment.beans.ShipmentDetails;
import com.algorithma.friendsshipment.file.csv.FriendsCSVNetworkBuilder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendsNetworkTest {

	private static FriendsNetwork friendsNetwork;
	private static List<ShipmentDetails> testCases;

	@BeforeAll
	public static void init() {

		FriendsCSVNetworkBuilder csvParser = new FriendsCSVNetworkBuilder();
		String testCaseCSVPath = System.getProperty("csvFile");

		if (testCaseCSVPath == null) {

			javax.swing.JFileChooser csvFileChooser = new JFileChooser("Please Select CSV file");
			csvFileChooser.showDialog(null, "Select CSV test case file");
			File csvFile = csvFileChooser.getSelectedFile();
			csvParser.loadTestCase(csvFile.getAbsolutePath());

		} else {

			csvParser.loadTestCase(testCaseCSVPath);
		}

		friendsNetwork = csvParser.getFriendsNetwork();
		testCases = csvParser.getTestCases();

	}

	@Test
	public void whenSPPSolved_thenCorrect() {

		FriendsShipmentHardnessEvaluator friendsShipmentHardnessEvaluator = new FriendsShipmentHardnessEvaluator();
		friendsNetwork = friendsShipmentHardnessEvaluator.evaluateClosestFriendFrom(friendsNetwork,
				friendsNetwork.getFriend("ME"));

		for (ShipmentDetails shipment : testCases) {
			Friend destinationFriend = friendsNetwork.getFriend(shipment.getDestinationFriend().getName());
			
			printPath(destinationFriend);
			
			if(destinationFriend.getFriendsShipmentPath().size()!=0)
			ShipmentCostCalculator.calculateShipmentCost(shipment, destinationFriend);
			
		}

//        Friend FriendA = new Friend("A");
//        Friend FriendB = new Friend("B");
//        Friend FriendC = new Friend("C");
//        Friend FriendD = new Friend("D");
//        Friend FriendE = new Friend("E");
//        Friend FriendF = new Friend("F");
//
//        FriendA.addFriend(FriendB, 10);
//        FriendA.addFriend(FriendC, 15);
//
//        FriendB.addFriend(FriendD, 12);
//        FriendB.addFriend(FriendF, 15);
//
//        FriendC.addFriend(FriendE, 10);
//
//        FriendD.addFriend(FriendE, 2);
//        FriendD.addFriend(FriendF, 1);
//
//        FriendF.addFriend(FriendE, 5);
//
//        FriendsNetwork graph = new FriendsNetwork();
//
//        graph.addFriend(FriendA);
//        graph.addFriend(FriendB);
//        graph.addFriend(FriendC);
//        graph.addFriend(FriendD);
//        graph.addFriend(FriendE);
//        graph.addFriend(FriendF);
//
//        FriendsShipmentHardnessEvaluator friendsShipmentHardnessEvaluator = new FriendsShipmentHardnessEvaluator();
//        graph = friendsShipmentHardnessEvaluator.evaluateClosestFriendFrom(graph, FriendA);
//
//        List<Friend> shortestPathForFriendB = Arrays.asList(FriendA);
//        List<Friend> shortestPathForFriendC = Arrays.asList(FriendA);
//        List<Friend> shortestPathForFriendD = Arrays.asList(FriendA, FriendB);
//        List<Friend> shortestPathForFriendE = Arrays.asList(FriendA, FriendB, FriendD);
//        List<Friend> shortestPathForFriendF = Arrays.asList(FriendA, FriendB, FriendD);
//
//        for (Friend friend : graph.getFriends()) {
//
//            printPath(friend.getFriendsShipmentPath());
//            switch (friend.getName()) {
//                case "B":
//                    printPath(shortestPathForFriendB);
//                    assertTrue(friend
//                            .getFriendsShipmentPath()
//                            .equals(shortestPathForFriendB));
//                    break;
//                case "C":
//                    printPath(shortestPathForFriendC);
//                    assertTrue(friend
//                            .getFriendsShipmentPath()
//                            .equals(shortestPathForFriendC));
//                    break;
//                case "D":
//                    printPath(shortestPathForFriendD);
//                    assertTrue(friend
//                            .getFriendsShipmentPath()
//                            .equals(shortestPathForFriendD));
//                    break;
//                case "E":
//                    printPath(shortestPathForFriendE);
//                    assertTrue(friend
//                            .getFriendsShipmentPath()
//                            .equals(shortestPathForFriendE));
//                    break;
//                case "F":
//                    printPath(shortestPathForFriendF);
//                    assertTrue(friend
//                            .getFriendsShipmentPath()
//                            .equals(shortestPathForFriendF));
//                    break;
//            }
//        }
	}

	private void printPath(Friend receiverFriend) {
		List<Friend> friendsPath = receiverFriend.getFriendsShipmentPath();

		if (friendsPath.size() == 0) {
			System.out.println(receiverFriend.getName() + ": Destination friend Unreachable");
			return;
		}
		for (Friend friend : friendsPath) {
			System.out.print("" + friend.getName() + "(" + friend.getHardness() + ")-->");
		}
		System.out.print(receiverFriend.getName() + "(" + receiverFriend.getHardness() + ")");

		System.out.println();
	}

}
