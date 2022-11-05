package com.IdfcBankApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IdfcBankApp.UserActivities;
import com.IdfcBankApp.repository.mongorepo;

@RestController
@RequestMapping("/activities")
public class activitycontroller {

	@Autowired
	private mongorepo repo;
	@GetMapping("/getactivities")
	public List<UserActivities> getactivities(){
		return repo.findAll();
		
	}
	
}
