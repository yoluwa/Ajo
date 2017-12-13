package com.example.oluwaseun.ajo.models;

import java.io.Serializable;

/**
 * Created by Oluwaseun on 19/10/2017.
 */

public class User implements Serializable {

    private String name, dateOfBirth, bvn, phoneNumber, accountNumber, bankName, email, password,
            confirmPassword, securityQuestion, answerToSecurityQuestion;


    public User(String name, String dateOfBirth, String bvn, String phoneNumber,
                String accountNumber, String bankName){

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bvn = bvn;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }
//
//    public User2 (){
//        this.email = email;
//        this.password = password;
//        this.confirmPassword = confirmPassword;
//        this.securityQuestion = securityQuestion;
//        this.answerToSecurityQuestion = answerToSecurityQuestion;
//    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getBvn(){
        return this.bvn;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName(){
        return this.bankName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword(){
        return this.confirmPassword;
    }

    public void setAnswerToSecurityQuestion(String answerToSecurityQuestion) {
        this.answerToSecurityQuestion = answerToSecurityQuestion;
    }

    public String getAnswerToSecurityQuestion(){
        return this.answerToSecurityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityQuestion(){
        return this.securityQuestion;
    }
}

