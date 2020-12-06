/**
* This creates the actual binary tree object.
* This stores nodes with a left and right child.
* The right child comes after the parent node.
* The left child comes before the parent node. 
* The methods within this class are used to 
* insert, delete, and find specific countries
* based on the parameters given and print them
* out. 
*
* @author Camille Copeland
* @version 4/13/2020
*/
public class BinarySearchTree {
	public class Node{
	String name;
	 double gdpPerCapita;
	 Node leftChild;
	 Node rightChild;
	 
	 /**
	 * This method acts as a constructor for the Node
	 * object.
	 *
	 * @param the name parameter takes a String and assigns it 
	 * as the name of the country node
	 * @param the gdpPerCapita parameter assigns a value to the
	 * country node's gdp 
	 * @return none
	 */
	 public Node(String name, double gdpPerCapita) {
		 this.name = name;
		 this.gdpPerCapita = gdpPerCapita;
	 }
	 
	 /**
	 *The purpose of this method is to print both the country name
	 *and the country gdp of a node. 
	 *
	 * @param none
	 * @return none
	 */
	 public void printNode() {
		 System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
	 }
	}
	
	public Node root;
	
	/**
	*This is/ a constructor that initializes the root of a binary tree 
	*as null
	*
	* @param none
	* @return none
	*/
	public BinarySearchTree() {
		root=null;
	}
	
	/**
	* Description: this inserts the node into the binary tree. 
	* It uses a series of if statements to decide where the 
	* node is to be placed within the tree.
	*
	* @param takes string name and assigns it as a country
	* @param takes a double and assigns a gdp to the country
	* @return Node
	*/
	public Node insert(String name, double gdpPerCapita) {
	String x = name;

	      Node return_val = null;

	      if(root==null) {      
	    	 Node newNode = new Node(name, gdpPerCapita);    
	         root = newNode;
	         return_val = root;
	         }
	      else                         
	         {
	         Node current = root;       
	         Node parent;
	         while(current != null)                
	            {
	            parent = current;
	            if(x.compareTo(current.name) > 0)  
	               {
	               current = current.rightChild;
	               if(current == null)  
	                  {                 
	            	   Node newNode = new Node(name, gdpPerCapita);    
	                   return_val = newNode;

	                  parent.rightChild = newNode;
	                  }
	               }  
	            else if (x.compareTo(current.name) < 0)          // or go right?
	               {
	               current = current.leftChild;
	               if(current == null)  
	                  {                
	            	   Node newNode = new Node(name, gdpPerCapita);    // make new node
	                   return_val = newNode;

	                  parent.leftChild = newNode;
	                  }
	               }  
	            else current = null;                 

	            }  // end while
	         }  // end else not root

	         return return_val;

	      }  // end insert()
	
	
	/**
	* Description:
	*
	* @param The string acts as a key. The name passed 
	* into the parameter is supposed to match the name
	* of a node within the binary tree.
	* @return the gdp of the country that's been found
	*/
	 public double find(String name)      // find node with given key
     {  
		 int y = 0;
     Node current = root;               // start at root
     while(!name.equals(current.name))        // while no match,
        {
        if(name.compareTo(current.name)<0)         
           current = current.leftChild;
        else                            
        	current = current.rightChild;
        if(current == null)             
           return -1;   
        y++;
        }
     System.out.println(name + " is found with GDP per Capita " + current.gdpPerCapita);
     System.out.println(y + " nodes visited");
     return current.gdpPerCapita;                   
     }  // end find()
	 
	 /**
		* Description: This deletes the node based 
		* on the name given in the key. First the node
		* is found then the method reconstructs the tree
		* depending on whether the deleted node has zero,
		* one, or two children. 
		*
		* @param The string taken acts as a key to find 
		* the correct node to be deleted
		* @return none
		*/
	
	public void delete(String name) 
    {      
		String x = name;
		int flag = 0;
		

    if (root == null)
    	flag = 1; 
    
    Node current = root;
    
    Node parent = root;
    
    boolean isLeftChild = true;
    
    if(flag == 0) {
    
    while(!name.equals(current.name))       
       {    	
       parent = current;       
       if(x.compareTo(current.name) < 0)         
          {    	   
          isLeftChild = true;          
          current = current.leftChild;          
          }
       else                           
          {    	   
          isLeftChild = false;          
          current = current.rightChild;
          }
       if(current == null) {  
    	   System.out.println("The country can't be found.");
    	   flag = 1; 
    	   }
       // didn't find it
       }  // end while
    }
    // found node to delete

    if(flag == 0) {
    if(current.leftChild==null &&
                                 current.rightChild==null)
       {
       if(current == root)             // if root,
          root = null;                 // tree is empty
       else if(isLeftChild)
          parent.leftChild = null;     // disconnect
       else                            // from parent
          parent.rightChild = null;
       }

   
    else if(current.rightChild==null)
       if(current == root)
          root = current.leftChild;
       else if(isLeftChild)
          parent.leftChild = current.leftChild;
       else
          parent.rightChild = current.leftChild;

   
    else if(current.leftChild==null)
       if(current == root)
          root = current.rightChild;
       else if(isLeftChild)
          parent.leftChild = current.rightChild;
       else
          parent.rightChild = current.rightChild;

    else 
       {
       Node successor = getSuccessor(current);

       
       if(current == root)
          root = successor;
       else if(isLeftChild)
          parent.leftChild = successor;
       else
          parent.rightChild = successor;
       successor.leftChild = current.leftChild;  
       }  
    System.out.println(name + " has been deleted from tree");         
    }
    } // end delete()
	
	/**
	* Description: This finds the successor node. In the
	* event that a node has two children it needs a successor. 
	* This successor replaces the deleted node as the parent of
	* that deleted node's subtree. 
	*
	* @param delNode is the deleted node. Think of as the root
	* of that subtree
	* @return the successor node. The next parent of that subtree.
	*/
	
	private Node getSuccessor(Node delNode)
    {
    Node successorParent = delNode;
    Node successor = delNode;
    Node current = delNode.rightChild;   // go to right child
    while(current != null)               // until no more
       {                                 // left children,
       successorParent = successor;
       successor = current;
       current = current.leftChild;      // go to left child
       }
                                         // if successor not
    if(successor != delNode.rightChild)  // right child,
       {                                 // make connections
       successorParent.leftChild = successor.rightChild;
       successor.rightChild = delNode.rightChild;
       }
    return successor;
    }//get successor
	
	/**
	* Description: A preorder traversal, prints root, left, right.
	*
	* @param local root is a the root node of the subtree to be printed
	* @return none
	*/
	public void printPreorder(Node localRoot)
    {
    if(localRoot == null){
    	return;
    }
      localRoot.printNode();
       printPreorder(localRoot.leftChild);
       printPreorder(localRoot.rightChild);
       
    }
	
	/**
	* Description:Post order traversal. Prints left, right, root
	*
	* @param local root is the root nod of the subtree to be printed
	* @return none
	*/
	 public void printPostorder(Node localRoot)
     {
     if(localRoot == null){
    	 return;
     }
        
        printPostorder(localRoot.leftChild);
        printPostorder(localRoot.rightChild);
        localRoot.printNode();
     }
	
	 /**
		* Description:in order traversal, Prints left, root, right
		*
		* @param local root is the root node of the subree to be printed
		* @return none
		*/
	public void printInorder(Node localRoot)
     {
     if(localRoot == null){
    	 return;
     }
        
    	printInorder(localRoot.leftChild);
        localRoot.printNode();
        printInorder(localRoot.rightChild);
   
     }
	
	int min = 515;
	/**
	* Description:Finds the bottom five using the inorder traversal
	* method and printing the countries with a gdp less than the min
	* described above
	*
	* @param takes a node to start the traversal
	* @return none
	*/
	public void printBottomFive(Node localRoot) {
		if(localRoot != null) {
			printBottomFive(localRoot.leftChild);
			if(localRoot.gdpPerCapita < min) {
				localRoot.printNode();
			}
			printBottomFive(localRoot.rightChild);
		}	
		
	}// end bottom 
	
	int count = 69000;
	/**
	* Description:Find the top five using the inorder traversal
	* method and printing the countries with a gdp greater than 
	* the count above.
	*
	* @param takes a node to start the traversal 
	* @return none
	*/
	public void printTopFive(Node localRoot) {
		if(localRoot != null) {
			printTopFive(localRoot.leftChild);
			printTopFive(localRoot.rightChild);
			if(localRoot.gdpPerCapita > count) {
				localRoot.printNode();
			}
		}	
	}
	
	
}
	


