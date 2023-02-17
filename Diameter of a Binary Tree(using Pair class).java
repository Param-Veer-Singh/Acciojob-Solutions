import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;



class Main {
    static Node buildTree(String str) {
        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }
        String ip[] = str.split(" ");
        Node root = new Node(Integer.parseInt(ip[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (queue.size() > 0 && i < ip.length) {
            Node currNode = queue.peek();
            queue.remove();
            String currVal = ip[i];
            if (!currVal.equals("N")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if (i >= ip.length) break;
            currVal = ip[i];
            if (!currVal.equals("N")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        Node root1 = buildTree(s1);
        Solution g = new Solution();
        System.out.println(g.diameter(root1));
    }
}


class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    static class Pair{
		int height;
		int dia;
		Pair(){
			this.height = 0;
			this.dia = 0;
		}
		Pair(int height, int dia){
			this.height = height;
			this.dia = dia;
		}
	}
	public static Pair diameter2(Node root){
		if(root == null){
			return new Pair();
		}

		Pair leftNode = diameter2(root.left);
		Pair rightNode = diameter2(root.right);

		int leftHeight = leftNode.height;
		int rightHeight = rightNode.height;

		int maxHeight = Math.max(leftHeight, rightHeight)+1;

		int diaPassingThru = leftHeight + 1 + rightHeight;

		int maxDia = Math.max(diaPassingThru,(Math.max(leftNode.dia,rightNode.dia)));

		return new Pair(maxHeight, maxDia);
	}
    public static int diameter(Node root) {
        Pair ans = diameter2(root);
		return ans.dia;
    }
}
