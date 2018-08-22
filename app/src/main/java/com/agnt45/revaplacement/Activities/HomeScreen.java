package com.agnt45.revaplacement.Activities;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.agnt45.revaplacement.Fragments.AccountSettings;
import com.agnt45.revaplacement.Fragments.TakeTest;
import com.agnt45.revaplacement.Fragments.ViewCards;
import com.agnt45.revaplacement.R;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigationView = findViewById(R.id.home_bottom_nav);
        frameLayout = findViewById(R.id.display_layout);
        toolbar = findViewById(R.id.toolbar_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragement = new ViewCards();
                if(item.getItemId()==R.id.home_action){
                    selectedFragement = new ViewCards();
                    toolbar.setVisibility(View.VISIBLE);
                }
                else if(item.getItemId()==R.id.take_test_action){
                    selectedFragement=new TakeTest();
                    toolbar.setVisibility(View.GONE);

                }else if(item.getItemId()==R.id.account_setting_action){
                    selectedFragement=new AccountSettings();
                    toolbar.setVisibility(View.GONE);
                 }
                getSupportFragmentManager().beginTransaction().replace(R.id.display_layout,selectedFragement).commit();
                return true;
            }
        });
    }
}
