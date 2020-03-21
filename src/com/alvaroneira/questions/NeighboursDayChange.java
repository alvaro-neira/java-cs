package com.alvaroneira.questions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//public class AmazonTest1
public class NeighboursDayChange
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days)
    {
        // WRITE YOUR CODE HERE
        int[] retVal=states;

        for(int i=0;i<days;i++){
            retVal=dayChange(retVal);
        }
        return Arrays.stream( retVal ).boxed().collect( Collectors.toList() );
    }
    // METHOD SIGNATURE ENDS

    public int[] dayChange(int[] states){
        return new int[]{0^states[1],states[0]^states[2],states[1]^states[3],
                states[2]^states[4],
                states[3]^states[5],
                states[4]^states[6],
                states[5]^states[7],
                states[6]^0};
    }

}