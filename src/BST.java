/**
File name: BST.java
Copyright 2011-2023 by OpenDSA Project Contributors and distributed under an MIT license.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/

// Binary Search Tree implementation
public class BST {
    private BSTNode root; // Root of the BST
    private int nodecount; // Number of nodes in the BST

    /**
		Constructor
	*/
    BST() {
        root = null;
        nodecount = 0;
    }


	/**
		resets the tree to empty.
	*/ 
    public void clear() {
        root = null;
        nodecount = 0;
    }

    //INSERTING INTO TREE

    // Insert a record into the tree.
    // Records can be anything, but they must be Comparable
    // e: The record to insert.
	
	/**
	 * Insert value into the tree.
	 * @param v - comparable object to be stored as a new value in tree.
	 */
    public void insert(Comparable v) {
        root = inserthelp(root, v);
        nodecount++;
    }

	/**
	 *	Recursive method to handle the recursive insert of a value.
	 *  @param rt - root of current tree/subtree
	 *  @param - value - comparable objec tto be stored as a new value in tree.
     * This method is updated to increment the count of the node if the value already exists in the tree. 
	 */
    private BSTNode inserthelp(BSTNode rt, Comparable value) {
        if (rt == null) return new BSTNode(value);
        else if (rt.value().compareTo(value) == 0){
            rt.increment();
            nodecount--;//Adjust the node count to account for the increment thats not need since there is no new node added.
        }
        else if (rt.value().compareTo(value) >= 0)
            rt.setLeft(inserthelp(rt.left(),value));
        else
            rt.setRight(inserthelp(rt.right(), value));
        return rt;
    }

    //Removing a node by value.

    // Remove a record from the tree
    // value: The value of record to remove
    // Returns the record removed, null if there is none.
	
	/**
	 *	Removes a node from the tree storing a specified value.
	 *  @param value - the value that should be removed from the tree.
	 *  @return object removed is returned.
	 */
    public Object remove(Comparable<String> value) {
        Object temp = findhelp(root, value); // First find it
        if (temp != null) {
            root = removehelp(root, value); // Now remove it
            nodecount--;
        }
        return temp;
    }

	/**
	 *	Recursive method to remove a node based on value.
	 *  @param rt - root of current subtree
	 *  @param value - value to be deleted.
	 *  @return rt is returned following the changes to the tree.
	 */
    private BSTNode removehelp(BSTNode rt, Comparable<String> value) {
        if (rt == null) return null;

        if (rt.value().compareTo(value) > 0) {
            rt.setLeft(removehelp(rt.left(), value));
        }
        else if (rt.value().compareTo(value) < 0) {
            rt.setRight(removehelp(rt.right(), value));
        }
        else {
            if (rt.left() == null) return rt.right();
            if (rt.right() == null) return rt.left();

            BSTNode tmp = getmax(rt.left());
            rt.setValue(tmp.value());
            rt.setLeft(delmax(rt.left()));
        }
        return rt;
    }

	/**
	 *  Deletes the maximum value in a tree/subtree rooted at rt.
	 *   max is always the right most node in the tree.
	 *  @param rt - root of tree/subtree for which the maximum will be deleted.
	 */
    private BSTNode delmax(BSTNode rt) {
        if (rt.right() == null) return rt.left();
        rt.setRight(delmax(rt.right()));
        return rt;
    }

	/**
	 *  Returns the maximum value in a tree/subtree rooted at rt.
	 *   max is always the right most node in the tree.
	 *  @param rt - reference to the maximum value in a subtree rooted at rt.
	 */
    private BSTNode getmax(BSTNode rt) {
        if (rt.right() == null) return rt;
        return getmax(rt.right());
    }

    // FINDING A VALUE FOR A -VALUE PAIR STORED IN TREE.

    // Return the record with value k, null if none exists
    // value: The comparable object being inserted.
	
	/**
	 * Returns the value stored in the tree if a key is found.
	 *
	 * @param value - a comparable object that we are searching for.
	 * @return object whose key (i.e. comparable variable matches the value we are searching for).
	 */
    public Object find(Comparable<String> value) {
        return findhelp(root, value);
    }

	/**
	 * Recursive method to help find an object whoses value equals the value we are seeking.
	 */
    private Object findhelp(BSTNode rt, Comparable<String> value) {

        if (rt == null) return null;
        if (rt.value().compareTo(value) > 0)
            return findhelp(rt.left(), value);
        else if (rt.value().compareTo(value) < 0)
            return findhelp(rt.right(), value);
        else
            return rt.value();
    }

    //OTHER METHODS

    // Return the number of records in the dictionary
    public int size() {
        return nodecount;
    }

	/**
	 *	Prints the binary search tree values in ascending order.
	 */
    public void inorderPrint() {

        inorderPrint(root);

    }

    /**
	 * Recursive method to perform the inorder traversal to print each value in ascending order.
	 */
	private void inorderPrint(BSTNode rt) {
        if (rt == null) return;
        inorderPrint(rt.left());
        System.out.print(rt.toString() + "\n");
        inorderPrint(rt.right());
    }

	/**
	 * Computes the number of nodes in the tree by calling recursive method.
	 */
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(BSTNode rt) {
        if (rt == null) return 0;
        return 1 + countNodes(rt.left()) + countNodes(rt.right());
    }

	/**
	 *  Returns the height of the tree by calling a recursive method.
	 */
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BSTNode rt) {
        if (rt == null) return 0;
        return 1 + Math.max(getHeight(rt.left()), getHeight(rt.right()));
    }


	/** Main Method to Test Tree 

    public static void main(String [] args) {

        BST bst = new BST();

        Comparable [] values = new Integer[]{8,6,7,5,3,0,9};

        for (int i = 0; i < values.length; i++) {
            bst.insert(values[i]);
            bst.inorderPrint();
        }

        System.out.println("Num Nodes:" + bst.countNodes());
        System.out.println("Height:" + bst.getHeight());

        System.out.println(bst.remove(5));
        bst.inorderPrint();




    }*/

}