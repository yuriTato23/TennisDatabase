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
public class Team implements Serializable{
    
    private int team_no;
    private int player_no;
    private String division;
    
    private boolean modify;
    private boolean keyModify;

    public Team() {
        
        this.keyModify=true;
        this.modify=true;
        
    }

    public Team(int team_no, int player_no, String division) {
        this.team_no = team_no;
        this.player_no = player_no;
        this.division = division;
        this.modify=false;
        this.keyModify=false;
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
    
    
    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
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

    @Override
    public String toString() {
        return "Team{" + "team_no=" + team_no + ", player_no=" + player_no + ", division=" + division + '}';
    }
    
    
}
