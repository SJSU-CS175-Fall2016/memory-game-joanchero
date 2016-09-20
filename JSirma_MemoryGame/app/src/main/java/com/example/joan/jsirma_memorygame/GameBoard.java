package com.example.joan.jsirma_memorygame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joan on 9/19/16.
 */
public class GameBoard implements java.io.Serializable {

    private String score = "0";
    List<String> players = new ArrayList<>(Arrays.asList(""));;
    List<String> playersClicked = new ArrayList<>(Arrays.asList(" "));

    public GameBoard(){
        Collections.shuffle(players);
    }
    public GameBoard(List<String> playersCount, List<String> playersClickedCount){
        players = playersCount;
        playersClicked=playersClickedCount;
    }
    public List<String> getplayers(){
        return players;

    }

    public List<String> getplayersClicked(){
        return playersClicked;
    }

    public void addPlayersClicked(String playersClicked){
        players.add(playersClicked);
    }

}
