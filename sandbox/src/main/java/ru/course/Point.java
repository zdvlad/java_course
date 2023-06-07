package ru.course;

public class Point {
    //х и у - координаты точки на плоскости
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //метод определения расстояния между двумя точками
    public double distance(Point p2) {
        return Math.sqrt(Math.pow(p2.x - this.x, 2) + Math.pow(p2.y - this.y, 2));
    }
}
