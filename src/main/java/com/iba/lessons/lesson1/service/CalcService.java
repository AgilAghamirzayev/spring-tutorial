package com.iba.lessons.lesson1.service;

import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public int add(int a, int b){
        return a+b;
    }

    public int mul(int a, int b){
        return a*b;
    }
}
