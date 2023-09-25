package com.example.cleancode.chapter04;

/**
 * 소수를 구하는 클래스이다
 * 사용자로부터 최대값(maxValue)을 입력받아, 최대값까지의 소수를 구한다
 * 에라토스테네스의 체 알고리즘을 사용한다
 * */

public class PrimeGenerator {
	private static boolean[] crossedOut;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if (maxValue < 2) {
			return new int[0];
		}

		crossOutIntegerUpTo(maxValue);
		crossOutMultiples();
		putUncrossedIntegerIntoResult();

		return result;
	}

	private static void putUncrossedIntegerIntoResult() {
		result = new int[countNotCrossedOut()];
		for (int i = 2, j = 0; i < crossedOut.length; i++) {
			if (isNotCrossedOut(i)) {
				result[j++] = i;
			}
		}
	}

	private static int countNotCrossedOut() {
		int count = 0;
		for (int i = 2; i < crossedOut.length; i++) {
			if (isNotCrossedOut(i)) {
				count++;
			}
		}
		return count;
	}

	private static void crossOutMultiples() {
		int iterationLimit = determineIterationLimit();

		for (int i = 2; i <= iterationLimit; i++) {
			if (isNotCrossedOut(i)) {
				crossOutMultiplesOf(i);
			}
		}
	}

	private static int determineIterationLimit() {
		// 배열에 있는 모든 배수는 배열의 크기보다 작다
		// 따라서 이 제곰근 보다 큰 배수는 제거할 필요가 없다
		return (int)Math.sqrt(crossedOut.length);
	}

	private static void crossOutMultiplesOf(int i) {
		for (int j = 2 * i; j < crossedOut.length; j += i) {
			crossedOut[j] = true;
		}
	}

	private static boolean isNotCrossedOut(int i) {
		return !crossedOut[i];
	}

	private static void crossOutIntegerUpTo(int maxValue) {
		crossedOut = new boolean[maxValue + 1];
		for (int i = 2; i < crossedOut.length; i++) {
			crossedOut[i] = false;
		}
	}

	public static void main(String[] args) {
		for (int i : generatePrimes(50)) {
			System.out.print(i + " ");
		}
	}
}
