package com.example.oluwaseun.ajo.utils;

/**
 * Created by Oluwaseun on 27/11/2017.
 */

public class Endpoint {

    private static String BASE_URL = "https://ajoplus.herokuapp.com/api/v1";
    private static final String USER = BASE_URL + "/user";
    public static final String LOGIN = BASE_URL + "/login";
    public static final String REGISTER = BASE_URL + "/register";

    public  static final String USER_PROFILE =  USER + "/profile";
    public static final String CREATE_GROUP = USER + "/group/create";
    public static final String  FUND_REFERENCE = USER + "/wallet/fund";

    public static final String PAYSTACK_INITIALIZE = "https://api.paystack.co/transaction/initialize";
    public Endpoint() {

    }

}
