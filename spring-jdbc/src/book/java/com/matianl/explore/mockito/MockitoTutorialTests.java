package com.matianl.explore.mockito;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MockitoTutorialTests {

	@Test
	public void test1() {
		//mock creation
		List mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	@Test
	public void test2() {
		LinkedList mockedList = mock(LinkedList.class);
		when(mockedList.get(0)).thenReturn("first1");
		when(mockedList.get(0)).thenReturn("first2");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		System.out.println(mockedList.get(0));

		//		System.out.println(mockedList.get(1));

		System.out.println(mockedList.get(999));

		//		verify(mockedList).get(0);
	}

	@Test
	public void test3() {
		LinkedList<String> mockedList = mock(LinkedList.class);
		when(mockedList.get(anyInt())).thenReturn("element");

		//		when(mockedList.contains(argThat(isValid()))).thenReturn(Boolean.FALSE);
		when(mockedList.contains(argThat(someString -> ((String) someString).length() > 5))).thenReturn(Boolean.TRUE);

		System.out.println(mockedList.get(999));

		verify(mockedList).get(anyInt());

		verify(mockedList).add(argThat(someString -> someString.toString().length() > 5));

	}

	private ArgumentMatcher<String> isValid() {
		return new ArgumentMatcher<String>() {

			@Override
			public boolean matches(String argument) {
				// TODO Auto-generated method stub
				return argument.length() <= 5;
			}
		};
	}

	@Test
	public void test4() {
		LinkedList<String> mockedList = mock(LinkedList.class);
		//using mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		//following two verifications work exactly the same - times(1) is used by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		//exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		//verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");

		//verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("five times");
		verify(mockedList, atMost(5)).add("three times");
	}

	@Test
	public void test5() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		doThrow(new RuntimeException()).when(mockedList).clear();

		//following throws RuntimeException:
		mockedList.clear();
	}

	@Test
	public void test6() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		// A. Single mock whose methods must be invoked in a particular order
		List singleMock = mock(List.class);

		//using a single mock
		singleMock.add("was added first");
		singleMock.add("was added second");

		//create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(singleMock);

		//following will make sure that add is first called with "was added first, then with "was added second"
		inOrder.verify(singleMock).add("was added first");
		inOrder.verify(singleMock).add("was added second");

		// B. Multiple mocks that must be used in a particular order
		List firstMock = mock(List.class);
		List secondMock = mock(List.class);

		//using mocks
		firstMock.add("was called first");
		secondMock.add("was called second");

		//create inOrder object passing any mocks that need to be verified in order
		InOrder inOrder2 = inOrder(firstMock, secondMock);

		//following will make sure that firstMock was called before secondMock
		inOrder2.verify(firstMock).add("was called first");
		inOrder2.verify(secondMock).add("was called second");

		// Oh, and A + B can be mixed together at will
	}

	@Test
	public void test7() {
		LinkedList<String> mockOne = mock(LinkedList.class);
		LinkedList<String> mockTwo = mock(LinkedList.class);
		LinkedList<String> mockThree = mock(LinkedList.class);

		//using mocks - only mockOne is interacted
		mockOne.add("one");

		//ordinary verification
		verify(mockOne).add("one");

		//verify that method was never called on a mock
		verify(mockOne, never()).add("two");

		//verify that other mocks were not interacted
		verifyZeroInteractions(mockTwo, mockThree);
	}

	@Test
	public void test8() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		//using mocks
		mockedList.add("one");
		mockedList.add("two");

		verify(mockedList).add("one");

		//following verification will fail
		verifyNoMoreInteractions(mockedList);
	}

	@Test
	public void test9() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test10() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		when(mockedList.get(0)).thenThrow(new RuntimeException()).thenReturn("one");

		//First call: throws runtime exception:
		mockedList.get(0);

		//Second call: prints "foo"
		System.out.println(mockedList.get(0));

		//Any consecutive call: prints "foo" as well (last stubbing wins).
		System.out.println(mockedList.get(0));
	}

	@Test
	public void test11() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		when(mockedList.get(anyInt())).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				Object mock = invocation.getMock();
				return "called with arguments: " + args;
			}
		});

		//the following prints "called with arguments: foo"
		System.out.println(mockedList.get(0));
	}

	@Test
	public void test12() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		doThrow(new RuntimeException()).when(mockedList).clear();

		//following throws RuntimeException:
		mockedList.clear();
	}

	@Test
	public void test13() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test14() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test15() {
		LinkedList<String> mockedList = mock(LinkedList.class);

		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		mockedList.add("aa");

		verify(mockedList).add(argument.capture());
		
		assertEquals("aa", argument.getValue());
	}

	@Test
	public void test16() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test17() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test18() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test19() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void test20() {
		LinkedList<String> mockedList = mock(LinkedList.class);

	}

	@Test
	public void testX() {
		
	}

}
