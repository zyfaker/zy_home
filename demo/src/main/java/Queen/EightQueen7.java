package Queen;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;


/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/14 10:26
 */
public class EightQueen7 {
	private static final short K = 8;
	private static short N = 0;

	public static void main(String[] args) throws Exception {
		for (N = 1; N <= 17; N++) {
			long count = 0;
			Date begin = new Date();
			/**
			 * 初始化棋盘，使用一维数组存放棋盘信息
			 * chess[n]=X:表示第n行X列有一个皇后
			 */

			List<short[]> chessList = new ArrayList<short[]>(N);

			//short taskSize =(short)( N/2+(N%2==1?1:0) );

			for (short i = 0; i < N; i++) {
				short chess[] = new short[N];
				chess[0] = i;
				chessList.add(chess);
			}
			// 创建一个线程池
			ExecutorService pool = Executors.newFixedThreadPool(N);
			CompletionService<Long> completionService = new ExecutorCompletionService(pool);
			// 创建多个有返回值的任务
			for (short[] chess:chessList) {
				completionService.submit(new Callable<Long>(){
					@Override
					public Long call() throws Exception {
						return putQueenAtRow(chess, (short)1);
					}
				});
			}
			// 关闭线程池
			pool.shutdown();

			for (short[] chess:chessList) {
				Future<Long> future = completionService.take();
				count+=future.get();
			}
			Date end = new Date();
			System.out.println("解决 " + N + "皇后问题，用时：" + String.valueOf(end.getTime() - begin.getTime()) + "毫秒，计算结果：" + count);
		}
	}
	private static Long putQueenAtRow(short[] chess, short row) {
		if (row == N) {
			return (long) 1;
		}

		short[] chessTemp = chess.clone();
		long sum = 0;
		/**
		 * 向这一行的每一个位置尝试排放皇后
		 * 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
		 */
		for (short i = 0; i < N; i++) {
			//摆放这一行的皇后
			chessTemp[row] = i;

			if (isSafety(chessTemp, row, i)) {
				sum += putQueenAtRow(chessTemp, (short) (row + 1));
			}
		}

		return sum;
	}
	private static boolean isSafety(short[] chess, short row, short col) {
		//判断中上、左上、右上是否安全
		short step = 1;
		for (short i = (short) (row - 1); i >= 0; i--) {
			if (chess[i] == col) {
				return false;
			}
			if (chess[i] == col - step) {
				return false;
			}
			if (chess[i] == col + step) {
				return false;
			}
			step++;
		}

		return true;
	}
}

