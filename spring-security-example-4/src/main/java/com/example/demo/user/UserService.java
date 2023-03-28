package com.example.demo.user;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.persistence.User;
import com.example.demo.user.persistence.VerificationToken;

public interface UserService {

	public User registerNewUserAccount(UserDto userDto);

	public void createVerificationTokenForUser(User user, String token);

	public VerificationToken getVerificationToken(String token);

	public void saveRegisteredUser(User user);
}
