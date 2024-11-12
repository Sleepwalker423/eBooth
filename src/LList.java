/*
File name: LList.java

Copyright 2011-2023 by OpenDSA Project Contributors and distributed under an MIT license.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
import java.util.NoSuchElementException;

// Linked list implementation
class LList<E> implements List<E> {
  private Link<E> head;      // Pointer to list header
  private Link<E> tail;      // Pointer to last element
  private Link<E> curr;      // Access to current element
  private int listSize;      // Size of list

  // Constructors
  LList(int size) {      // Used to avoid user error when creating a list
    this(); 
  }
  LList() { 
    clear(); 
  }

  // Remove all elements
  @Override
  public void clear() {
    curr = tail = new Link<>(null); // Create trailer
    head = new Link<>(tail);        // Create header
    listSize = 0;
  }
  
  // Insert "it" at current position
  @Override
  public boolean insert(E it) {
    curr.setNext(new Link<>(curr.element(), curr.next()));
    curr.setElement(it);
    if (tail == curr) {
      tail = curr.next();  // New tail
    }
    listSize++;
    return true;
  }
  
  // Append "it" to list
  @Override
  public boolean append(E it) {
    tail.setNext(new Link<>(null));
    tail.setElement(it);
    tail = tail.next();
    listSize++;
    return true;
  }

  // Remove and return current element
  @Override
  public E remove () throws NoSuchElementException {
    if (curr == tail) {// Nothing to remove
      throw new NoSuchElementException("remove() in LList has current of " + curr + " and size of "
        + listSize + " that is not a a valid element");
    }
    E it = curr.element();                  // Remember value
    curr.setElement(curr.next().element()); // Pull forward the next element
    if (curr.next() == tail) {
      tail = curr;   // Removed last, move tail
    }
    curr.setNext(curr.next().next());       // Point around unneeded link
    listSize--;                             // Decrement element count
    return it;                              // Return value
  }

  @Override
  public void moveToStart() { 
    curr = head.next(); // Set curr at list start
  } 

  @Override
  public void moveToEnd() {      
    curr = tail; // Set curr at list end
  }

  // Move curr one step left; no change if now at front
  @Override
  public void prev() {
    if (head.next() == curr) {
      return; // No previous element
    }
    Link<E> temp = head;
    // March down list until we find the previous element
    while (temp.next() != curr) {
      temp = temp.next();
    }
    curr = temp;
  }

  // Move curr one step right; no change if now at end
  @Override
  public void next() { if (curr != tail) { curr = curr.next(); } }

  @Override
  public int length() { return listSize; } // Return list length


  // Return the position of the current element
  @Override
  public int currPos() {
    Link<E> temp = head.next();
    int i;
    for (i=0; curr != temp; i++) {
      temp = temp.next();
    }
    return i;
  }
  
  // Move down list to "pos" position
  @Override
  public boolean moveToPos(int pos) {
    if ((pos < 0) || (pos > listSize)) {
      return false;
    }
    curr = head.next();
    for(int i=0; i<pos; i++) { curr = curr.next(); }
    return true;
  }

  // Return true if current position is at end of the list
  @Override
  public boolean isAtEnd() { return curr == tail; }

  // Return current element value. Note that null gets returned if curr is at the tail
  @Override
  public E getValue() throws NoSuchElementException {
    if (curr == tail) // No current element
    {
      throw new NoSuchElementException("getvalue() in LList has current of " + curr + " and size of "
        + listSize + " that is not a valid element");
    }
    return curr.element(); 
 }
  
  //Tell if the list is empty or not
  @Override
  public boolean isEmpty() {
    return listSize == 0;
  }
}