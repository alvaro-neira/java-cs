package com.alvaroneira.questions;

/**
 * Created by aneira on 3/13/17.
 */
public class GoogleQuestion {
[0 0 1 1 1 2 2 3 4 5]
        0 0 1 | 1  1 2| 2 2 3| 4 5]
        [0 1 2 3 4 5 6 7]
    popular = if > ¼ of the list

    Integer findPopular2(int[] arr){

    }

    Integer recursive(int[] arr,int i, int j, int height){
        if(height > 2)
            return null;
        if(arr[j]==arr[i])
            return arr[i];
        Integer left=recursive(arr,i,j/2,height+1);
        if(left != null) return left;
        else{
            Integer upper=arr[j/2];
        }
        Integer right=recursive(arr,j/2+1,j,height+1);
        if(right != null) return right;
        return null;
    }

    Integer findPopular(int[] arr){
        //First, not using that is sorted
        HashMap<Integer,Integer> hm=new HashMap()

        for(Integer elem:arr){
            int times=hm.containsKey(elem) ? hm.getValue(elem)+1:1
            hm.add(elem,times);
        }
        Collection keys=hm.getKeys().getIterator();
        while(keys.hasNext()){
            Integer k=keys.next()
            int freq=keys.getValue(k);
            if (freq >  arr.length/4.0){
                return k;
            }
        }
        return null;
    }




}
