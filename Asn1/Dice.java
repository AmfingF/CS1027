public class Dice{
    private int value;
    
    public Dice(){//Initialize value to -1
        this.value = -1; 
    }

    public Dice(int value){//Initialize value to the given argument
        this.value = value;
    }

    public void roll(){//Use the provided class RandomNumber to generate a number between 1 and 6 (inclusive) and set the value to that number
        this.value = RandomNumber.getRandomNumber(1, 6);
    }

    public int getValue(){//Returns the dice value
        return this.value;
    }

}
