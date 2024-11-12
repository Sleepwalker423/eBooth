/**
 * File name: BSTNode.java

Copyright 2011-2023 by OpenDSA Project Contributors and distributed under an MIT license.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/

public class BSTNode {

    //Node Information
    private Comparable<String> value;

    //Child References
    private BSTNode left;
    private BSTNode right;

	//Vote counter
	private int count;

	/**
	 * Constructor
	 * @param value - the comparable value stored in the node
	 */
    public BSTNode(Comparable value) {
        this.value = value;

		this.count = 1;
    }

	//Used to increment the count variable
	public void increment(){

		count++;

	}

	/**
	 * Getter for value of node.
	 * @return value stored in node.
	 */
    public Comparable value() {
        return value;
    }

	/**
		Getter for left child
		@return reference to left child.
	*/
    public BSTNode left() {
        return left;
    }

	/**
		Getter for right child
		@return reference to right child.
	*/
    public BSTNode right() {
        return right;
    }

	/**
		Setter to change left child.
		@param left - new left child BSTNode reference
	*/
    public void setLeft(BSTNode left) {
        this.left = left;
    }

	/**
		Setter to change right child.
		@param right - new right child BSTNode reference
	*/

    public void setRight(BSTNode right) {
        this.right = right;
    }


	/**
		Set value of the node to a new value.
		@param value - new value to be inserted.
	*/
    public void setValue(Comparable value) {
        this.value = value;
    }
	
	/**
		Provides a string output for the object with
		the stored value.
		@return string with value
		This method was updated to print the value and count to show proper display of results.
	*/
	@Override
    public String toString() {
        return "" + value + ", " + count;
    }

}
