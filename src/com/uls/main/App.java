package com.uls.main;

import com.uls.dao.JpaUserDao;
import com.uls.generated.User;

public class App {

	public static void main(String[] args) {
		new App();
	}
	
	App() {
		JpaUserDao userDao = new JpaUserDao();
		System.out.println(">>> Specific: " + userDao.selectById(1).get().getFistname());
		
		System.out.println(">>> List");
		
		for (User u : userDao.selectAll()) {
			System.out.println(">>> " + u.getEmail());
		}
		
		
		System.out.println(userDao.selectById(1).get().getGroups());
		
	}

}
