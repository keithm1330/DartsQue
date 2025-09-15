package com.example.dartsque;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private final LinkedList<Player> players = new LinkedList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public List<Player> getPlayers() { return players; }

    public Player addPlayer(String name){
        if(name != null && !name.isBlank()){
            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        }
        Player p = new Player(idCounter.incrementAndGet(), name);
        players.add(p);
        return p;
    }

    public void removePlayer(int id){ players.removeIf(p -> p.getId() == id); }

    public void moveToEnd(int id){
        Player found = players.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
        if(found != null){
            players.remove(found);
            players.add(found);
        }
    }

    public void moveDown(int id){
        for(int i=0;i<players.size()-1;i++){
            if(players.get(i).getId()==id){
                Player temp = players.get(i+1);
                players.set(i+1, players.get(i));
                players.set(i, temp);
                break;
            }
        }
    }

    // Win/Lose logic
    public void playerWon(int winnerId, int loserId, boolean enforceThreeWins){
        Player winner = players.stream().filter(p->p.getId()==winnerId).findFirst().orElse(null);
        Player loser = players.stream().filter(p->p.getId()==loserId).findFirst().orElse(null);

        if(winner != null) winner.incrementWins();

        if(loser != null){
            if(enforceThreeWins && loser.getConsecutiveWins() >= 3){
                // Loser stays on board
            } else {
                moveToEnd(loserId);
                loser.resetWins();
            }
        }
    }
}
