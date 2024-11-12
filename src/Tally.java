/**
 * Author: Charles J. Walker
 * File name: Tally.java
 * The Tally class controls the binary search trees, which are all stored in an array, that tracks the voting results 
 * of each ballots choices. Choices are stored in an array and the class contains a method to print the question and choices.
 * This entire class it utilized in Booth's swtich option 3.
 */
public class Tally {
    
    private final int numQuestions;//Tracks the number of questions on the ballot. 

    private BST[] trees;//Array of binary search trees that store the voting results of each ballot.

    private AQueue<Ballot> ballots;//Array queue used to hold a copy of the current ballots. A separate copy is used
    //to allow for the election results to be printed multiple times without affecting the original ballots and meet the 
    //assignmnets requirements for the Tally class.     

    private Ballot masterb;//Holds a copy of the master ballot used to show the questions.

    Tally(AQueue<Ballot> bQueue, Ballot masterBallot){


        try {

            masterb = (Ballot)masterBallot.clone();
            
        } catch (CloneNotSupportedException e) {

            System.out.printf("Exception Thrown! Clone not supported for master ballot clone.");

        }

        numQuestions = masterBallot.getNumOfQuestions();

        ballots = new AQueue<>(bQueue.length());//Initializes the ballots queue....this was missing and caused a bug that took me
        //foooreeeeeeeeeeeveeeeeeeerrrrr to find.

        for(int i = 1; i < bQueue.length()+1; i++){//Loops through the ballots to enqueue a copy of each into the ballots queue.

            try {

                ballots.enqueue(bQueue.getElement(i).clone());

            } catch (CloneNotSupportedException e) {
                
                System.out.printf("Exception Thrown! Clone not supported for ballot clone.");

            }
            
            
        }
        this.trees = new BST[numQuestions];//intializes the array of binary search trees based of the number of questions on
        //the ballot. 

        for(int i = 0; i < numQuestions; i++){//Initiliazes each binary search tree in the array.

            trees[i] = new BST();

        }
    }

    public void computeTally(){//Counts the votes by dequeueing each ballot and inserting the votes into the binary search trees.

        int totBallots = ballots.length();

        for(int i = 0; i < totBallots; i++){

            Ballot ballot = ballots.dequeue();

            for (int j = 0; j < numQuestions; j++){

                trees[j].insert(ballot.getVote(j));

            }
        } 
    }

    public void printResults(){//Calls the computeTally method and then prints the voting results from the BST. 

        computeTally();

            if (trees[0].countNodes() != 0){

                System.out.println("Current voting results:\n");

            for(int i = 0; i < numQuestions; i++){

                System.out.println(masterb.getQuestion(i));

                trees[i].inorderPrint();

                System.out.println("\n");

            }

        } else {

            System.out.println("No votes have been cast yet.\n");

        }

    }
}
