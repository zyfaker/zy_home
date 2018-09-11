package jvm;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/11 10:26
 */
public class tryFinallyTest extends Test {
	static {
		System.out.print("D");
	}

	public static void main(String[] args) {
		Test rr = new tryFinallyTest();
		rr.main0();
		System.out.println(test());

	}

	private static String test() {
		try {
			System.out.print("A");
			System.exit(1);
			return "X";
		} finally {
			System.out.print("B");
			return "Y";
		}

	}

}

class Test {
	public void main0() {
		System.out.print("E");
	}

	static {
		System.out.print("F");
	}
}

