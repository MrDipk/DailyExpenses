package com.dip.dailyexpenses;

public class Expenses_List {
    private String title,amount,uiid,date;

    public Expenses_List(String amount,String title,  String date,String uiid){
        this.title=title;
        this.amount=amount;
        this.uiid=uiid;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    public String getUiid() {
        return uiid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setUiid(String uiid) {
        this.uiid = uiid;
    }
}
