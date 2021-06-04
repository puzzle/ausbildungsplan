package model;

import java.util.ArrayList;

public class CompentencyLevel{
    private String id;
    private ArrayList<String> instruments;
    private ArrayList<String> goals;
    private String level;

    public CompentencyLevel() {
    }

    public CompentencyLevel(String id, ArrayList<String> instruments, ArrayList<String> goals) {
        this.id = id;
        this.instruments = instruments;
        this.goals = goals;
        this.level = id.split("[.]")[2];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(ArrayList<String> instruments) {
        this.instruments = instruments;
    }

    public ArrayList<String> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<String> goals) {
        this.goals = goals;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
