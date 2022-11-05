package com.IdfcBankApp.BankController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.IdfcBankApp.BankModel.Bank;
import com.IdfcBankApp.Repository.BankRepo;
import com.IdfcBankApp.Repository.UserRepo;
import com.IdfcBankApp.UserModel.User;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/Bank")
public class BankController {
@Autowired
private BankRepo repo;
@Autowired
private UserRepo userrepo;
@PostMapping("/SaveBranchDadar")
public Mono<Bank> saveBranchDadar() {
List<User> list = new ArrayList<>();
userrepo.findAll().filter(user ->
user.getBranchName().equals("Dadar")).doOnNext(user -> list.add(user))
.subscribe();
return repo.save(new Bank(58095, "Dadar", list));
}
@PostMapping("/saveBranchBandra")
public Mono<Bank> saveBranchBandra() {
List<User> list = new ArrayList<>();
userrepo.findAll().filter(user ->
user.getBranchName().equals("Bandra")).doOnNext(user -> list.add(user))
.subscribe();
return repo.save(new Bank(58095, "Bandra", list));
}
}