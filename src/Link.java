/*
File name: Link.java

Copyright 2011-2023 by OpenDSA Project Contributors and distributed under an MIT license.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
class Link<E> {         // Singly linked list node class
    private E e;          // Value for this node
    private Link<E> n;    // Point to next node in list
  
    // Constructors
    Link(E it, Link<E> inn) { e = it; n = inn; }
    Link(Link<E> inn) { e = null; n = inn; }
  
    E element() { return e; }                        // Return the value
    E setElement(E it) { return e = it; }            // Set element value
    Link<E> next() { return n; }                     // Return next link
    Link<E> setNext(Link<E> inn) { return n = inn; } // Set next link
  }