package com.dip.dailyexpenses.ui.expenses;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dip.dailyexpenses.DatePicker;
import com.dip.dailyexpenses.ExpensesData;
import com.dip.dailyexpenses.R;
import com.dip.dailyexpenses.ShowAlertDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public class ExpensesFragment extends Fragment {
String date,title,amount,phoneno,uiid,dt;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_expenses, container, false);
        Button close=(Button)v.findViewById(R.id.button_close);
        Button add=(Button)v.findViewById(R.id.buttonAdd);
        final EditText tvtitle=(EditText)v.findViewById(R.id.textTitle);
        final EditText tvamount=(EditText)v.findViewById(R.id.textAmount);
        final TextView DateView=(TextView)v.findViewById(R.id.textViewdate);
        final ScrollView SVEXp=(ScrollView)v.findViewById(R.id.scrollviewExpenses);

        TextView ChooseDate=(TextView)v.findViewById(R.id.textViewchoosedt);

        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MYPREFERENCE", MODE_PRIVATE);
        phoneno=sharedPreferences.getString("phno","");

        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        DateView.setText(date);

        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker dt=new DatePicker();
                dt.DatePicker(getContext(),DateView);
            }
        });

        tvtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SVEXp.getMeasuredHeight();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=tvtitle.getText().toString();
                amount=tvamount.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserExpenses");

                if(!title.isEmpty() && !amount.isEmpty()){
                    uiid= UUID.randomUUID().toString();
                    dt =DateView.getText().toString();
                    ExpensesData ed=new ExpensesData(title,amount,dt,uiid);
                    myRef.child(phoneno).child(dt).child(uiid).setValue(ed);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Amount", amount);
                    editor.putString("Title", title);
                    editor.putString("Date", date);
                    editor.putString("UIID",uiid);
                    editor.apply();
                }
                else{
                   ShowAlertDialog ad= new ShowAlertDialog();
                   ad.showAlertDialog("Please Fill All Details","ok",getContext());
                }
                tvtitle.setText("");
                tvamount.setText("");
                uiid="";
                DateView.setText(date);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(ExpensesFragment.this).commit();

            }
        });
        return v;
    }
}