package com.algorithma.friendsshipment.file.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.algorithma.friendsshipment.algorithm.Friend;
import com.algorithma.friendsshipment.algorithm.FriendsNetwork;
import com.algorithma.friendsshipment.beans.ShipmentDetails;

public class FriendsCSVNetworkBuilder {

	private FriendsNetwork friendsNetwork = new FriendsNetwork();
	private List<ShipmentDetails> testCases = new ArrayList<>();
	private HashMap<String, Friend> allFriends = new HashMap<>();

	public void loadTestCase(String filePath) {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

			for (CSVRecord csvRecord : csvParser.getRecords()) {
				if (csvRecord.get(0).startsWith("@"))
					buildTestCase(csvRecord);
				else
					addFriendsToTheNetwork(csvRecord);

			}

			friendsNetwork.setFriends(new HashSet<Friend>(allFriends.values()));

			csvParser.close();
		} catch (IOException e) {
			System.err.print("Unable to load CSV file");
		}
	}

	private void addFriendsToTheNetwork(CSVRecord csvRecord) {
		Friend firstFriend = new Friend(csvRecord.get(0));
		if (allFriends.get(firstFriend.getName()) == null) {
			allFriends.put(firstFriend.getName(), firstFriend);
		} else {
			firstFriend = allFriends.get(csvRecord.get(0));
		}

		for (int i = 1; i < csvRecord.size(); i++) {
			String csvCell = csvRecord.get(i);
			String friendName = csvCell.substring(0, csvCell.indexOf(':'));
			String friendHardness = csvCell.substring(csvCell.indexOf(':') + 1);
			Friend friend;

			if (allFriends.get(friendName) == null) {
				friend = new Friend(friendName);
				allFriends.put(friend.getName(), friend);
			} else {
				friend = allFriends.get(friendName);
			}

			firstFriend.addFriend(friend, Integer.parseInt(friendHardness));

		}
	}

	private void buildTestCase(CSVRecord csvRecord) {

		ShipmentDetails shipmentDetails = new ShipmentDetails();
		shipmentDetails.setSourceFriend(new Friend("ME"));
		shipmentDetails.setDestinationFriend(new Friend(csvRecord.get(1)));

		String[] packageDetails = csvRecord.get(2).split("x");
		shipmentDetails.setWidth(Double.parseDouble(packageDetails[0]));
		shipmentDetails.setHeight(Double.parseDouble(packageDetails[1]));
		shipmentDetails.setLength(Double.parseDouble(packageDetails[2]));

		if (!csvRecord.get(3).equals("~")) {
			shipmentDetails.setCost(Double.parseDouble(csvRecord.get(3)));
		}

		testCases.add(shipmentDetails);

	}

	public List<ShipmentDetails> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<ShipmentDetails> testCases) {
		this.testCases = testCases;
	}

	public FriendsNetwork getFriendsNetwork() {
		return friendsNetwork;
	}

	public void setFriendsNetwork(FriendsNetwork friendsNetwork) {
		this.friendsNetwork = friendsNetwork;
	}

}
