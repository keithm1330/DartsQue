package com.example.dartsque;

public class Player {
    private final int id;
    private final String name;
    private int consecutiveWins = 0;

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public int getConsecutiveWins() { return consecutiveWins; }
    public void incrementWins() { consecutiveWins++; }
    public void resetWins() { consecutiveWins = 0; }
}
