package com.matianl.explore.java8.ch2.sec05;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class MyTest {
   public static <T> void show(String title, Stream<T> stream) {
      final int SIZE = 10;
      List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
      System.out.print(title + ": ");
      if (firstElements.size() <= SIZE)
         System.out.println(firstElements);
      else {
         firstElements.remove(SIZE);
         String out = firstElements.toString();            
         System.out.println(out.substring(0, out.length() - 1) + ", ...]");
      }
   }

   public static void main(String[] args) throws IOException {
      Stream<String> uniqueWords
         = Stream.of("merrily", "merrily", "merrily", "gently", "abc", "bbbbbbbbb334", "aba").distinct();
      
      uniqueWords.distinct().sorted().forEach(s -> System.out.println(s));
      
   }
}


