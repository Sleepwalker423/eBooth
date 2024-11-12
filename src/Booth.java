/**
 * Author: Charles J. Walker
 * File name: Booth.java
 * Purpose: The Booth class is the main class that runs the voting booth program. It contains the main method. It is
 * responsible for creating the master ballot, interacting with the voters, and printing the results of the election.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Booth {

    private static final int MAX_VOTER_ID = 999999;
    private static final int MIN_VOTER_ID = 0;
    private static final int MAX_OPTION = 4;
    private static final int MIN_OPTION = 1;
    private Ballot masterballot;
    private AQueue<Ballot> completedBallots;
    private Tally tally;
    int voter_id = -1;  //Initializes voter_id to an invalid value

    Booth(Ballot ballot){

        masterballot = ballot;
        completedBallots  = new AQueue<>();

    }



    public static void main(String[] args) {
        Ballot ballot = new Ballot();
        

        //The three initialization of the BallotQuestion objects below create the 3 questions on the ballot
        //as well as the choices for each question
        BallotQuestion bq1 = new BallotQuestion("Who do you elect for president?", 4);
        bq1.addChoice("Chase Oliver");
        bq1.addChoice("Donald Trump");
        bq1.addChoice("Jill Stein");
        bq1.addChoice("Kamala Harris");

        BallotQuestion bq2 = new BallotQuestion("Who do you think will win the house majority?", 4);
        bq2.addChoice("Democrats");
        bq2.addChoice("Republicans");
        bq2.addChoice("I don't know.");

        BallotQuestion bq3 = new BallotQuestion("Do American political theatrics make you laugh or cry?", 3);
        bq3.addChoice("Laugh");
        bq3.addChoice("Cry");
        bq3.addChoice("Both");

        //These method call add the BallotQuestion instances to a linked list stored in the Ballot object.
        ballot.addQuestion(bq1);
        ballot.addQuestion(bq2);
        ballot.addQuestion(bq3);

        Booth booth = new Booth(ballot);

        booth.mainMenu();

    }

    public void mainMenu(){
        int option = 0;

        Scanner sc = new Scanner(System.in);

        do{

            option = 0;

            System.out.print("""
                    Welcome to the 2024 Presidential Voting eBooth!
                    Please select from one of the following options
                    1.  Vote
                    2.  Show number of ballots
                    3.  Print results
                    4.  Exit
                    """);

            //This while loop range checks and handles excpetions for the option variable.
            while(option < MIN_OPTION || option > MAX_OPTION){

                try {
                    
                    option = sc.nextInt();

                } catch (InputMismatchException e) {

                    System.out.println("Exception thrown! You must use an integer.\n");
                    sc.next();

                }
                if(option < MIN_OPTION || option > MAX_OPTION){

                    System.out.println("Please select 1, 2, 3, or 4.");

                }

            }
            switch (option) {
                case 1://Vote Option
                    //This while loop ensures that the voter_id is an integer between 0 and 999999 and utilizes
                    //a try-catch block to handle exceptions
                    voter_id = -1;
                    while(voter_id < MIN_VOTER_ID || voter_id > MAX_VOTER_ID){
                        
                        try{
                            System.out.print("Please enter your voter ID to begin voting(Only positive integers no greater than 999999):");
                            
                            voter_id = sc.nextInt();
                            
                        }catch(InputMismatchException e){
                            
                            System.out.println("Exception thrown! You must use an integer.\n");
                            sc.next();
                        }
                        if(voter_id < 0 || voter_id > MAX_VOTER_ID){
                            
                            System.out.println("Please enter an integer between 0 and 999999:");
                            
                        }
                        
                    }   

                    try {
                        
                        Ballot b = masterballot.clone();

                        b.vote(voter_id);//initiates the voting process.

                        completedBallots.enqueue(b);
                        
                    } catch (CloneNotSupportedException e) {
                        
                        System.out.printf("!Clone Not Supported Exception Thrown!");
                        
                    }   break;
                case 2://Show number of ballots option

                    System.out.println("Number of Ballots = " + completedBallots.length());
                    break;

                case 3://Print Results option
                    
                    //Tally is initialized here so every time the user chooses to print the results, they are updated
                    //occordingly.
                    tally = new Tally(completedBallots, masterballot);

                    tally.printResults();
                    
                    break;

                case 4://Exit option
                    if(completedBallots.length() != 0){

                        System.out.println("""
                                                         Thank you for voting!
                                            -------------------Your Ballot-------------------""");

                        for(int i = 1; i < completedBallots.length()+1; i++){
                            
                            completedBallots.getElement(i).printBallot();
                            
                        }
                    }else{
                        
                        System.out.println("There are no completed Ballots.");
                        
                    }
                                        
                    System.out.println("""
                                        We hope you voted in the actual election on November 5th, 2024!
                                        On behalf of Charles J. Walker, have a great day!
                                        """);
                    sc.close();

                    break;

                default:

                    break;
            }
        }while(option != 4);//Option 4 ends the program.

        sc.close();

    }
}
