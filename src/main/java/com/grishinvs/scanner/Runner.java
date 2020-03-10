package com.grishinvs.scanner;

public class Runner {

    public static void main(String[] args) {
        ScannerFacade scannerDecorator = new ScannerFacade();
        scannerDecorator.startScan("report.txt");
    }

}
