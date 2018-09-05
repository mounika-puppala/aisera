package com.aisera.service.impl;

import com.aisera.entities.User;
import com.aisera.service.Query;
import com.aisera.service.Summarizer;

import java.util.List;

public class SummarizerImpl implements Summarizer {
    private List<Query> queries;

    public SummarizerImpl(List<Query> queries) {
        this.queries = queries;
    }

    @Override
    public void printSummary(List<User> users) {
        StringBuilder sb = new StringBuilder();
        for(Query query : queries) {
        	sb.append(query.processUsers(users));
        }
		System.out.println(sb);
    }
}
