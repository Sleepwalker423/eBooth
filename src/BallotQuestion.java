/**
 * Author: Charles J. Walker
 * File name: BallotQuestion.java
 * The BallotQuestion class represents a single question on a ballot and its corresponding choices.
 * Choices are stored in an array and the class contains a method to print the question and choices.
 * The class also stores the voter's vote. 
 */
public class BallotQuestion implements Cloneable{

    //Instance variables set to final to ensure immutability.
    private final String question;
    private final int maxNumChoices;

    //Instance of mutable variables.
    private int currNumofChoices = 0;   //Used to ensure that the max number of choices is not exceeded.
    private int vote = -1;
    private String[] choices;

    BallotQuestion(String question, int maxNumChoices){     //Constructor
        this.question = question;
        this.maxNumChoices = maxNumChoices;
        choices = new String[maxNumChoices];
    }

    //Below are the getters and setters
    public String getQuestion(){
        return question;
    }

    public String[] getChoices(){
        return choices;
    }

    public int getNumChoices(){
        return maxNumChoices;
    }

    public String getVote(){
        return choices[vote];
    }

    public int getVoteIndex(){
        return vote;
    }

    public void addChoice(String option){    //Adds a choice to the choices array

        if(currNumofChoices < maxNumChoices){   //Ensures that the max number of choices is not exceeded

            choices[currNumofChoices] = option; //Adds the choice to the array

            currNumofChoices++; //Counts the number of choices added

        }else{  //Prints an error message if the max number of choices is exceeded

            System.out.println("Cannot add '"+option+"' because max number of choices reached");

        }
    }

    public void setVote(int vote){  //Sets the voter's vote
        this.vote = vote;
    }

    @Override
    public String toString(){   //Overrides the toString method to print the question and choices

        String str = "Question: " + question + "\n";

        for (int i = 0; i < currNumofChoices; i++) {

            str += "Choice: " + i + " " + choices[i] + "\n";    //Concatenates the question and choices to a string

        }
        
        if(vote > -1) str += "Your vote: " + choices[vote] + "\n";  //Prints the voter's vote if they have voted

        return str;
    }

    @Override//Designed to copy the string array containing the choices
    public BallotQuestion clone() throws CloneNotSupportedException {

        BallotQuestion newBQ = (BallotQuestion) super.clone();

        newBQ.choices = this.choices.clone();

        return newBQ;

    }


}