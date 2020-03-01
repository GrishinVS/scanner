package com.grishinvs.scanner;

public class Runner {

    public static void main(String[] args) {

        Configuration.getDirectoryList().forEach(System.out::println);

    }

}
