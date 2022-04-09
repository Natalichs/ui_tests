package com.geekbrains.hw4;

public class Triangle {
    private int a,b,c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public static boolean isTriangle(int a, int b, int c) throws NotTriangleExeption{
        if(a + b > c && a + c > b && b + c > a)
            return  true;
        else {
            throw new NotTriangleExeption("Фигура со стрононами " + a + ", " + b + ", " + c + " не может быть треугольником");
        }

    }
    public static float triangleSquare(int a, int b, int c) throws NotTriangleExeption {
        isTriangle(a, b, c);
        return (a + b + c)/2.0f;
    }

}
