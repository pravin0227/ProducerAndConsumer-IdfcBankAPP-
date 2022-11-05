package com.IdfcBankApp.TransactionController;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IdfcBankApp.UserActivities;
import com.IdfcBankApp.Repository.UserRepo;
import com.IdfcBankApp.Repository.WithdrawRepository;
import com.IdfcBankApp.Repository.depositRepo;
import com.IdfcBankApp.Repository.transferrepo;
import com.IdfcBankApp.TransactionModel.Deposit;
import com.IdfcBankApp.TransactionModel.Transfer;
import com.IdfcBankApp.TransactionModel.Withdraw;
import com.IdfcBankApp.UserModel.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private KafkaTemplate<String, UserActivities> kafkatemplate;
	
	
	@Autowired
	private depositRepo depositrepo;
	@Autowired
	private WithdrawRepository withdrawrepo;
	@Autowired
	private transferrepo transferrepo;
	@Autowired
	private UserRepo userrepo;

	@GetMapping("/deposit/{amount}/{accountNo}")
	public Mono<Deposit> depositamount(@PathVariable int amount, @PathVariable String accountNo) {
		userrepo.findAll().filter(user -> user.getAccountNo().equals(accountNo)).doOnNext(user -> {
			user.setBalance(user.getBalance() + amount);
			System.out.println(user);
		}).flatMap(userrepo::save).subscribe();
		
		kafkatemplate.send("useractivities1", new UserActivities("deposit",accountNo, amount+" deposited in "+accountNo+" "+LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		
		return depositrepo.save(new Deposit(accountNo, amount, amount + " deposited successfully ",
				LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
	}

	@GetMapping("/viewBalance/{accountNo}")
	public Flux<User> getUser(@PathVariable String accountNo) {
		
		kafkatemplate.send("useractivities1", new UserActivities("viewBalance",accountNo, " user viewed Balance "+LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		
		return userrepo.findAll().filter(user -> user.getAccountNo().equals(accountNo));
	}

	@GetMapping("/withdraw/{amount}/{accountNo}")
	public Mono<Withdraw> withdrawamount(@PathVariable int amount, @PathVariable String accountNo) {
		if (amount > 15000) {
			return withdrawrepo.save(new Withdraw(accountNo, amount, "Cannot withdraw amount more than 15000 at a time",
					LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		}else if(amount%100!=0) {
			return withdrawrepo.save(new Withdraw(accountNo, amount, "Notes not available try with multiple of 100 rs",
					LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		}
		userrepo.findAll().filter(user -> user.getAccountNo().equals(accountNo)).doOnNext(user -> {
			user.setBalance(user.getBalance() - amount);
			System.out.println(user);
		}).flatMap(userrepo::save).subscribe();
		
		kafkatemplate.send("useractivities1", new UserActivities("withdraw",accountNo, amount+" withdrawl from "+accountNo+" "+LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		
		return withdrawrepo.save(new Withdraw(accountNo, amount, amount + " withdraw successfully",
				LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
	}

	@GetMapping("/transfer/{amount}/{SenderAccountNo}/{RecieverAccountNO}")
	public Mono<Transfer> transfermoney(@PathVariable int amount, @PathVariable String SenderAccountNo,
			@PathVariable String RecieverAccountNO) {
		if (amount >= 20000) {
			return transferrepo.save(new Transfer(SenderAccountNo, RecieverAccountNO, amount,
					"Cannot Transfer amount more than 20000 at a time", LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		}
		userrepo.findAll().filter(user -> user.getAccountNo().equals(SenderAccountNo)).doOnNext(user -> {
			user.setBalance(user.getBalance() - amount);
			System.out.println(user);
		}).flatMap(userrepo::save).subscribe();
		userrepo.findAll().filter(user -> user.getAccountNo().equals(RecieverAccountNO)).doOnNext(user -> {
			user.setBalance(user.getBalance() + amount);
			System.out.println(user);
		}).flatMap(userrepo::save).subscribe();
		
		kafkatemplate.send("useractivities1", new UserActivities("transfer",SenderAccountNo, amount+" transferred from account "+SenderAccountNo+" to "+RecieverAccountNO+" at "+LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
		
		return transferrepo.save(new Transfer(SenderAccountNo, RecieverAccountNO, amount,
				amount + " transferred successfully to the account " + RecieverAccountNO,
				LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));
	}
}
