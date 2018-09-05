package com.aisera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.aisera.entities.User;
import com.aisera.service.UserService;
import com.aisera.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class ParserApplication {
	public static UserService userService = new UserServiceImpl();

	public static void main(String[] args) {

		try {
			for (String fileName : args) {
				processFile(fileName);
			}
		} catch (IOException e) {

		}
	}

	public static void processFile(String fileName) throws IOException {
		System.out.println("*******************************");
		System.out.println("\nProcessing file : "+ fileName);
		File initialFile = new File("resources/" + fileName);
		InputStream inputStream = new FileInputStream(initialFile);
		JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
		Gson gson = new GsonBuilder().create();
		List<User> users = new ArrayList<User>();
		reader.beginArray();
		while (reader.hasNext()) {
			User user = gson.fromJson(reader, User.class);
			users.add(user);
			if (users.size() == 1000) {
				userService.printSummary(users);
				users.clear();
			}
		}
		if (!users.isEmpty()) {
			userService.printSummary(users);
		}
		reader.endArray();
		reader.close();
		inputStream.close();

	}

}