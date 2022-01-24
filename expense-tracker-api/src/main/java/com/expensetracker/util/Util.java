package com.expensetracker.util;

public class Util {

    public static synchronized int createID(int idCounter)
    {
        return ++idCounter;
    }
}
