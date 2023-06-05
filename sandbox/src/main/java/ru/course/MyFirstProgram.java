package ru.course;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(2, 7, -2, 7);//создание точек в классе Point

        System.out.println("Расстояние между двумя точками х и у = " + p1.distance());
    }
}