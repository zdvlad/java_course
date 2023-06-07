package ru.course;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(2, 7);//создание первой точки в классе Point
        Point p2 = new Point( -2, 7);//создание второй точки в классе Point

        System.out.println("Расстояние между двумя точками х и у = " + p1.distance(p2));
    }
}