package org.example;

import java.io.*;
import java.util.*;

public class Main {

    static final String FILE = "tasks.txt";
    static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Показать  2. Добавить  3. Удалить  0. Выход");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("1")) show();
            else if (cmd.equals("2")) {
                System.out.print("Задача: ");
                String t = sc.nextLine().trim();
                if (!t.isEmpty()) {
                    tasks.add(t);
                    save();
                    System.out.println("Добавлено");
                }
            }
            else if (cmd.equals("3")) {
                show();
                if (!tasks.isEmpty()) {
                    System.out.print("Номер: ");
                    try {
                        int i = Integer.parseInt(sc.nextLine().trim()) - 1;
                        if (i >= 0 && i < tasks.size()) {
                            System.out.println("Удалено: " + tasks.remove(i));
                            save();
                        }
                    } catch (Exception e) {}
                }
            }
            else if (cmd.equals("0")) {
                System.out.println("Пока!");
                break;
            }
        }
    }

    static void load() {
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null)
                if (!line.trim().isEmpty()) tasks.add(line.trim());
        } catch (Exception ignored) {}
    }

    static void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            for (String t : tasks) {
                w.write(t);
                w.newLine();
            }
        } catch (Exception ignored) {}
    }

    static void show() {
        if (tasks.isEmpty()) {
            System.out.println("Пусто");
            return;
        }
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("%2d. %s%n", i + 1, tasks.get(i));
    }
}
