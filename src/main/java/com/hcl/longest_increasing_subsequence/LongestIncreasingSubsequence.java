package com.hcl.longest_increasing_subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Definition of Longest Increasing Subsequence as defined by our instructor
 * from SimpliLearn: An increasing subsequence that is not interrupted which
 * means it is continuous. For Example: { 2, 3, 4, 6, 1, 9, 12, 14, 16, 37, 99,
 * 98, 97, 0, -1, 10 }; The longest increasing subsequence is {1, 9, 12, 14, 16,
 * 37, 99}
 */

public class LongestIncreasingSubsequence {

	private int[] numbers;

	public LongestIncreasingSubsequence(int length) {
		this.numbers = new int[length];
		fillArray();
	}

	public Integer[] getLongestIncreasingSubsequence() {

		// Sequences we find
		List<Integer[]> sequences = new ArrayList<>();

		// Our method of recursion
		// helps to keep track of what we've seen previously
		Stack<Integer> entries = new Stack<>();

		// step through our numbers
		for (int r = 0; r < this.numbers.length; r++) {

			// Our number we're looking at
			Integer entry = this.numbers[r];

			// If this is our first number, we just add it and move on
			if (entries.empty()) {
				entries.push(entry);
				continue;
			}

			// The previous number
			Integer previous = entries.peek();

			// If our number is higher than the previous - add it to our stack
			if (previous < entry) {
				entries.push(entry);
			}

			// if the number is decreasing
			// or if we are at the end
			// then capture the sequence
			boolean atTheEnd = (r == this.numbers.length - 1);
			if (previous >= entry || atTheEnd) {

				// Capture the sequence we found
				sequences.add(toArray(entries));

				// Starting over so we clear out stack
				// Start the next sequence
				entries.push(entry);

			}
		}

		return longest(sequences);
	}

	public Integer[] longest(List<Integer[]> candidates) {
		TreeMap<Integer, Integer[]> byLength = new TreeMap<>();
		for (Integer[] candidate : candidates) {
			byLength.put(candidate.length, candidate);
		}
		return byLength.lastEntry().getValue();
	}

	private Integer[] toArray(Stack<Integer> entries) {
		Integer[] sequence = new Integer[entries.size()];
		int idx = entries.size() - 1;
		Integer sequenceEntry = entries.pop();
		while (sequenceEntry != null) {
			sequence[idx] = sequenceEntry;
			try {
				sequenceEntry = entries.pop();
			} catch (EmptyStackException e) {
				break;
			}
			idx--;
		}
		return sequence;
	}

	private void fillArray() {
		Random random = new Random();
		for (int i = 0; i < this.numbers.length; i++) {
			int num = random.nextInt(100);
			this.numbers[i] = num;
		}

	}

	public void printArray() {
		System.out.println(Arrays.toString(this.numbers));
	}

	public static void main(String[] args) {
		int lengthOfArray = 20; // change the length of the array here
		LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence(lengthOfArray);
		System.out.print("Original Sequence: ");
		obj.printArray();
		System.out.print("Longest Subsequence: ");
		System.out.println(Arrays.toString(obj.getLongestIncreasingSubsequence()));
	}

}