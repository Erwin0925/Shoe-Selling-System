package Utility;

import java.util.Scanner;

public class GlobalScanner {

    private static GlobalScanner instance;
    private final Scanner scanner;

    private GlobalScanner(){
        this.scanner = new Scanner(System.in);
    }

    public static GlobalScanner getInstance(){
        if (instance == null){
            instance = new GlobalScanner();
        }
        return instance;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public void closeScanner(){
        scanner.close();
    }
}
