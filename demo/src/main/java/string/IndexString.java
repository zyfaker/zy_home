package string;

import java.util.Scanner;

/**
 * indexString class
 *
 * @author zhangyuan33@jd.com
 * @date 2018/9/11 13:59
 */
public class IndexString {

	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		String str = sin.nextLine();
		int[] numbers = new int[256];
		while (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				numbers[str.charAt(i)] += 1;
			}
			str = sin.nextLine();
		}
		int start = 'a';
		int end = 'z';
		StringBuffer sb = new StringBuffer();
		for (int i = start; i <= end; i++) {
			if (numbers[i] != 0) {
				sb.append((char) i);
				sb.append(numbers[i]);
			}
		}
		System.out.println(sb.toString());
		sin.close();
	}
}
