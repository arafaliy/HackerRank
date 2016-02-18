/* Node is defined as :
typedef struct node
{
    int val;
    struct node* left;
    struct node* right;
    int ht;
} node; */

public class SelfBalancingTree{
  
//halper methods 
  /**
   * returns hight of node 
   */	
  static int hight(Node node){
  	if(node == null){
  		return 0;
  	}
  	return node.ht;
  }

  /**
   * Balance factor - Balance factor = hight of left sub tree - hight of right subtree
   */
  static int BalanceFactor(Node node){
  	return hight(node.left)-hight(node.right);
  }

  static int max(int leftHight , int rightHight){
    return  (leftHight>rightHight) ? leftHight:rightHight;
  }

  /**
   * left rortation
   */
   static Node leftRotation(Node node){
      //rotation
      Node temp = node.right;
      node.right = temp.left;
      temp.left = node;
      
      Node newRoot = temp; 
      //update hight of node
      node.ht = max(hight(node.left),hight(node.right))+1;

      //update hight on ne root node 
      newRoot.ht = max(hight(newRoot.left),hight(newRoot.right)) +1;

      return newRoot;

   }
   
   static Node righRotation(Node node){
      //rotation
      Node temp = node.left;
      node.left = temp.right;
      temp.right = node;
      
      Node newRoot = temp; 
      //update hight of node
      node.ht = max(hight(node.left),hight(node.right))+1;

      //update hight on ne root node 
      newRoot.ht = max(hight(newRoot.left),hight(newRoot.right)) +1;

      return newRoot;
  
   }

  static Node insert(Node root, int key){
    //insertion of node as simple binary tree
    if(root == null){
      root = new Node();
      root.val=key;   //Value
      root.ht=1;      //Height
    }
    else if(root.val > key){
      root.left = insert(root.left,key);     
    }
    else 
    {
      root.right = insert(root.right,key);
    }

    //update hight of current node 
    root.ht = max(hight(root.left),hight(root.right)) + 1;
    
    //balancer factor 
    int balance = BalanceFactor(root);

    if(balance< -1 && key < root.right.val){
       root.right=righRotation(root.right);
       return leftRotation(root);
    }
    
    if(balance< -1 && key > root.right.val){
      return leftRotation(root);
    }
    
    if(balance > 1 && key > root.left.val ){
      root.left=leftRotation(root.left);
      return righRotation(root);
    }

    if(balance >1 && key < root.left.val){
      return righRotation(root);
    }

    return root;
  }
}

 class Node {
       int val;   //Value
       int ht;      //Height
       Node left;   //Left child
       Node right;   //Right child
}

