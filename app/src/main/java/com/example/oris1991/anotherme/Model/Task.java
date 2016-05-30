package com.example.oris1991.anotherme.Model;

/**
 * Created by oris1991 on 09/05/2016.
 */
public class Task {

    long startTime;
    long endTime;
    String Title;
    String location;
    int whatToDo;


    Solution solution;
    public Task(String title, long startTime, long endTime, String location,int whatToDo) {
        Title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.whatToDo=whatToDo;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getEndTime() {

        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {

        return startTime;
    }
    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public int getWhatToDo() {
        return whatToDo;
    }

    public void setWhatToDo(int whatToDo) {
        this.whatToDo = whatToDo;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}


