package thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/12 10:49
 */
public class FileSearchTask2 {
	private static int nThreads = 10;
	private static ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
	private static CompletionService<Map<File, Integer>> completionService = new ExecutorCompletionService(executorService);


	private static Integer findCount(File file, String keyword) {
		int count = 0;
		try (Scanner scanner = new Scanner(new FileInputStream(file))) {
			while (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				boolean exist = str.contains(keyword)&&(str.contains(" "+keyword+" ")||str.equals(keyword)||(str.contains(" "+keyword)&&str.endsWith(keyword))||(str.contains(keyword+" ")&&str.startsWith(keyword)));
				if (exist) {
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//获取文件路径
		String path = "E:\\software\\elasticsearch-2.3.5\\logs";
		//获取关键字
		String keyword = "to";

		File[] files = new File(path).listFiles();
		ArrayList<Future<Integer>> rs = new ArrayList<>();
		//每个文件启动一个task去查找
		for (final File file : files) {
			completionService.submit(new Callable<Map<File, Integer>>() {
				@Override
				public Map<File, Integer> call() throws Exception {

					Map<File, Integer> mapRes = new HashMap<>();

					mapRes.put(file, findCount(file, keyword));

					return mapRes;
				}
			});
		}
		//迭代返回结果

		Map<File, Integer> result = new HashMap<>();
		for (final File file : files) {
			Future<Map<File, Integer>> future = completionService.take();
			Map<File, Integer> map = future.get();
			if (map.isEmpty()) {
				map = new HashMap<>(16);
			}
			result.putAll(map);
		}

		for (Map.Entry<File, Integer> map : result.entrySet()) {
			System.out.println("文件：" + map.getKey().getName() + "含有数字" + map.getValue());
		}
	}
}

