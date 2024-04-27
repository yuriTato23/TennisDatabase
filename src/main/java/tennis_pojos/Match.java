/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tennis_pojos;

import java.io.Serializable;

/**
 *
 * @author lisset
 */
public class Match implements Serializable{
    
    private int match_no;
    
    private int team_no;
    
    private int player_no;
    
    private int won;
    
    private int lost;

    private boolean modify;
    private boolean keyModify;
    
    public Match() {
        modify = true;
        keyModify = true;
        
    }
    
    public Match(int match_no, int team_no, int player_no, int won, int lost) {
        this.match_no = match_no;
        this.team_no = team_no;
        this.player_no = player_no;
        this.won = won;
        this.lost = lost;
        modify = false;
        keyModify = false;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    public boolean isKeyModify() {
        return keyModify;
    }

    public void setKeyModify(boolean keyModify) {
        this.keyModify = keyModify;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getPlayer_no() {
        return player_no;
    }

    public void setPlayer_no(int player_no) {
        this.player_no = player_no;
    }

    public int getTeam_no() {
        return team_no;
    }

    public void setTeam_no(int team_no) {
        this.team_no = team_no;
    }

    public int getMatch_no() {
        return match_no;
    }

    public void setMatch_no(int match_no) {
        this.match_no = match_no;
    }

    @Override
    public String toString() {
        return "Match{" + "match_no=" + match_no + ", team_no=" + team_no + ", player_no=" + player_no + ", won=" + won + ", lost=" + lost + ", modify=" + modify + ", keyModify=" + keyModify + '}';
    }
    

}
