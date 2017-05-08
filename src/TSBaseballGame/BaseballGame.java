package TSBaseballGame;

import java.util.ArrayList;

/**
 * Created by TXS0403 on 4/13/2017.
 */
public class BaseballGame extends BaseballUtils {
    //Initializing my fields/variables
    private String visitingTeam;
    private String homeTeam;
    private boolean gameIsTied = false;
    private int visitingTeamBatter = 1;
    private int homeTeamBatter = 1;
    private int[] bases = new int[3];
    private String[] visitingPlayers = new String[9];
    private String[] homePlayers = new String[9];
    private ArrayList<String> TeamStatistics = new ArrayList<String>();
    private int inning;
    boolean extraInnings = false;
    private int[] score = {0,0};

    //Using constructors to set my identify my Visitor and Home Teams
    public BaseballGame(){
        //visitingTeam is the Chicago White Sox
        //homeTeam is the Atlanta Braves
        this.visitingTeam = "Visitor";
        this.homeTeam = "Home";
    }
    //
    public BaseballGame(String[] Visitor, String[] Home){
        this.visitingPlayers = Visitor;
        this.homePlayers = Home;
    }
    //Using Getters and Setters for my private fields/variables
    public String getVisitingTeam() {
        return visitingTeam;
    }
    public void setVisitingTeam(String visitingTeam) {
        this.visitingTeam = visitingTeam;
    }
    public String getHomeTeam(){
        return homeTeam;
    }
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }
    public String[] getVisitingPlayers(){
        return visitingPlayers;
    }
    public void setVisitingPlayers(String[] visitingPlayers){
        this.visitingPlayers = visitingPlayers;
    }
    public String[] getHomePlayers(){
        return homePlayers;
    }
    public void setHomePlayers(String[] homePlayers){
        this.homePlayers = homePlayers;
    }

    //Lets Play Ball with 9 innings starting from 1 - 9
    public void BaseballGame(){
        inning = 1;
            do{
                playFullInning(inning);
                inning++;
            }while((inning <= 9) || extraInnings);
            //if the visiting team and the home team have the same score then play extra innings
            if(score[0] == score[1]){
                gameIsTied = true;
            }
            while(gameIsTied){
                playFullInning(inning);
                //if the visiting team and the home team don't have the same score then extra innings aren't needed
                if(score[0] != score[1]){
                    gameIsTied = false;
                }
            }
            System.out.println();
            System.out.println("Final Score: ");
            System.out.println("********************");
            System.out.println(visitingTeam+ ": " + score[0]);
            System.out.println(homeTeam+ ": " + score[1]);
            System.out.println("******************************************************************");
            System.out.println("Team Statistics for the "+ visitingTeam +" and the "+ homeTeam);
            System.out.println("******************************************************************");
            printArrayList(TeamStatistics);
    }

    //Method was created to play a Full inning with both the Visiting and Home Teams
    private void playFullInning(int inning){
        String visitingTeam, homeTeam;
        visitingTeam = playHalfInning(visitingTeamBatter, this.visitingPlayers, true);
        homeTeam = playHalfInning(homeTeamBatter, this.homePlayers, false);

        // Add the game statistics for the Visiting Team based on what inning they are in
        if(visitingTeam != "") {
            TeamStatistics.add("Inning "+inning + "("+ this.visitingTeam +"): " +visitingTeam);
        }
        // Add the game statistics for the Home Team based on what inning they are in
        if(homeTeam != "") {
            TeamStatistics.add("Inning "+inning + "("+ this.homeTeam +"): " +homeTeam);
        }

    }

    //Generate a Half Inning for each team and tracks the batter
    public String playHalfInning(int batter, String[]teams, boolean vTeam ){
        int outs = 0;
        int randomNum =0;
        int runs = 0;
        String continuePlaying = "";

        bases[0] = 0;
        bases[1] = 0;
        bases[2] = 0;

        //Random Number Generator for Hits
     //17% is for a Single, 7% for a Double, 2% for a Triple, 4% for a Home Run, and 70% for an Out
        do {
            randomNum = RandomNumberGen(100);

            if(randomNum <= 17){
                continuePlaying += teams[batter] +"(Single); ";
                runs = movePlayers(1);
            }
            else if(randomNum > 18 && randomNum <= 24){
                continuePlaying += teams[batter] +"(Double); ";
                runs = movePlayers(2);
            }
            else if(randomNum > 25 && randomNum <= 26){
                continuePlaying += teams[batter] +"(Triple); ";
                runs = movePlayers(3);
            }
            else if(randomNum > 27 && randomNum <= 30){
                continuePlaying += teams[batter] +"(HOME RUN); ";
                runs = movePlayers(4);
            }
            else{
                    outs++;
                    runs = 0;
                    continuePlaying += teams[batter] +"(OUT); ";
            }

            if(vTeam){
                score[0] += runs;
            }
            else{
                score[1] += runs;
            }
            if(batter < 8) {
                batter++;
            }
            else {
                batter = 1;
            }
        }
        while (outs != 3);
    //Tracking the batters on each team
        if(vTeam){
            visitingTeamBatter = batter;
        }
        else{
            homeTeamBatter = batter;
        }
        return continuePlaying;
    }

    //Keeping up with who's on 1st, who's on 2nd, who's on 3rd, and who scores based on hits
    public int movePlayers(int move){
        int score = 0;
        //Keeping track of the player on 3rd base.
        if (bases[2] == 1){
            //set 3rd base to 0 before advancing the runner
            bases[2] = 0;
                score++;
        }

        //Keeping track of the player on 2nd base.
        if (bases[1] == 1){
            //reset 2nd base to 0 before advancing the runner.
            bases[1] = 0;
            //Single Hit
            if (move == 1){
                bases[2] = 1;
            }
            else{
             //Runner scores if the player hits a Double, Triple, or Home Run
                score++;
            }
        }
        //Keeping track of the player on 1st base.
        if (bases[0] == 1){
            bases[0] = 0;
            //Single Hit
            if (move == 1){
                bases[1] = 1;
            }
            //Double Hit
            else if (move == 2){
                bases[2] = 1;
            }
            else{
             //Triple Hit or Home Run
                score++;
            }
        }
        //The Player scores if they hit a Home Run
        if (move == 4){
            score++;
        }

        // Advancing player around the bases based on what they hit
        if (move != 4) {
            bases[move - 1] = 1;
        }
        return score;


    }

}
