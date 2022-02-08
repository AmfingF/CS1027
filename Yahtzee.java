import java.util.*;

public class Yahtzee{//Initialize the dice array and roll them for random values
    
    private Dice[] dice;

    public Yahtzee() {
    	
        this.dice = new Dice[5];
        
        for (int i = 0; i<dice.length; i++) {
            dice[i] = new Dice();
            dice[i].roll();
        }
    }
    public Yahtzee(Dice[] dice) {//Initialize the dice array with the given argument
        this.dice = dice;
    }

    public int[] getValueCount(){//Count how many dice show each of the possible values from 1-6 and record all of them in an int array. Return this array with the 6 counters
    	
        int[] countValue = new int[6];
        
        for(int i = 0; i < dice.length; i++){
            countValue[dice[i].getValue()-1]++;
        }
        return countValue;
    }

    private void initializeArray(int[] results){
        for(int i = 6; i<13; i++){
            results[i] = 0;
        }
    }

    private int getArraySum(Dice[] dice){
        int sumOfArray = 0;
        for (int i = 0; i<dice.length; i++){
            sumOfArray += dice[i].getValue();
        }
        return sumOfArray;
    }

    private void calculateDiceShowingSameValue(int[] results, int[] valueCount, int sumOfArray){
        for (int i = 0; i<valueCount.length; i++){
            results[i] = valueCount[i] * (i+1);
            if(valueCount[i]>=3){
                results[6] = sumOfArray;
            }

            if(valueCount[i]>=4){
                results[7] = sumOfArray;
            }
            if(valueCount[i]==5){
                results[11] = 50;
            }
        }
        results[12] = sumOfArray;
    }

    private void calculateFullHouse(int[] results, int[] valueCount){
        if(results[6] != 0){
            for (int i = 0; i<valueCount.length; i++){
                if(valueCount[i] == 2){
                    results[8] = 25;
                    break;
                	}
            	}
        	}        
    	}

    private void calculateConsecutive(int[] results, int[] valueCount){
        int consecutive = 0;
        for (int i = 0; i<valueCount.length; i++){
            if(valueCount[i] == 0){
                consecutive = 0;
            }
            else{
                consecutive++;
            	}
            if(consecutive==4){
            results[9] = 30;
            	}
            if(consecutive==5){
            results[10] = 40;
            	}
        	}
    	}

    public int[] getScoreOptions(){// Create an int array with 13 elements, to record all the possible scores for the dice in the instance variable. These 13 scores must be ordered exactly as described in the introduction
    	
        int[] results = new int[13];
        Dice[] dice = this.dice;
        
        int[] valueCount = getValueCount();
        int sumOfArray = getArraySum(dice);
        initializeArray(results);
        
        calculateDiceShowingSameValue(results, valueCount, sumOfArray);
        calculateFullHouse(results, valueCount);
        calculateConsecutive(results, valueCount);
        
        return results;
    }

    public int[] score(){//Call getScoreOptions() and then determine the maximum value from the array of possible scores, return int arr : the maximum value and then the corresponding index of that value
    	
        int[] results = getScoreOptions();
        int maxScore = 0;
        int scoreIndex = 0;
        
        for(int i = 0;  i<results.length; i++){
            if(results[i]>maxScore){
                maxScore = results[i];
                scoreIndex = i;
            }
        }
        return new int[]{maxScore, scoreIndex};
    }

    public boolean equals(Yahtzee other){//Compare the given Yahtzee object from the argument with the "this" object to see if they are equal. Consider equality to be the same 5 dice but in any order
        return Arrays.equals(this.getValueCount(), other.getValueCount());
    }

    public String toString(){//Return a string of the dice values formatted this way "Dice: {3, 5, 1, 1, 2}"
    	
        String s = "Dice: {";
        for(int i = 0; i<dice.length; i++){
            if(i == 4){
                s += dice[i].getValue() + "}";
                break;
            }
            s += dice[i].getValue() + ", ";
        }
        return s;
    }

    public static void main(String[] args){//main 
    	
        Dice[] testArray = new Dice[5];
        
        testArray[0] = new Dice(4);
        testArray[1] = new Dice(4);
        testArray[2] = new Dice(4);
        testArray[3] = new Dice(4);
        testArray[4] = new Dice(4);
        
        Yahtzee x = new Yahtzee(testArray);
        int[] results = x.getScoreOptions();
        
        System.out.println(x.toString());
        System.out.println(Arrays.toString(results));
    }
}
