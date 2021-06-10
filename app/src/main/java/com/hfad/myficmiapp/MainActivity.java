package com.hfad.myficmiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.hfad.myficmiapp.activities.AboutActivity;
import com.hfad.myficmiapp.activities.BookmarkActivity;
import com.hfad.myficmiapp.activities.ContactUsActivity;
import com.hfad.myficmiapp.activities.CreditsActivity;
import com.hfad.myficmiapp.activities.LoginActivity;
import com.hfad.myficmiapp.activities.RateAppActivity;
import com.hfad.myficmiapp.activities.RegistrationActivity;
import com.hfad.myficmiapp.fragments.HomeFragment;
import com.hfad.myficmiapp.fragments.NotesFragment;
import com.hfad.myficmiapp.fragments.TheWordFragment;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    private BottomNavigationView bottom;
    private HomeFragment home;
    private NotesFragment not;
    private TheWordFragment word;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        home = new HomeFragment();
        not = new NotesFragment();
        word = new TheWordFragment();

        context = this;

        setFragment(home);
        bottom = findViewById(R.id.bottombar);

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        setFragment(home);
                        return true;

                    case R.id.word:
                        setFragment(word);
                        return  true;
                    case R.id.note:
                        setFragment(not);
                        return true;


                    default:
                        return  false;
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle action = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(action);
        action.syncState();

        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Intent intent = null;

                switch (item.getItemId()){
                    case R.id.bookmarks:
                        intent = new Intent(context, BookmarkActivity.class);
                        break;
                    case R.id.contact_us:
                        intent = new Intent(context, ContactUsActivity.class);
                        break;
                    case R.id.about_us:
                        intent = new Intent(context, AboutActivity.class);
                        break;
                    case R.id.nav_rate:
                        intent = new Intent(context, RateAppActivity.class);
                        break;
                    case R.id.nav_credits:
                        intent = new Intent(context, CreditsActivity.class);
                        break;
                    case R.id.logout:
                        auth.signOut();
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    default:
                        fragment = new HomeFragment();
                }
                if (fragment != null){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe, fragment);
                    ft.commit();
                }else {
                    startActivity(intent);
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

        });

    }
    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, fragment);
        fragmentTransaction.commit();

    }


}