package com.aisera.service.impl;

import com.aisera.entities.User;
import com.aisera.service.Query;

import java.util.List;
import java.util.stream.DoubleStream;

public class MedianNumberOfFriends implements Query {
	@Override
	public String processUsers(List<User> users) {
		DoubleStream friends = users.parallelStream().mapToDouble(u -> u.getFriends().size()).sorted();
		double result = users.size() % 2 == 0 ? friends.skip(users.size() / 2 - 1).limit(2).average().getAsDouble()
				: friends.skip(users.size() / 2).findFirst().getAsDouble();
		return "\nMedian for Number of Friends -->" + result;
	}
}
