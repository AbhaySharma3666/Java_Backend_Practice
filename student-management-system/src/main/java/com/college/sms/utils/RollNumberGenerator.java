package com.college.sms.utils;

import java.time.Year;
import java.util.Random;

public class RollNumberGenerator {

    public static String generate(String departmentCode) {
        int year = Year.now().getValue();
        int random = new Random().nextInt(9000) + 1000;
        return departmentCode + year + random;
    }
}
