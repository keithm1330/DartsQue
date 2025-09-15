package com.example.dartsque;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service){ this.service = service; }

    @GetMapping
    public List<Player> getPlayers(){ return service.getPlayers(); }

    @PostMapping
    public Player addPlayer(@RequestParam String name){ return service.addPlayer(name); }

    @DeleteMapping("/{id}")
    public void removePlayer(@PathVariable int id){ service.removePlayer(id); }

    @PutMapping("/{id}/moveToEnd")
    public void moveToEnd(@PathVariable int id){ service.moveToEnd(id); }

    @PutMapping("/{id}/moveDown")
    public void moveDown(@PathVariable int id){ service.moveDown(id); }

    @PutMapping("/{winnerId}/winVs/{loserId}")
    public void winVs(@PathVariable int winnerId, @PathVariable int loserId, @RequestParam(defaultValue = "false") boolean enforceThreeWins){
        service.playerWon(winnerId, loserId, enforceThreeWins);
    }


}
