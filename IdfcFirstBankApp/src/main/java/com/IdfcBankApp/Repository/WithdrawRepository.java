package com.IdfcBankApp.Repository;
import
org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.IdfcBankApp.TransactionModel.Withdraw;
@Repository
public interface WithdrawRepository extends
ReactiveMongoRepository<Withdraw, Integer> {
}
