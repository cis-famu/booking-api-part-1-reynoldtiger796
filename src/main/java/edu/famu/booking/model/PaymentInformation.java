package edu.famu.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInformation {
    private String cardNumber;
    private String expirationDate;
    private String billingAddress;
}
