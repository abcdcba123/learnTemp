package Beautiful;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BeautifulNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int cases = in.nextInt();
		for (int i = 1; i < cases; ++i) {
			int n = in.nextInt();
			System.out.println("Case #" + i + ": " + beautiful(n));
		}
	}

	private static int beautiful(int n) {
		for (int radix = 2; radix < n; radix++) {
			System.out.println("Case #" + radix + ": ");
			if (isBeautiful(n, radix)) {
				return radix;
			}
		}
//		return n - 1;
		throw new IllegalStateException("Should not reach here.");
	}

	private static boolean isBeautiful(int n, int radix) {
		while (n > 0) {
			if (n % radix != 1) {
				return false;
			}
			n /= radix;
		}
		return true;
	}
}
