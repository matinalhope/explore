package com.matianl.explore.java8.ch2.sec03;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class MyTest {
	
	public static Stream<Character> characterStream(String s) {
	      List<Character> result = new ArrayList<>();
	      for (char c : s.toCharArray()) result.add(c);
	      return result.stream();
	   }

	public static void main(String[] args) throws IOException {
		Stream<String> song = Stream.of("row", "row", "row", "your", "boat", "gently", "down", "the", "stream");
		Stream<Stream<Character>> map = song.map(s -> characterStream(s));
		
//		Stream<Character> flatMap = song.flatMap(w -> {
//			List<Character> result = new ArrayList<>();
//		      for (char c : w.toCharArray()) result.add(c);
//		      return result.stream();
//		});
//		flatMap.forEach(s -> System.out.println(s));
	}
}
