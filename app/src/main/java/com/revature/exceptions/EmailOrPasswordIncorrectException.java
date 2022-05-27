package com.revature.exceptions;

public class EmailOrPasswordIncorrectException extends Exception{
    public EmailOrPasswordIncorrectException(){
        super("This user input an incorect email or password");
    }

    public EmailOrPasswordIncorrectException(String message){
        super(message);
    }
}
