package demo.jingdong.diary;

import com.thoughtworks.xstream.XStream;

import java.util.Scanner;

public class Combtest {


	public static void main(String[] args) {

		Scanner sin = new Scanner(System.in);
		int n = sin.nextInt();
		int m = sin.nextInt();
		int index = sin.nextInt();
		TreeNode root = buildTree(n, m, 'H');

		XStream stream = new XStream();

		String xml = stream.toXML(root);
		System.out.println(xml);
		String rs = getIndexRes(index,root);
		System.out.println(rs.substring(1));
	}

	private static String getIndexRes(int index, TreeNode root) {

		String rs = "";
		Node node = root.getNode();
		if(index>node.getSonNum()){
			return "";
		}
		rs+=node.value;
		boolean isfind = false;
		if(root.left!=null){
			if(index>root.left.node.sonNum){
				rs+=getIndexRes(index-root.left.node.sonNum,root.right);
			}else{
				rs+=getIndexRes(index,root.left);
			}
			isfind = true;
		}
		if(!isfind&&root.right!=null){
			if(index<=root.right.node.sonNum){
				rs+=getIndexRes(index,root.right);
			}
		}
		return rs;
	}

	private static TreeNode buildTree(int n, int m, char value) {

		TreeNode root = new TreeNode();
		Node node = new Node();
		node.setValue(value);
		if (n * m == 0 && (n + m) == 0) {
			node.setSonNum(1);
			root.node = node;
			return root;
		}
		if (n > 0) {
			root.left = buildTree(n - 1, m, 'a');
		}
		if (m > 0) {
			root.right = buildTree(n, m - 1, 'z');
		}
		int number = 0;
		if (root.left != null) {
			number += root.left.node.getSonNum();
		}
		if (root.right != null) {
			number += root.right.node.getSonNum();
		}
		node.setSonNum(number);
		root.node = node;
		return root;

	}

	private static class Node {
		char value;
		int sonNum;

		public char getValue() {
			return value;
		}

		public void setValue(char value) {
			this.value = value;
		}

		public int getSonNum() {
			return sonNum;
		}

		public void setSonNum(int sonNum) {
			this.sonNum = sonNum;
		}
	}

	private static class TreeNode {
		TreeNode left;
		TreeNode right;
		Node node;

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public Node getNode() {
			return node;
		}

		public void setNode(Node node) {
			this.node = node;
		}
	}
}
