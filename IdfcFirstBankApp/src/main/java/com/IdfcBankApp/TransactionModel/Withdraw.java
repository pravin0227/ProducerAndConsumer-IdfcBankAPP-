package com.IdfcBankApp.TransactionModel;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Withdraw")
public class Withdraw {
private String AccountNo;
private int amount;
private String status;
private LocalDateTime time;
}
