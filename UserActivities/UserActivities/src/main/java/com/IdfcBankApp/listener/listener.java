package com.IdfcBankApp.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.IdfcBankApp.UserActivities;
import com.IdfcBankApp.repository.mongorepo;

@Component
public class listener {

	
	@Autowired
	private mongorepo repo;
	
	@KafkaListener(topics="useractivities1",groupId="IdfcBankAppGroup")
	public void consumes(UserActivities activity) {
		repo.save(activity);
		activity.getAccountNo();
		System.out.println(activity);
		
	}
	
}
