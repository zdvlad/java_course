package ru.course;

public class MyFirstProgram {

    public static void main(String[] args) {
        // System.out.println("Hello World");


        Point p1 = new Point();//создание точки p1
        p1.x = 2;
        p1.y = 7;

        Point p2 = new Point();//создание точки p2
        p2.x = -2;
        p2.y = 7;

        System.out.println("Расстояние между двумя точками х и у = " + distance(p1, p2) );
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}