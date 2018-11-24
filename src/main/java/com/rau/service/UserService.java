package com.rau.service;

import com.rau.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
