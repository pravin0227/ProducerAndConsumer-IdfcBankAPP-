package com.IdfcBankApp.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.IdfcBankApp.Repository.UserRepo;
import com.IdfcBankApp.UserModel.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/User")
public class UserController {
@Autowired
private ReactiveMongoTemplate reactiveMongoTemplate;
@Autowired
private UserRepo userrepo;

@Autowired
private KafkaTemplate<String, User> kafkatemplate;

@PostMapping("/createUser")
public Mono<User> createUser(@RequestBody User user) {

return userrepo.save(user);
}
@GetMapping("/ViewBalance/{custId}")
public Mono<User> getbalance(@PathVariable int custId) {
return userrepo.findById(custId);
}
@DeleteMapping("/deleteById/{custId}")
public Mono<Void> deletebyId(@PathVariable int custId){
return userrepo.deleteById(custId);
}
}

