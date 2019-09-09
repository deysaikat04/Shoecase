package com.example.saikatdey.shoecase;

//import android.support.annotation.RequiresApi;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Home extends AppCompatActivity
{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    ViewFlipper viewFlipper;
    String user;

    Button btn;

   // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user  = getIntent().getExtras().getString("user");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        //drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        //mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        //drawerLayout.addDrawerListener(mToggle);
        //mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
        viewFlipper = (ViewFlipper) findViewById(R.id.v_flipper);

        //loop
        for(int image: images){
            flipperImage(image);
        }

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_men:
                        Intent men = new Intent(Home.this, ItemGrid.class);
                        men.putExtra("user",user);
                        startActivity(men);
                        break;
                    case R.id.nav_women:
                        Intent women = new Intent(Home.this, ItemGridW.class);
                        women.putExtra("user",user);
                        startActivity(women);
                        break;

                }
                return true;
            }
        });

        /*bottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                }
            }
        });*/

    }
    public void flipperImage(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        if(id == R.id.cart_menu){
            Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.search_menu){
            startActivity(new Intent(Home.this,SearchBar.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.cart_menu){
            Intent cart = new Intent(Home.this, Cart.class);
            cart.putExtra("user",user);
            startActivity(cart);
            return true;
        }
        else if(id == R.id.search_menu){
            //Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Home.this,Search.class));
            return true;
        }
        else if(id == R.id.logout){
            //Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Home.this,MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}






