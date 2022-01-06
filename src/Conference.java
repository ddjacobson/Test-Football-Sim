///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title: Basic Output
// Course: COMPSCI 200 Fall 2021
//
// Author: Dane Jacobson
// Email: ddjacobson@wisc.edu
// Lecturer's Name: Prof. Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
//
//
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;

public class Conference {

    public String name;
    public ArrayList<Division> divisionList;

    public Conference(String name){
        this.name = name;
        buildDivisions();

    }

    private void buildDivisions() {
        this.divisionList = new ArrayList<>();
        for (int division = 0; division < 4; division++){
            switch (division){
                case 0 -> divisionList.add(new Division(this,"East"));
                case 1 -> divisionList.add(new Division(this,"North"));
                case 2 -> divisionList.add(new Division(this,"South"));
                case 3 -> divisionList.add(new Division(this,"West"));
            }
        }
    }


}
