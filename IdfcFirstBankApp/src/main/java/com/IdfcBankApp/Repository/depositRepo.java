package com.IdfcBankApp.Repository;
import
org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.IdfcBankApp.TransactionModel.Deposit;
@Repository
public interface depositRepo extends ReactiveMongoRepository<Deposit,
Integer> {
}
