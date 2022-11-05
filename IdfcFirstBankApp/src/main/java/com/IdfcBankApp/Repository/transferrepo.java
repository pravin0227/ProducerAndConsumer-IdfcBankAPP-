package com.IdfcBankApp.Repository;
import
org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.IdfcBankApp.TransactionModel.Transfer;
@Repository
public interface transferrepo extends ReactiveMongoRepository<Transfer,
Integer>{
}
