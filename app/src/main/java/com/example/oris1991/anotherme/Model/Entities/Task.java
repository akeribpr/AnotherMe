package com.example.oris1991.anotherme.Model.Entities;

/**
 * Created by oris1991 on 09/05/2016.
 */
public class Task {

    int id;
    long startTime;
    long endTime;
    String Title;
    String location;
    Solution solution;

    public Task(int id, String title, long startTime, long endTime, String location) {
        this.id = id;
        this.Title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;

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


    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


