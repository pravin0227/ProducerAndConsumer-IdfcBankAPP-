package com.IdfcBankApp.Repository;
import
org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.IdfcBankApp.UserModel.User;
public interface UserRepo extends ReactiveMongoRepository<User, Integer>{
}
