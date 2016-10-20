package ru.stqa.pft.sandbox;

/**
 * Created by Natusik on 19.10.2016.
 */
public class Square {

  public double l;

  public Square(double len){
    this.l=len;
  }

  public double area(){
    return this.l*this.l;
  }
}
