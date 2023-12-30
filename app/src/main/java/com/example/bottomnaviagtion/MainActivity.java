package com.example.bottomnaviagtion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.bottomnaviagtion.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

          getSupportFragmentManager().beginTransaction().replace( binding.frameContainer.getId() , new todayFrag()).commit();



        binding.bottomNaviagtion.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {




                Log.d("items" , "selected item " + item.getItemId());


                if(item.getItemId() == R.id.mnToday)
                {

                    
                    getRepalce(new todayFrag());
                }
                else if(item.getItemId() == R.id.mnInbox)
                {
                        getRepalce(new InbuxFrag());
                }
                else if(item.getItemId() == R.id.mnSearch)
                {
                        getRepalce(new SearchFrag());
                } else
                {
                        getRepalce(new MenuFrag());
                }

                return true;
            }
        });


    }

    private void getRepalce(Fragment frag)
    {
        getSupportFragmentManager().beginTransaction().
                replace(binding.frameContainer.getId() , frag).commit();
    }
}