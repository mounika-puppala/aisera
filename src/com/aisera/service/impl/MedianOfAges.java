package com.aisera.service.impl;

import java.util.List;
import java.util.stream.DoubleStream;

import com.aisera.entities.User;
import com.aisera.service.Query;

public class MedianOfAges implements Query {
	@Override
	public String processUsers(List<User> users) {
		DoubleStream sortedAges = users.parallelStream().mapToDouble(u -> u.getAge()).sorted();
		Double result = users.size() % 2 == 0 ? sortedAges.skip(users.size() / 2 - 1).limit(2).average().getAsDouble()
				: sortedAges.skip(users.size() / 2).findFirst().getAsDouble();
		return "\nMedian age for Users --> " + result;
	}
}
