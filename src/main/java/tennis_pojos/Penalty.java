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
public class Penalty implements Serializable{
    
    private int payment_no;
    private int player_no;
    private String payment_date;
    private Date payment_date_date;
    private double amount;
    
    private boolean modify;
    private boolean keyModify;

    public Penalty() {
        payment_date = "0000-00-00";
        payment_date_date = UtilitiesDatabase.stringDateToJavaUtilDate(payment_date);
        this.keyModify = true;
        this.modify = true;
    }

    public Penalty(int payment_no, int player_no, String payment_date, double amount) {
        this.payment_no = payment_no;
        this.player_no = player_no;
        this.payment_date = payment_date;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }
    
    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }
    
    public Date getPayment_date_date() {
        payment_date_date = UtilitiesDatabase.stringDateToJavaUtilDate(this.payment_date);
        return payment_date_date;
    }
    
    public void setPayment_date_date(Date payment_d) {
        this.payment_date_date = payment_d;
        int year = payment_d.getYear() + 1900;
        int month = payment_d.getMonth() + 1;
        int day = payment_d.getDate();
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
        this.payment_date = s;   
    }
    
    public int getPlayer_no() {
        return player_no;
    }

    public void setPlayer_no(int player_no) {
        this.player_no = player_no;
    }

    public int getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(int payment_no) {
        this.payment_no = payment_no;
    }

    @Override
    public String toString() {
        return "Penalties{" + "payment_no=" + payment_no + ", player_no=" + player_no + ", payment_date=" + payment_date + ", payment_date_date=" + payment_date_date + ", amount=" + amount + ", modify=" + modify + ", keyModify=" + keyModify + '}';
    }
    
}
