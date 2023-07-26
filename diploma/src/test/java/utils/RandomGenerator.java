package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {

    public Integer intGen() {
        return  (int) ((Math.random() * (Integer.MAX_VALUE - 0)) + 0);
    }

    public String stringGen(int quantity){
        return RandomStringUtils.randomAlphabetic(quantity);
    }
}
