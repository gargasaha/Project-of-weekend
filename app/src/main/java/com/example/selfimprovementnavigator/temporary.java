package com.example.selfimprovementnavigator;

import java.util.List;

public class temporary {
    private int user_id,task_id,sun,mon,tue,wed,thu,fri,sat;
    private String task_name;
    temporary(int user_id,int task_id,String task_name,int sun,int mon,int tue,int wed,int thu,int fri,int sat){
        this.user_id=user_id;
        this.task_id=task_id;
        this.task_name=task_name;
        this.sun=sun;
        this.mon=mon;
        this.tue=tue;
        this.wed=wed;
        this.thu=thu;
        this.fri=fri;
        this.sat=sat;
    }
    temporary(){

    }
//    public void show(){
//        String temp=user_id+""+task_id+task_name+sun+mon+tue+wed+thu+fri+sat;
//        System.out.println(temp);
//    }
    public int get_task_id(){
        return this.task_id;
    }
    public String get_task_name(){
        return task_name;
    }
}
