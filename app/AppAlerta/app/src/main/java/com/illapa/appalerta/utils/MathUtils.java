package com.illapa.appalerta.utils;

import java.util.Random;

/**
 * Created by emedinaa on 04/06/2015.
 */
public class MathUtils {

    public static int randomByRange(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        Random random= new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
