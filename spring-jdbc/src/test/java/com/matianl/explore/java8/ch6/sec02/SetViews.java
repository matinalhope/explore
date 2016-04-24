package com.matianl.explore.java8.ch6.sec02;

import java.util.*;
import java.util.concurrent.*;

public class SetViews {
   public static void main(String[] args) {
      Set<String> words = ConcurrentHashMap.<String>newKeySet();

      ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
      words = map.keySet(1L);
      words.add("Java");
      System.out.println(map.get("Java"));
   }
}
