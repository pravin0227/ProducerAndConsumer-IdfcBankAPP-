package com.IdfcBankApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.IdfcBankApp.UserActivities;

@Repository
public interface mongorepo extends MongoRepository<UserActivities, String>{

}
