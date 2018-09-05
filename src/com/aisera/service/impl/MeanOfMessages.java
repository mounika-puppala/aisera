package com.aisera.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.aisera.entities.User;
import com.aisera.service.Query;

public class MeanOfMessages implements Query {
	@Override
	public String processUsers(List<User> users) {
		Double msgsMean = users.parallelStream().filter(u -> u.getIsActive() && u.getGender().equals("female"))
				.collect(Collectors.averagingLong(u -> u.getMessages()));
		return "\nMean for number of Unread messages for Active females --> " + msgsMean;
	}
}
