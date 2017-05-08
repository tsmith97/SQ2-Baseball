package TSBaseballGame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by TXS0403 on 4/13/2017.
 */
public class MainBaseball {
    public static void main(String[] args) {
        //Instantiate the BaseballGame class
        BaseballGame bgame = new BaseballGame();

        //Printing the Chicago White Sox's Players using an Array of Strings
        String[]soxs = {"R.Durham", "M.Caruso", "F.Thomas", "M.Ordóñez", "G.Norton", "J.Abbott", "D.Jackson", "B.Fordyce", "J.Baldwin"};

        //Printing the Atlanta Braves Players using an Array of Strings
        String[]braves = {"O.Nixon", "B.Boone", "C.Jones", "B.Jordan", "J.Lopez", "R.Klesko", "A.Jones", "W.Weiss", "T.Glavine"};


        //Setting the names of the Visiting Team and the Home Team
        bgame.setVisitingTeam("Chicago White Sox");
        bgame.setHomeTeam("Atlanta Braves");
        bgame.setVisitingPlayers(soxs);
        bgame.setHomePlayers(braves);

        //Call the BaseballGame Class
        bgame.BaseballGame();

    }
}