/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tennis_pojos;

import java.io.Serializable;
import java.util.Date;
import tennis_dbase.UtilitiesDatabase;

/**
 *
 * @author lisset
 */
public class Committee implements Serializable{
    
    private int player_no;
    private String begin_date;
    private Date begin_date_date;
    private String end_date;
    private Date end_date_date;
    private String position;
    
    private boolean modify;
    private boolean keyModify;
    
    public Committee() {
        modify=true;
        keyModify = true;
        begin_date = "0000-00-00";
        begin_date_date = UtilitiesDatabase.stringDateToJavaUtilDate(begin_date);
        end_date = "0000-00-00";
        end_date_date = UtilitiesDatabase.stringDateToJavaUtilDate(end_date);
    }

    public Committee(int player_no, String begin_date, String end_date, String position) {
        this.player_no = player_no;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.position = position;
        this.modify = false;
        this.keyModify = false;
    }

    public boolean isKeyModify() {
        return keyModify;
    }

    public void setKeyModify(boolean keyModify) {
        this.keyModify = keyModify;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBegin_date_date() {
        begin_date_date = UtilitiesDatabase.stringDateToJavaUtilDate(this.begin_date);
        return begin_date_date;
    }

    public void setBegin_date_date(Date begin_d) {
        this.begin_date_date = begin_d;
        int year = begin_d.getYear() + 1900;
        int month = begin_d.getMonth() + 1;
        int day = begin_d.getDate();
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
        this.begin_date = s;   
    }

    public Date getEnd_date_date() {
        
        end_date_date = UtilitiesDatabase.stringDateToJavaUtilDate(this.end_date);
        return end_date_date;
    }

    public void setEnd_date_date(Date end_d) {
        this.end_date_date = end_d;
        int year = end_d.getYear() + 1900;
        int month = end_d.getMonth() + 1;
        int day = end_d.getDate();
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
        this.end_date = s;   }
    
    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public int getPlayer_no() {
        return player_no;
    }

    public void setPlayer_no(int player_no) {
        this.player_no = player_no;
    }

}
