package org.example.View;
import org.example.Controller.Controller;
import org.example.Data.Toy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class View {
    public void start() {
        Scanner sc = new Scanner(System.in);
        Controller toyController = new Controller();
        toyController.loadToysFromFile("toys.txt");
        PriorityQueue<Toy> allToys = new PriorityQueue<>();
        boolean flag = true;
        while(flag){
            String menu = "\n1 - Добавить новую игрушку" +
                    "\n2 - Изменить частоту выпадения игрушки" +
                    "\n3 - Провести розыгрыш" +
                    "\n-1 -> Выход";
            System.out.println(menu);
            String choice = sc.nextLine();
            switch (choice){
                case "1": //Добавить новую игрушку
                    System.out.println("Введите id игрушки:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Введите название игрушки:");
                    String name = sc.nextLine();

                    System.out.println("Введите количество игрушек:");
                    int numberOfToys = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Введите частоту выпадения игрушки (от 0 до 100):");
                    int chance = sc.nextInt();
                    sc.nextLine();
                    toyController.addToy(new Toy(id, name, numberOfToys, chance));
                    toyController.saveToFile("toys.txt");
                    break;

                case "2": //Изменить частоту выпадения игрушки
                    System.out.println("Список всех игрушек:");
                    toyController.getAllToys();
                    for (Toy toy : allToys) {
                        System.out.println(toy.getId() + ". " + toy.getName());
                    }

                    System.out.println("Введите id игрушки для обновления веса:");
                    int toyId = sc.nextInt();
                    sc.nextLine();

                    // Проверка, существует ли игрушка с введенным ID
                    if (toyController.getToyById(toyId) != null) {
                        System.out.println("Введите новый вес игрушки:");
                        int newChance = sc.nextInt();
                        sc.nextLine();

                        toyController.updateChance(toyId, newChance);

                        System.out.println("Вес игрушки обновлен.");
                    } else {
                        System.out.println("Игрушка с указанным ID не найдена.");
                    }
                    break;

                case "3": //Провести розыгрыш
                    System.out.println("Введите количество попыток розыгрыша: \n");
                    int count = sc.nextInt();
                    sc.nextLine();
                    String result =  toyController.getToy(count);
                    toyController.addResultToFile(result, "raffleToy.txt");
                    break;

                case "-1": //Выход
                    flag = false;
                    break;
                //endregion
            }
        }
    }
}
