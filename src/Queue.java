/*
File name: Queue.java

Copyright 2011-2023 by OpenDSA Project Contributors and distributed under an MIT license.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
public interface Queue<E> { // Queue class ADT
    // Reinitialize queue
    public void clear();
  
    // Put element on rear
    public boolean enqueue(E it);
  
    // Remove and return element from front
    public E dequeue();
  
    // Return front element
    public E frontValue();
  
    // Return queue size
    public int length();
    
    //Tell if the queue is empty or not
    public boolean isEmpty();
  }
