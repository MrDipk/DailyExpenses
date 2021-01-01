package com.dip.dailyexpenses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.dip.dailyexpenses.ui.expenses.ExpensesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView profile;
    TextView name,email;
    String Uname,phoneno,Uemail,gender;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewadapter;
    List<Expenses_List> ExpList;
    String user,date;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERENCE", MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentByTag("myFragmentTag");
                if (fragment == null) {
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new ExpensesFragment();
                    ft.replace(android.R.id.content, fragment, "myFragmentTag");
                    ft.commit();
                }
            }
        });

        //nav_header_main ---------------

        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);

        profile=(ImageView)hView.findViewById(R.id.imageViewprofile);
        name=(TextView)hView.findViewById(R.id.textviewname);
        email=(TextView)hView.findViewById(R.id.textViewemail);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);



        Uname= sharedPreferences.getString("name","");
        Uemail=sharedPreferences.getString("email","");
        phoneno=sharedPreferences.getString("phno","");
        gender=sharedPreferences.getString("gender","");
        putimage();
        //phno="8005634848";
        name.setText(Uname);
        email.setText(Uemail);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Dashboard, R.id.nav_About)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERENCE", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("login", "");
                editor.apply();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void putimage(){
        if(gender.equals("Male")){
            profile.setBackgroundResource(R.mipmap.ic_launcher_male_avtar_round);
        }
        else{
            profile.setBackgroundResource(R.mipmap.ic_launcher_female_avtar_round);
        }
    }
}