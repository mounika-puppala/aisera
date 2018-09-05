package com.aisera.service.impl;

import com.aisera.entities.User;
import com.aisera.service.Query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRegisteredPerYear implements Query {
    @Override
    public String processUsers(List<User> users) {
       StringBuilder sb = new StringBuilder();
		
		Map<Integer, Long> yearMap = users.stream().collect(
                Collectors.groupingBy(
                        User::getRegisteredYear, Collectors.counting()
                ));
		sb.append("\nUsers registered in each Year:");
		for(Integer key:yearMap.keySet()) {
			sb.append("\n"+key+" --> "+yearMap.get(key));
		}
		return new String(sb);
    }
}
