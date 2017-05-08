package TSBaseballGame;

import java.util.ArrayList;

/**
 * Created by TXS0403 on 4/13/2017.
 */
public class BaseballUtils {

   //Default Constructor
   BaseballUtils(){
   }

    //Random Number Generator
    public int RandomNumberGen(int limit){
        int num;
        do {
            num = (int) (Math.random() * limit);
        }
        while(num == 0 );
        return num;
    }

    //Array List to track runners on base
    public void printArrayList(ArrayList<String> arrayList){
        for (String temp:arrayList)
        {
            System.out.println(temp);
        }
    }
}
