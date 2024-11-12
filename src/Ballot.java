/**
 * Author: Charles J. Walker
 * File name: Ballot.java
 * The Ballot class represents a ballot that contains a linked list of BallotQuestion objects.
 * This class is used to create the master ballot that all ballots are created from using clone().
 */
import java.util.Scanner;

public class Ballot implements Cloneable{

    //Instance variables and linked list of BallotQuestion objects
    private int voter_id;
    private LList<BallotQuestion> bList;
    private int questionsAnswered = 0;

    //Constructor
    Ballot(){   
        bList = new LList<>();
    }

    //Appends a BallotQuestion object to the linked list
    public void addQuestion(BallotQuestion bq){     
        bList.append(bq);
    }

    //Sets the voter_id and calls the printBallot method to start the voting process
    public void vote(int vote_id){      
        this.voter_id = vote_id;

        printBallot();

    }

    //Prints the ballot and prompts the voter for their vote on each question if they have not voted yet.
    public void printBallot(){  

        System.out.println("Voter ID: " + voter_id + "\n");

        bList.moveToStart();

        for(int i = 0; i < bList.length(); i++){

            System.out.println(bList.getValue().toString());    //Prints the question and choices

            if (bList.getValue().getVoteIndex() == -1){     //Only prompts for vote if the voter has not voted yet

                System.out.print("What is your vote? (Enter the number of your choice):");

                bList.getValue().setVote(getInput(bList.getValue().getNumChoices()));   //Sets the voter's vote by calling the getInput method
            }
            if(questionsAnswered < bList.length()) {

                questionsAnswered++;

                System.out.println("\nYou voted: " + bList.getValue().getVote()+"\n");  //Prints the voter's vote
            }

            bList.next();//Move to the next question
        }
    }

    private int getInput(int currNumofChoices){//Prompts the voter for their vote and returns the integer value of their choice

        int choice = -1;
        
        
        while (choice < 0 || choice >= currNumofChoices) {  //Loops until the voter enters a valid choice

            Scanner s = new Scanner(System.in);

            try {

                choice = s.nextInt();

            } catch (Exception e) {

                System.out.println("Exception thrown! You must use an integer.\n");
                s.next();   //Clears the scanner buffer to avoid an infinite loop
                
            }
            if (choice < 0 || choice >= currNumofChoices)   //Prints an error message if the voter enters an invalid choice

                System.out.print("Please enter an integer between 0 and " + (currNumofChoices - 1)+":");
        }
        
        return choice;
    }

    @Override
    public Ballot clone() throws CloneNotSupportedException {

        //Creates the new ballot by cloning the current ballot's information over.
        //Note to self: Primitive data types are cloned over as if doing deep copies. Only objects need to 
        //be specifically deep copied over. 
        Ballot newBallot = (Ballot) super.clone();

        newBallot.bList = new LList<>();

        this.bList.moveToStart();

        for (int i = 0; i < this.bList.length(); i++){

            BallotQuestion clonedBQ = this.bList.getValue().clone();
            newBallot.bList.append(clonedBQ);
            this.bList.next();
        }

        this.bList.moveToStart();


        return newBallot;
    }

    public int getNumOfQuestions(){
            
            return bList.length();
    }

    //Made to allow access to the vote choices for the Tally class. 
    public String getVote(int questionIndex){
        String vote;
        bList.moveToPos(questionIndex);
        vote = bList.getValue().getVote();
        bList.moveToStart();
        return vote;
    }

    //Made to allow access to the question strings for the Tally class.
    public String getQuestion(int questionIndex){
        String question;
        bList.moveToPos(questionIndex);
        question = bList.getValue().getQuestion();
        bList.moveToStart();
        return question;
    }
}