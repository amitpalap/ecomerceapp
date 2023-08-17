package com.ecommerce.ecomerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecomerceapp.dao.*;
import com.ecommerce.ecomerceapp.entity.*;
import java.util.List;

@Component
public class UserService {

	 @Autowired
	 private UserDAO userDAO;
	 
	 
	@Transactional
		public User authenticate(String userId, String pwd) {
			return userDAO.authenticate(userId, pwd);
	}
	
	 @Transactional
		public User getUserById(long id) {
		 	return userDAO.getUserById(id);
		}
	 
	 @Transactional
		public User getUserByEmailId(String emailId) {
			return userDAO.getUserByEmailId(emailId);
		}
		
	 @Transactional
		public void updateUser(User user) {
			userDAO.updateUser(user);			
		}

	 @Transactional
		public List<User> getAllUsers() {
		 return userDAO.getAllUsers();
		}	 
}
