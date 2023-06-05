package ru.course;

public class Point {
    //х1 и у1 - координаты первой точки на плоскости
    //х2 и у2 - координаты второй точки на плоскости
    double x1;
    double y1;
    double x2;
    double y2;

    public Point(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    //метод определения расстояния между двумя точками
    public double distance() {
        return Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
    }
}
