package com.aisera.service;

import com.aisera.entities.User;

import java.util.List;

public interface Summarizer {
    void printSummary(List<User> users);
}
