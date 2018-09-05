package com.aisera.service;

import com.aisera.entities.User;

import java.util.List;

public interface Query {
    String processUsers(List<User> users);
}
