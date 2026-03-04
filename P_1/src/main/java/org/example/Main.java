package org.example;
public class Main {

    public static String getAgeCategory(int age) {
        if (age < 0) {
            return "возраст не может быть отрицательным";
        }

        if (age <= 17) {
            return "ребенок";
        } else if (age <= 64) {
            return "взрослый";
        } else {
            return "пенсионер";
        }
    }

    public static void main(String[] args) {
        System.out.println(getAgeCategory(12));     // ребенок
        System.out.println(getAgeCategory(25));     // взрослый
        System.out.println(getAgeCategory(67));     // пенсионер
    }
}