package com.dip.dailyexpenses;

public class ExpensesData {
    public String title,amount,date,uiid;
    public ExpensesData(){}
    public  ExpensesData(String title,String amount,String date, String uiid){
        this.title=title;
        this.amount=amount;
        this.date=date;
        this.uiid=uiid;
    }
}
