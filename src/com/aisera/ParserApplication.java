package com.aisera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.aisera.entities.User;
import com.aisera.service.Query;
import com.aisera.service.Summarizer;
import com.aisera.service.impl.MeanOfBalances;
import com.aisera.service.impl.MeanOfMessages;
import com.aisera.service.impl.MedianNumberOfFriends;
import com.aisera.service.impl.MedianOfAges;
import com.aisera.service.impl.SummarizerImpl;
import com.aisera.service.impl.UserRegisteredPerYear;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class ParserApplication {
	
	static final List<Query> queries = new LinkedList<>();
	
	static {
		queries.add(new UserRegisteredPerYear());
		queries.add(new MedianNumberOfFriends());
		queries.add(new MedianOfAges());
		queries.add(new MeanOfBalances());
		queries.add(new MeanOfMessages());

	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Please pass at least 1 json file to parse...");
			return;
		}
		Summarizer summarizer = new SummarizerImpl(queries);

		try {
			for (String fileName : args) {
				System.out.println("*******************************");
				System.out.println("\nProcessing file : " + fileName);
				File initialFile = new File(fileName);
				InputStream inputStream = new FileInputStream(initialFile);
				JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
				Gson gson = new GsonBuilder().create();
				List<User> users = new ArrayList<User>();
				reader.beginArray();
				while (reader.hasNext()) {
					User user = gson.fromJson(reader, User.class);
					users.add(user);
					if (users.size() == 1000) {
						summarizer.printSummary(users);
						users.clear();
					}
				}
				if (!users.isEmpty()) {
					summarizer.printSummary(users);
				}
				reader.endArray();
				reader.close();
				inputStream.close();
			}
		} catch (IOException e) {
			System.out.println("Exception in main class");
		}
	}

}