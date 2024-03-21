package com.kog.LibraryManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kog.LibraryManagementSystem.beans.User;

@Repository
public class UserService {
	
	private List<User> userList=new ArrayList<User>();
	
	//Get all The Users
	public List<User> getAlUsers()
	{
		return userList;
	}
	
	//Add Users
	public User addUser(User u)
	{
		 userList.add(u);
		 return u;
	}
	
	//Get a User
	public User getUser(Integer id)
	{
		return userList.stream().filter(u->u.getUser_id().equals(id)).findFirst().orElse(null);
	}
	
	//Update a user 
	public User updateUser(Integer id,String name)
	{
		User u=userList.stream().filter(user->user.getUser_id().equals(id)).findFirst().orElse(null);
		
		if(u!=null)
		{
			u.setUser_name(name);
			return u;
		}
		return null;
			
	}
	
	//Delete User
	public String deleteUser(Integer id)
	{
		userList.removeIf(u->u.getUser_id().equals(id));
		return "User got deleted";
		
	}
		
}
