/*
File name: AQueue.java

Copyright 2011-2023 by OpenDSA Project Contributors and distributed under an MIT license.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
class AQueue<E> implements Queue<E> {
    private E queueArray[];      // Array holding queue elements
    private static final int DEFAULT_SIZE = 10;
    private int maxSize;         // Maximum size of queue
    private int front;           // Index of front element
    private int rear;            // Index of rear element
  
    // Constructors
    @SuppressWarnings("unchecked") // Generic array allocation
    AQueue(int size) {
      maxSize = size+1;          // One extra space is allocated
      rear = 0; front = 1;
      queueArray = (E[])new Object[maxSize];  // Create queueArray
    }
    AQueue() { 
      this(DEFAULT_SIZE); 
    }
  
    // Reinitialize
    @Override
    public void clear() {
      rear = 0; front = 1; 
    }
  
    // Put "it" in queue
    @Override
    public boolean enqueue(E it) {
      if (((rear+2) % maxSize) == front) {
        increaseSize();  // Full
      }
      rear = (rear+1) % maxSize; // Circular increment
      queueArray[rear] = it;
      return true;
    }
  
    // Remove and return front value
    @Override
    public E dequeue() {
      if(length() == 0) {
        return null;
      }
      E it = queueArray[front];
      front = (front+1) % maxSize; // Circular increment
      return it;
    }
  
    // Return front value
    @Override
    public E frontValue() {
      if (length() == 0) {
        return null;
      }
      return queueArray[front];
    }
  
    // Return queue size
    @Override
    public int length() {
      return ((rear+maxSize) - front + 1) % maxSize; 
    }
    
    //Tell if the queue is empty or not
    @Override
    public boolean isEmpty() { 
      return front - rear == 1; 
    }

    //This method is used to increase the size of the queue if it is full when attempting to enqueue.
    public void increaseSize(){

        int newSize = maxSize + 10;

        E temp[] = (E[]) new Object[newSize];

        for (int i = 0; i < maxSize; i++){

            temp[i] = queueArray[i];

        }

        queueArray = (E[])new Object[newSize];

        for (int i = 0; i < maxSize; i++){

            queueArray[i] = temp[i];

        }

        maxSize = newSize;
    }

    //This was created to easily print the ballots
    public E getElement(int index){

        if (length() == 0){

            return null;
        }
        return queueArray[index];

    }

  }