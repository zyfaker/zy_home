package string;
import java.util.*;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/6 15:00
 */
public class StringSolution {
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		String str = sin.nextLine();
		//1、替换空格
		//str = replaceSpace(str,"%20");

		//转换大小写upperCase
		//str.toLowerCase();

		//2、最长前缀匹配
		//String[] newStr = str.split("%20");
		//str = longestCommonPrefix(newStr);

		//3、给定字符最长回文串
		//int number = longestPalindrome(str);

		//4、判断是不是回文串
		//boolean state = isPalindrome(str);

		//5、最长回文串（可能有多个，这种的针对连续区间的统计，应该反过来统计，即从每一位置开始）
		Set<String> res = getMaxPalindrome(str);
		res.forEach((String s) -> {
			System.out.println(s);
		});

	}

	//构建辅助类
	private static class Palindrome {
		String str;
		int length;

		Palindrome(String a, int b) {
			this.str = a;
			this.length = b;
		}
	}


	private static Set<String> getMaxPalindrome(String str) {
		Set<String> res = new HashSet<>();

		if (str == null || str.length() == 0) {
			return null;
		}
		int length = str.length();
		if (length == 1) {
			res.add(str);
			return res;
		}
		//针对每一个字符，返回他的最长回文的起始和终止位置；
		int index = 0;//记录位置
		int maxLength = 0;//记录最长记录长度
		while (index < length) {
			//过滤非数字字母
			while (index < length && !Character.isLetterOrDigit(str.charAt(index))) {
				index++;
			}
			//计算当前位置为中间位置的回文串对象,字符串保留符号，但是有效长度只计算数字和字母
			Palindrome ps1 = PalindromeAssInit(str, index, index, length);
			if (maxLength == ps1.length) {
				res.add(ps1.str);
			} else if (maxLength < ps1.length) {
				res.clear();
				res.add(ps1.str);
				maxLength = ps1.length;
			}
			//计算当前位置和相邻位置为最中间
			Palindrome ps2 = PalindromeAssInit(str, index, index + 1, length);
			if (maxLength == ps2.length) {
				res.add(ps2.str);
			} else if (maxLength < ps2.length) {
				res.clear();
				res.add(ps2.str);
				maxLength = ps2.length;
			}
			index++;
		}
		return res;
	}

	private static Palindrome PalindromeAssInit(String str, int start, int end, int length) {

		StringBuffer sb = new StringBuffer();
		int effLength = 0;
		while (start >= 0 && end < length) {

			sb.reverse();
			while (start >= 0 && !Character.isLetterOrDigit(str.charAt(start))) {
				sb.append(str.charAt(start));
				start--;
			}
			sb.reverse();
			while (end < length && !Character.isLetterOrDigit(str.charAt(end))) {
				sb.append(str.charAt(end));
				end++;
			}

			if (start >= 0 && end < length && start == end) {
				sb.append(str.charAt(start));
				effLength++;
				start--;
				end++;
				continue;
			}

			if (start >= 0 && end < length && Character.toLowerCase(str.charAt(start)) != Character.toLowerCase(str.charAt(end))) {
				break;
			}

			if (start >= 0 && end < length) {
				sb.append(str.charAt(end));
				sb.reverse();
				sb.append(str.charAt(start));
				sb.reverse();
			}
			effLength+=2;
			start--;
			end++;
		}
		return new Palindrome(sb.toString(),effLength);
	}

	private static boolean isPalindrome(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		//是否忽略大小写
		int l = 0, r = str.length() - 1;
		while (l < r) {
			while (l < r && !Character.isLetterOrDigit(str.charAt(l))) {
				l++;
			}
			while (l < r && !Character.isLetterOrDigit(str.charAt(r))) {
				r--;
			}
			if (l < r && Character.toLowerCase(str.charAt(l)) != Character.toLowerCase(str.charAt(r))) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}

	private static int longestPalindrome(String str) {

		if (str == null || str.length() == 0) {
			return 0;
		}
		StringBuffer sb = new StringBuffer();

		//构建一个Set
		HashSet<Character> cSet = new HashSet<>();

		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			if (cSet.contains(str.charAt(i))) {
				cSet.remove(str.charAt(i));
				num++;
				continue;
			}
			cSet.add(str.charAt(i));
		}
		return cSet.isEmpty() ? 2 * num : 2 * num + 1;
	}

	private static String longestCommonPrefix(String[] newStr) {
		if (newStr == null || newStr.length == 0) {
			return "";
		}
		int length = newStr.length;
		if (length == 1) {
			return newStr[0];
		}
		//先排序
		if (length != 2) {
			Arrays.sort(newStr);
		}

		//对最后一个和第一个匹配
		StringBuffer sb = new StringBuffer();
		String head = newStr[0];
		String tail = newStr[length - 1];
		int i = 0;
		int max = head.length() > tail.length() ? tail.length() : head.length();
		while (i < max) {
			if (head.charAt(i) == tail.charAt(i)) {
				sb.append(head.charAt(i));
			} else {
				break;
			}
			i++;
		}
		return sb.toString();
	}

	private static String replaceSpace(String str, String rs) {
		StringBuffer sb = new StringBuffer();

		if (str == null) {
			return "";
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				sb.append(rs);
				continue;
			}
			sb.append(str.charAt(i));
		}
		//装逼大法，从后往前，然后sb.reverse().toString()
		return sb.toString();
	}
}
