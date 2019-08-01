package com.algorythma.friendsshipment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import com.algorythma.friendsshipment.algorithm.Friend;
import com.algorythma.friendsshipment.algorithm.FriendsNetwork;
import com.algorythma.friendsshipment.algorithm.FriendsShipmentEvaluator;
import com.algorythma.friendsshipment.beans.ShipmentDetails;
import com.algorythma.friendsshipment.file.csv.CSVFileReader;
import com.algorythma.friendsshipment.file.csv.FriendsCSVNetworkBuilder;
import com.algorythma.friendsshipment.utils.ShipmentCostCalculator;

/**
 * @author waseem 
 * 		   The Main JUnit test class, Initially loads the CSV file or the
 *         CSV files in the provided directory process each file individually to
 *         generate the FriendsNetwork (a graph structure internally) and the
 *         corresponding test cases provided in the same CSV, loaded in the the two static maps,
 *         that will be used later on during the Testing.
 */
public class FriendsNetworkTest {

	private static HashMap<String, FriendsNetwork> friendNetworks = new HashMap<>();
	private static HashMap<String, List<ShipmentDetails>> testCasesLists = new HashMap<>();
	private static File[] csvFiles;

	@BeforeAll
	public static void init() {

		String testCaseCSVPath = CSVFileReader.prepareSourceCSVFile();

		File csvFile = new File(testCaseCSVPath);
		if (csvFile.isDirectory()) {
			csvFiles = csvFile.listFiles((file) -> {
				return file.getAbsolutePath().endsWith("csv");
			});

			for (File file : csvFiles) {

				FriendsCSVNetworkBuilder csvParser = new FriendsCSVNetworkBuilder();
				String absolutePath = file.getAbsolutePath();
				csvParser.loadTestCaseFile(absolutePath);

				friendNetworks.put(absolutePath, csvParser.getFriendsNetwork());
				testCasesLists.put(absolutePath, csvParser.getTestCases());
			}

		} else {

			FriendsCSVNetworkBuilder csvParser = new FriendsCSVNetworkBuilder();
			csvParser.loadTestCaseFile(testCaseCSVPath);

			friendNetworks.put(testCaseCSVPath + csvFile.getName(), csvParser.getFriendsNetwork());
			testCasesLists.put(testCaseCSVPath + csvFile.getName(), csvParser.getTestCases());
			csvFiles = new File[] { new File(testCaseCSVPath) };

		}

	}

	/**
	 * in this test method we  iterate over the CSV files array loaded initially during the
	 * initialization, the absolute path is used as a key in the hashmap, 
	 * to retrieve the FriendsNetwork/TestCases (shipment details List)
	 * We use the FriendsShipmentEvaluator to prepare the FriendsNetwork(the actually graph) for the real
	 * work.
	 * in order to complete all the tests in case of failed tests we catch the error AssertionFailedError.
	 * 
	 */
	@Test
	public void validateTestCases() {

		for (File file : csvFiles) {

			FriendsNetwork friendsNetwork = friendNetworks.get(file.getAbsolutePath());
			List<ShipmentDetails> testCases = testCasesLists.get(file.getAbsolutePath());

			System.out.println("\n\nEvaluating File :" + file.getAbsolutePath());

			friendsNetwork = new FriendsShipmentEvaluator().evaluateClosestFriendFrom(friendsNetwork,
					friendsNetwork.getFriend("ME"));

			for (ShipmentDetails shipment : testCases) {

				Friend destinationFriend = friendsNetwork.getFriend(shipment.getDestinationFriend().getName());

				printPath(destinationFriend);

				if (destinationFriend.getFriendsShipmentPath().size() != 0) {

					try {
						assertTrue(shipment.getCost() == ShipmentCostCalculator.calculateShipmentCost(shipment,
								destinationFriend));
					} catch (AssertionFailedError failedAssert) {
						System.out.println("\nCalculated cost and provided cost not equal");
					}
				}

			}
		}

	}

	/**	
	 * @param receiverFriend
	 * Simply we print the path from the source friend to the destination friend, just note that
	 * the weight (Hardness) in the destination friend, is the sum of all weights all the way from the source.
	 */
	private void printPath(Friend receiverFriend) {
		List<Friend> friendsPath = receiverFriend.getFriendsShipmentPath();

		if (friendsPath.size() == 0) {
			System.out.println("\n" + receiverFriend.getName() + ": Destination friend Unreachable\n");
			return;
		}
		System.out.print("\nBest Shipment Path\n");
		for (Friend friend : friendsPath) {
			System.out.print(friend.getName() + "(" + friend.getHardness() + ")-->");
		}
		System.out.print(receiverFriend.getName() + "(" + receiverFriend.getHardness() + ")\n");

	}

}
