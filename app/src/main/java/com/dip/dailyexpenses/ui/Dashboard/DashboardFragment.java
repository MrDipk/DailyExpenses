package com.dip.dailyexpenses.ui.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dip.dailyexpenses.DatePicker;
import com.dip.dailyexpenses.R;

public class DashboardFragment extends Fragment {

    DatePicker dt,dt1;
    String FromDate,ToDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        final ImageView formDT=v.findViewById(R.id.imageViewFromDate);
        final ImageView toDT=v.findViewById(R.id.imageViewtodate);
        final EditText fromtextDT=v.findViewById(R.id.textformdate);
        final EditText totextDT=v.findViewById(R.id.editTextotp);
        final DatePicker dt=new DatePicker();
        final DatePicker dt1=new DatePicker();
        formDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dt.DatePicker(getContext(),fromtextDT);
            }
        });

        toDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dt1.DatePicker(getContext(),totextDT);
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        Toast.makeText(getContext(),"OnRResum",Toast.LENGTH_SHORT);
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