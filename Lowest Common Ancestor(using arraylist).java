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
            if (!currVal.equals("-1")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if (i >= ip.length) break;
            currVal = ip[i];
            if (!currVal.equals("-1")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Node root = buildTree(s);
        int x = sc.nextInt();
        int y = sc.nextInt();
        Solution g = new Solution();
        Node ans = g.findLCA(root,x,y);
        System.out.println(ans.data);
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
	static ArrayList<Node> n2r;
	public static boolean getN2R(Node node , int n){
		if(node == null){
			return false;
		}
		if(node.data == n){
			n2r.add(node);
			return true;
		}
		boolean left = getN2R(node.left, n);
		if(left == true){
			n2r.add(node);
			return true;
		}
		boolean right = getN2R(node.right, n);
		if(right == true){
			n2r.add(node);
			return true;
		}

		return false;
	}
    public static Node findLCA(Node node,int n1,int n2) {
        n2r = new ArrayList<>();
		getN2R(node, n1);
		ArrayList<Node> Node2RootFor_n1 = n2r;

		n2r = new ArrayList<>();
		getN2R(node, n2);
		ArrayList<Node> Node2RootFor_n2 = n2r;

		Node ans = new Node(0);

		int i = Node2RootFor_n1.size()-1;
		int j = Node2RootFor_n2.size()-1;
		while(i>=0 && j>=0){
			if(Node2RootFor_n1.get(i)==Node2RootFor_n2.get(j)){
				ans = Node2RootFor_n1.get(i);
				i--;
				j--;
			}else{
				break;
			}
		}
		return ans;
    }
}
