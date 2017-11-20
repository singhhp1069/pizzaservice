package com.ec.pizzaserve.assignment1.entity;
import java.util.Arrays;

public enum Size {

    Standard,
    Large;

    public static Size getSize(String str){
        String temp = str.toLowerCase();
            if(temp.equalsIgnoreCase("standard")){
                return Standard;
            }else if(temp.equalsIgnoreCase("large")){
                return Large;
            }else{
                return null;
            }
    }
}

