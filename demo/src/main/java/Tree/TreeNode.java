package Tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/7 15:14
 */
@ToString
public class TreeNode implements Cloneable{
	@Setter
	@Getter
	private int value;
	@Setter
	@Getter
	private TreeNode left;
	@Setter
	@Getter
	private TreeNode right;

	TreeNode(int value,TreeNode left,TreeNode right){
		this.value = value;
		this.left = left;
		this.right = right;
	}

	@Override
	public Object clone(){
		TreeNode tree = null;
		try {
			tree = (TreeNode) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		tree.left = (TreeNode) left.clone();
		tree.right = (TreeNode) right.clone();

		return tree;
	}

}
