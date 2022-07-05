package com.application.dbscgcollectionmanager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.application.dbscgcollectionmanager.database.CardsDatabase;
import com.application.dbscgcollectionmanager.database.UserDatabase;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.application.dbscgcollectionmanager.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    UserDatabase userDatabase = new UserDatabase(this);
    CardsDatabase cardsDatabase = new CardsDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_search, R.id.nav_collection)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Init DB and count elements
        //SQLiteDatabase sqldbUser = userDatabase.getReadableDatabase();
        //int userCardCount = this.userDatabase.getAll(sqldbUser).getCount();
        SQLiteDatabase sqldbCards = cardsDatabase.getReadableDatabase();
        int cardsCardCount = this.cardsDatabase.getAll(sqldbCards).getCount();

        /* If no data in DB, first time launching app
        if ( userCardCount == 0) {
            Toast.makeText(this, "No user Database. Creating...", Toast.LENGTH_SHORT).show();
            new Thread(() -> {
                userDatabase.addCard();
            }).start();
        }*/

        //If no data in DB, first time launching app
        if ( cardsCardCount == 0) {
            Toast.makeText(this, "No cards Database. Creating...", Toast.LENGTH_SHORT).show();
            new Thread(() -> {
                try {
                    cardsDatabase.copyFromAsset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //If db has a new version: update
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}