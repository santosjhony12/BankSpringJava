package com.bank.BankSpring.Model.Cliente;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
