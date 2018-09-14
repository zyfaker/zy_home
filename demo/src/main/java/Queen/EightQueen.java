package Queen;

import java.util.Date;


/**
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * 下面使用递归方法解决
 *
 * @author zhangyuan33@jd.com
 * @date 2018/9/13 18:06
 */
public class EightQueen {

	/**
	 * 使用常量来定义，方便之后解N皇后问题
	 */
	private static final short N = 8;
	/**
	 * 结果计数器
	 */
	private static int count = 0;

	public static void main(String[] args) {
		Date begin = new Date();
		short[][] chess = new short[N][N];
		boolean[] left = new boolean[N];
		boolean[] up = new boolean[N];
		boolean[] leftTop = new boolean[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				chess[i][j] = 0;
			}
			left[i] = false;
			up[i] = false;
			leftTop[i] = false;
		}
		putQueenAtRow(chess, 0,left,up,leftTop);
		Date end = new Date();
		System.out.println("解决 " + N + " 皇后问题，用时：" + String.valueOf(end.getTime() - begin.getTime()) + "毫秒，计算结果：" + count);
	}

	private static void putQueenAtRow(short[][] chess, int row, boolean[] left, boolean[] up, boolean[] leftTop) {
		/**
		 * 递归终止判断：如果row==N，则说明已经成功摆放了8个皇后
		 * 输出结果，终止递归
		 */
		if (row == N) {
			count++;
			System.out.println("第 " + count + " 种解：");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(chess[i][j] + " ");
				}
				System.out.println();
			}
			return;
		}

		short[][] chessTemp = chess.clone();
		boolean[] leftTemp = left.clone();
		boolean[] upTemp = up.clone();
		boolean[] leftTopTemp = leftTop.clone();

		/**
		 * 向这一行的每一个位置尝试排放皇后
		 * 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
		 */
		for (int i = 0; i < N; i++) {
			//摆放这一行的皇后，之前要清掉所有这一行摆放的记录，防止污染棋盘
			for (int j = 0; j < N; j++) {
				chessTemp[row][j] = 0;
			}
			chessTemp[row][i] = 1;

			if (isSafety(chessTemp, row, i)) {
				putQueenAtRow(chessTemp, row + 1, leftTemp, upTemp, leftTopTemp);
			}
		}
	}

	private static boolean isSafety(short[][] chess, int row, int col) {
		//判断中上、左上、右上是否安全
		int step = 1;
		while (row - step >= 0) {
			if (chess[row - step][col] == 1) {
				return false;
			}
			if (col - step >= 0 && chess[row - step][col - step] == 1) {
				return false;
			}
			if (col + step < N && chess[row - step][col + step] == 1) {
				return false;
			}
			step++;
		}
		return true;
	}
}