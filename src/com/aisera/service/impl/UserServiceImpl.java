package com.aisera.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import com.aisera.entities.User;
import com.aisera.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void printSummary(List<User> users) {
//		Task
//		- Print Summary of User Data including:
//		  * Users registered in each Year
//		  * Median for Number of Friends
//		  * Median age for Users
//		  * Mean Balance Amount
//		  * Mean for number of Unread messages for Active females
		StringBuffer sb = new StringBuffer();
		
		Map<Integer, Long> yearMap = users.stream().collect(
                Collectors.groupingBy(
                        User::getRegisteredYear, Collectors.counting()
                ));
		sb.append("\nUsers registered in each Year:");
		for(Integer key:yearMap.keySet()) {
			sb.append("\n"+key+" --> "+yearMap.get(key));
		}

		BigDecimal balanceMean = users.stream()
				.map(u -> u.getBalanceAmount())
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(new BigDecimal(users.size()));

		Double msgsMean = users.parallelStream()
				.filter( u -> u.getIsActive() && u.getGender().equals("female") )
		        .collect(Collectors.averagingLong(u -> u.getMessages()));
		
		sb.append("\nMedian for Number of Friends --> " + getFriendsMedian(users) + 
				"\nMedian age for Users --> " + getAgeMedian(users) + 
				"\nMean Balance Amount --> " + balanceMean +
				"\nMean for number of Unread messages for Active females --> " + msgsMean);
		System.out.println(sb);

	}

	private double getFriendsMedian(List<User> users) {
		DoubleStream friends = users.parallelStream().mapToDouble(u -> u.getFriends().size()).sorted();
		return users.size() % 2 == 0 ? friends.skip(users.size() / 2 - 1).limit(2).average().getAsDouble()
				: friends.skip(users.size() / 2).findFirst().getAsDouble();

	}

	private double getAgeMedian(List<User> users) {
		DoubleStream sortedAges = users.parallelStream().mapToDouble(u -> u.getAge()).sorted();
		return users.size() % 2 == 0 ? sortedAges.skip(users.size() / 2 - 1).limit(2).average().getAsDouble()
				: sortedAges.skip(users.size() / 2).findFirst().getAsDouble();
	}
}
