package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Integer> arrayList =new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        for(int i=0;i<arrayList.size();i++){
            System.out.println(i);
            if(i<4) arrayList.add(i);


        }

    }
}
