package org.Group34;

import org.Group34.model.App;
import org.Group34.model.enums.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (App.getCurrentMenu() != Menu.EXIT_MENU){
            App.getAppMenu().run(scanner);
        }
    }
}
