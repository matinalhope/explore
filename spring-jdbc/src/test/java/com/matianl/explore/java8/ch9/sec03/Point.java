package com.matianl.explore.java8.ch9.sec03;

public class Point {
   private int x, y;

   public int compareTo(Point other) {
      int diff = Integer.compare(x, other.x); // No risk of overflow
      if (diff != 0) return diff;
      return Integer.compare(y, other.y);
   }
}
