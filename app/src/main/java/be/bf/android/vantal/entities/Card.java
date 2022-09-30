package be.bf.android.vantal.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "card_holder")
    private String cardHolder;
    @ColumnInfo(name = "card_number")
    private String cardNumber;
    @ColumnInfo(name = "card_cvv")
    private Integer cvv;
    @ColumnInfo(name = "card_exp_month")
    private Integer exp_month;
    @ColumnInfo(name = "card_exp_year")
    private Integer exp_year;

    public Card(String cardHolder, String cardNumber, Integer cvv, Integer exp_month, Integer exp_year) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getExp_month() {
        return exp_month;
    }

    public void setExp_month(Integer exp_month) {
        this.exp_month = exp_month;
    }

    public Integer getExp_year() {
        return exp_year;
    }

    public void setExp_year(Integer exp_year) {
        this.exp_year = exp_year;
    }
}
