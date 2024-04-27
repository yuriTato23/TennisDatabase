/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tennis_pojos;

import java.io.Serializable;
import java.util.Date;
import tennis_dbase.UtilitiesDatabase;
//import tennis_dbase.UtilitiesDatabase;

/**
 *
 * @author lisset
 */
public class Player implements Serializable
{
    
    private int player_no;
    private String name;
    private String initials;
    private String birth_date;
    private String sex;
    private int joined;
    private String street;
    private String house_no;
    private String postcode;
    private String town;
    private String phone_no;
    private String league_no;
    private Date birthday_date;
    
    private boolean modify;
    private boolean keyModify;
    
    public Player(){
        modify = true;
        keyModify = true;
        birth_date = "0000-00-00";
        birthday_date = UtilitiesDatabase.stringDateToJavaUtilDate(birth_date);
    }
    
    public Player(
            int player_no, String name, String initials, String birth_date, 
            String sex, int joined, String street, String house_no, 
            String postcode, String town, String phone_no, String league_no) 
    {
        this.player_no = player_no;
        this.name = name;
        this.initials = initials;
        this.birth_date = birth_date;
        this.sex = sex;
        this.joined = joined;
        this.street = street;
        this.house_no = house_no;
        this.postcode = postcode;
        this.town = town;
        this.phone_no = phone_no;
        this.league_no = league_no;
        keyModify = false;
        modify = false;
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
    
    public String getLeague_no() {
        return league_no;
    }

    public void setLeague_no(String league_no) {
        this.league_no = league_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getJoined() {
        return joined;
    }

    public void setJoined(int joined) {
        this.joined = joined;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
     public Date getBirthday_date()
    {
        birthday_date = UtilitiesDatabase.stringDateToJavaUtilDate(this.birth_date);
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date) 
    {
        this.birthday_date = birthday_date;
        int year = birthday_date.getYear() + 1900;
        int month = birthday_date.getMonth() + 1;
        int day = birthday_date.getDate();
        
//        this.birthday = Integer.toString(year) + "-" + Integer.toString(month) + "-" +
//                Integer.toString(day);
        String s = year + "";
        s += "-";
        if (month < 10)
        {
            s += "0" + month + "";
        }
        else
        {
            s += month + "";
        }
        s += "-";
        if (day < 10)
        {
            s += "0" + day + "";
        }
        else
        {
            s += day + "";
        }
        this.birth_date = s;
    }
    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayer_no() {
        return player_no;
    }

    public void setPlayer_no(int player_no) {
        this.player_no = player_no;
    }

    @Override
    public String toString() {
        return "Player{" + "player_no=" + player_no + ", name=" + name + ", initials=" + initials + ", birth_date=" + birth_date + ", sex=" + sex + ", joined=" + joined + ", street=" + street + ", house_no=" + house_no + ", postcode=" + postcode + ", town=" + town + ", phone_no=" + phone_no + ", league_no=" + league_no + ", birthday_date=" + birthday_date + ", modify=" + modify + ", keyModify=" + keyModify + '}';
    }

}
