package com.aisera.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.aisera.entities.User;
import com.aisera.service.Query;

public class MeanOfBalances implements Query {
	@Override
	public String processUsers(List<User> users) {
		BigDecimal balanceMean = users.parallelStream().map(u -> u.getBalanceAmount()).reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(new BigDecimal(users.size()), RoundingMode.UP);
		return "\nMean Balance Amount --> " + balanceMean ;
	}
}
