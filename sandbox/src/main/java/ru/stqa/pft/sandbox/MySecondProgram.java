package ru.stqa.pft.sandbox;

/**
 * Created by Natusik on 20.10.2016.
 */
public class MySecondProgram {
  public static void main(String[] args){

    Point p1 = new Point(5,3);
    Point p2 = new Point(-10,-10);
    /*вычисление расстояния при помощи функции
    System.out.println("Расстояние между точками p1 и p2 = " + distance(p1,p2));*/

    /*вычисление расстояния при помощи метода класса*/
    System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));

  }

  /*public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
  }*/
}
