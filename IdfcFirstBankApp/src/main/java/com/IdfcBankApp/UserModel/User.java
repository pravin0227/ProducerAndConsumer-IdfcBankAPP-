package com.IdfcBankApp.UserModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
@Data
@Document(collection = "UserData")
public class User {
@Id
private int custId;
private int pin;
private String name;
private String AccountNo;
private Address address;
private int balance;
private int BranchId;
private String BranchName;
}