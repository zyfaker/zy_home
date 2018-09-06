package demo.jingdong.diary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class binaryTree {

	private static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		TreeNode(int a) {
			value = a;
		}
	}

	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		System.out.println("请输入前序遍历的结果：");
        //4 6 3 5 8 9 7
		String[] pre = sin.nextLine().trim().split(" ");

		int[] preOrder = new int[pre.length];

		for (int i = 0; i < pre.length; i++) {
			preOrder[i] = Integer.parseInt(pre[i]);
		}

		System.out.println("请输入中序遍历的结果：");
		//3 6 5 4 9 8 7
		String[] in = sin.nextLine().trim().split(" ");

		int[] inOrder = new int[in.length];

		for (int i = 0; i < in.length; i++) {
			inOrder[i] = Integer.parseInt(in[i]);
		}

		TreeNode tree = buildTree(preOrder, inOrder);
		System.out.println("后序遍历的结果为：");
		backOrder(tree);
		System.out.print("\n");

		System.out.println("广度优先遍历的结果为：");
		try {
			wideOrder(tree);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//广度优先遍历
	private static List<Integer> wideOrder(TreeNode tree) throws InterruptedException {
		List<Integer> wild = new ArrayList<>();
		if (tree == null) return null;
		LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();

		queue.put(tree);

		while (queue.peek() != null) {
			TreeNode root = queue.peek();

			if (root.left != null) {
				queue.put(root.left);
			}
			if (root.right != null) {
				queue.put(root.right);
			}
			wild.add(queue.take().value);
		}
		return wild;
	}

	//后序遍历
	private static void backOrder(TreeNode tree) {

		if (tree == null) {
			return;
		}
		backOrder(tree.left);
		backOrder(tree.right);
		System.out.print(tree.value + " ");

	}

	//找到根节点的位置
	private static int findPosition(int[] array, int start, int end, int key) {
		for (int i = start; i <= end; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}

	private static TreeNode myBuildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
		if (inStart > inEnd) {
			return null;
		}
		//前序遍历序列的第一个数字就是根结点，创建根结点root
		TreeNode root = new TreeNode(preorder[preStart]);
		//在中序遍历序列中找到根结点的位置
		int position = findPosition(inorder, inStart, inEnd, preorder[preStart]);
		//构建左子树
		root.left = myBuildTree(preorder, preStart + 1, preStart + (position - inStart), inorder, inStart, position - 1);
		//构建右子树
		root.right = myBuildTree(preorder, preStart + (position - inStart) + 1, preEnd, inorder, position + 1, inEnd);
		return root;
	}

	private static TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length != inorder.length) {//前序遍历序列与中序遍历序列长度不等
			return null;
		}
		return myBuildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}
}
