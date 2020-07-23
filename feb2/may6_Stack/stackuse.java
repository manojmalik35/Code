package may6;

import java.util.*;

import javax.swing.text.StyleContext.SmallAttributeSet;

public class stackuse {

	public static boolean hdb(String s) {// has duplicate brackets
		Stack<Character> st = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ')') {
				if (st.peek() == '(')
					return true;
				while (st.peek() != '(')
					st.pop();
				st.pop();
			} else
				st.push(c);
		}
		return false;
	}

	public static boolean hbb(String s) {// has balanced brackets
		Stack<Character> st = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[')
				st.push(c);
			else if (c == ')') {
				if (st.size() == 0 || st.peek() != '(')
					return false;
				else
					st.pop();
			} else if (c == '}') {
				if (st.size() == 0 || st.peek() != '{')
					return false;
				else
					st.pop();
			} else if (c == ']') {
				if (st.size() == 0 || st.peek() != '[')
					return false;
				else
					st.pop();
			}
		}
		if (st.size() != 0)
			return false;
		return true;
	}

	public static void psntfp(String s) {// print smallest number that follows
											// the pattern
		// every digit of the number should be unique
		Stack<Integer> st = new Stack<>();
		int n = 1;
		for (int i = 0; i < s.length(); i++) {
			st.push(n++);
			if (s.charAt(i) == 'i') {
				while (st.size() > 0) {
					int t = st.pop();
					System.out.print(t);
				}
			}
		}
		st.push(n++);
		while (st.size() > 0) {
			int t = st.pop();
			System.out.print(t);
		}
		System.out.println();
	}

	public static int[] nextGreaterElement(int[] arr) {
		int[] res = new int[arr.length];
		Stack<Integer> st = new Stack<>();
		res[arr.length - 1] = -1;
		st.push(arr[arr.length - 1]);
		for (int i = arr.length - 2; i >= 0; i--) {
			while (st.size() > 0 && st.peek() < arr[i])
				st.pop();

			res[i] = st.size() > 0 ? st.peek() : -1;
			st.push(arr[i]);
		}

		return res;
	}

	private static class Pair {
		int index;
		int value;

		Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	public static int[] stockspan(int[] arr) {
		Stack<Pair> st = new Stack<>();
		int[] span = new int[arr.length];
		span[0] = 1;
		Pair t = new Pair(0, arr[0]);
		st.push(t);
		for (int i = 1; i < arr.length; i++) {
			while (st.size() > 0 && st.peek().value < arr[i])
				st.pop();
			if (st.size() == 0)
				span[i] = i + 1;
			else
				span[i] = i - st.peek().index;
			st.push(new Pair(i, arr[i]));
		}
		return span;
	}

	public static void largestAreaHistogram(int[] arr) {
		Stack<Pair> lst = new Stack<>();
		Stack<Pair> rst = new Stack<>();
		int[] lb = new int[arr.length];
		int[] rb = new int[arr.length];
		// left side
		lb[0] = 0;
		lst.push(new Pair(0, arr[0]));
		for (int i = 1; i < arr.length; i++) {
			while (lst.size() > 0 && lst.peek().value > arr[i])
				lst.pop();
			if (lst.size() == 0)
				lb[i] = 0;
			else
				lb[i] = lst.peek().index + 1;
			lst.push(new Pair(i, arr[i]));
		}
		// right side
		rb[arr.length - 1] = arr.length - 1;
		rst.push(new Pair(arr.length - 1, arr[arr.length - 1]));
		for (int i = arr.length - 2; i >= 0; i--) {
			while (rst.size() > 0 && rst.peek().value > arr[i])
				rst.pop();

			rb[i] = rst.size() == 0 ? arr.length - 1 : rst.peek().index - 1;
			rst.push(new Pair(i, arr[i]));
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lb.length; i++) {
			int currarea = arr[i] * (rb[i] - lb[i] + 1);
			max = Math.max(max, currarea);
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		// String s="((a+b)+((d+e)))";
		// System.out.println(hdb(s));
		// System.out.println(hbb("(a+b)+(d+e)"));
		// psntfp("ddidddid");
		// psntfp("dddddddd");
		// psntfp("iiiiiiii");
		// psntfp("iddiddid");
		// int[] arr={1,9,7,3,2,6,4};
		// int[] res=nextGreaterElement(arr);
		// int[] span=stockspan(arr);
		// for(int i=0;i<span.length;i++)
		// System.out.print(span[i]+" ");
		int[] arr = { 6, 2, 5, 4, 5, 1, 6 };
		largestAreaHistogram(arr);
	}

}
