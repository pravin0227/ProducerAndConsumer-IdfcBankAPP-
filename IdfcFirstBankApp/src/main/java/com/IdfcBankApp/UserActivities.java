package com.IdfcBankApp;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivities {
	
	@Id
	private String transactionType;
	private String AccountNo;
	private String activity;
	
}
