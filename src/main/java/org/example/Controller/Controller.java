package org.example.Controller;

import org.example.Data.Toy;

import java.io.*;
import java.util.*;

public class Controller {

    Scanner sc = new Scanner(System.in);

    private PriorityQueue<Toy> toyQueue;
    public PriorityQueue<Toy> getAllToys() {
        return toyQueue;
    }

    public void addToy(Toy toy) {
        if (toy.getNumberOfToys() != 0)
        {
            for (int i = 0; i < toy.getNumberOfToys(); i++)
            {
                toyQueue.offer(toy);
            }
        }
        else
        {
            System.out.println("Toys " + toy.getName() + " ID " + toy.getId() + " are over!");
        }
    }

    public Controller() {
        toyQueue = new PriorityQueue<>((toy1, toy2) -> {
            if (toy1.getChance() == toy2.getChance())
            {
                return toy2.getChance() - toy1.getChance();
            }
            return toy1.getChance() - toy2.getChance();
        });
    }


    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Toy toy : toyQueue) {
                writer.write("ID " + toy.getId() + " ," + "Name " + toy.getName() + " ," + "Number of toys " + toy.getNumberOfToys() +
                        " ," + "DropChance " + toy.getChance() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Toy getToyById(int toyId) {
        for (Toy toy : toyQueue) {
            if (toy.getId() == toyId) {
                return toy;
            }
        }
        return null;
    }

    public void updateChance(int toyId, int newChance) {
        for (Toy toy : toyQueue) {
            if (toy.getId() == toyId) {
                toy.setChance(newChance);
                break;
            }
        }
    }

    public String getToy(int count) {
        Random random = new Random();
        StringBuilder transfStr = new StringBuilder();
        Queue<Toy> queue = new LinkedList<>(toyQueue);

        for (int i = 0; i < count; i++) {
            Toy toy = queue.poll();
            if (toy == null) {
                transfStr.append("No more toys!").append("\n");
                break;
            }

            if (toy.getChance() >= 100) {
                transfStr.append("Got toy with ").append(toy.getName()).append(" ID ").append(toy.getId()).append("\n");
            } else if (toy.getChance() >= 1 && toy.getChance() <= 99 && toy.getChance() > random.nextInt(100)) {
                transfStr.append("Got toy with ").append(toy.getName()).append(" ID ").append(toy.getId()).append("\n");
            } else {
                transfStr.append("Toy with ID ").append(toy.getId()).append(" did not drop.").append("\n");
            }
        }
        return transfStr.toString();
    }

    public void addResultToFile(String result, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
        {
            int count = 0;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while (reader.readLine() != null)
            {
                count++;
            }
            reader.close();

            writer.write("Raffle #" + (count + 1) + " - Result:" + "\n");
            writer.write(result + "\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadToysFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int numberOfToys = Integer.parseInt(parts[2]);
                int chance = Integer.parseInt(parts[3]);

                Toy toy = new Toy(id, name, numberOfToys, chance);
                addToy(toy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

