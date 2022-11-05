package com.IdfcBankApp.BankModel;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import com.IdfcBankApp.UserModel.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BankData")
public class Bank {
private int BranchId;
private String BranchName;
private List<User> user;
public void setUser(List<User> user2) {
this.user = user2;
}
}
