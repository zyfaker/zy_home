package Tree;

import java.util.*;

public class fatherFind {
	private static Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
	private static class Tree{
		int fatherV;
		int value;
		List<Tree> son;
		Tree(int fatherV,int value,List<Tree> son){
			this.fatherV = fatherV;
			this.value = value;
			this.son = son;
		}
	}
	public static void main(String[] args){
		Scanner sin = new Scanner(System.in);
		int n = sin.nextInt();

		int[][] nums = new int[n][2];
		boolean[] numsStates = new boolean[n];
		int value = 0;
		for(int i =0;i<n;i++){
			int a = sin.nextInt();
			int b = sin.nextInt();
			if(b==-1){
				value = a;
				numsStates[i] = true;
			}
			nums[i][0] = a;
			nums[i][1] = b;
		}
		List<Tree> sons = buildTree(n,numsStates,nums,value);
		Tree tree = new Tree(0,value,sons);
		buildMap(tree);

		int m = sin.nextInt();
		for (int i = 0;i<m;i++){
			int a = sin.nextInt();
			int b = sin.nextInt();
			if(map.get(a)!=null&&map.get(a).contains(b)){
				System.out.println(2);
				continue;
			}
			if(map.get(b)!=null&&map.get(b).contains(a)){
				System.out.println(1);
				continue;
			}
			System.out.println(0);
		}
	}

	private static void buildMap(Tree tree) {

		if(tree.fatherV==0){
			map.put(tree.value,null);
		}
		if(tree.son==null){
			return;
		}
		for (Tree tree1:tree.son) {
			List<Integer> fathers= map.get(tree1.fatherV);
			if(fathers==null){
				fathers = new ArrayList<Integer>();
			}
			List<Integer> fathersTemp = new ArrayList<>();
			fathersTemp.addAll(fathers);
			fathersTemp.add(tree1.fatherV);
			map.put(tree1.value,fathersTemp);
			buildMap(tree1);
		}
	}

	private static List<Tree> buildTree(int n, boolean[] numsStates,int[][] nums, int treeValue) {

		List<Tree> sons = new ArrayList<>();

		for(int i = 0;i<n;i++){
			if(numsStates[i]){
				continue;
			}
			if(nums[i][0]==treeValue){
				numsStates[i] = true;
				int value = nums[i][1];
				int fatherV = treeValue;
				List<Tree> sonss = buildTree(n,numsStates,nums,value);
				Tree tree1 = new Tree(fatherV,value,sonss);
				sons.add(tree1);
				continue;
			}
			if(nums[i][1]==treeValue){
				numsStates[i] = true;
				int value = nums[i][0];
				int fatherV = treeValue;
				List<Tree> sonss = buildTree(n,numsStates,nums,value);
				Tree tree1 = new Tree(fatherV,value,sonss);
				sons.add(tree1);
			}
		}
		return sons;
	}
}
