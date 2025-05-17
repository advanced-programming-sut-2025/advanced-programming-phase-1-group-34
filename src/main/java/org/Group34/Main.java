package org.Group34;

import org.Group34.view.AppView;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppView appView = new AppView();
        appView.run(scanner);
    }
}
