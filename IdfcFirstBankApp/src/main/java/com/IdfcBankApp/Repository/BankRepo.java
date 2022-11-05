package com.IdfcBankApp.Repository;
import
org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.IdfcBankApp.BankModel.Bank;
import com.IdfcBankApp.UserModel.User;
@Repository
public interface BankRepo extends ReactiveMongoRepository<Bank,
Integer>{
void save(User user);
}
