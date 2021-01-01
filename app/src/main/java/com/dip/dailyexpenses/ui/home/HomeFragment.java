package com.dip.dailyexpenses.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dip.dailyexpenses.ExpensesData;
import com.dip.dailyexpenses.Expenses_List;
import com.dip.dailyexpenses.MyRecyclerViewAdapter;
import com.dip.dailyexpenses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewadapter;
    List<Expenses_List> ExpList;
    String user,date;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getContext(),"OnCreate",Toast.LENGTH_SHORT);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        recyclerView=(RecyclerView)v.findViewById(R.id.RecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    @Override
    public void onResume() {
        Toast.makeText(getContext(),"OnResume",Toast.LENGTH_SHORT);
        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MYPREFERENCE", MODE_PRIVATE);
        FirebaseDatabase df = FirebaseDatabase.getInstance();
        final DatabaseReference DbExt;


        ExpList = new ArrayList<>();
        user=sharedPreferences.getString("phno","");
        /*amt=sharedPreferences.getString("Amount","");
        title=sharedPreferences.getString("Title","");
        dt=sharedPreferences.getString("Date","");
        uid=sharedPreferences.getString("UIID","");*/
        DbExt= df.getReference("UserExpenses").child(user).child(date);

        DbExt.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(final DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ExpensesData EData= dataSnapshot.getValue(ExpensesData.class);
                    Expenses_List EL=new Expenses_List(EData.amount,EData.title,EData.date,EData.uiid);
                    ExpList.add(EL);
                }
                recyclerViewadapter = new MyRecyclerViewAdapter(ExpList, getContext());
                recyclerView.setAdapter(recyclerViewadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onResume();
    }

    @Override
    public void onStart() {
        Toast.makeText(getContext(),"OnStart",Toast.LENGTH_SHORT);
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Toast.makeText(getContext(),"OnSavaINstance",Toast.LENGTH_SHORT);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(),"OnPouse",Toast.LENGTH_SHORT);
        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getContext(),"OnStop",Toast.LENGTH_SHORT);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getContext(),"OnDistroy",Toast.LENGTH_SHORT);
        super.onDestroy();
    }
}