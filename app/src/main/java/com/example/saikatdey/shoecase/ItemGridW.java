package com.example.saikatdey.shoecase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemGridW extends AppCompatActivity {

    private String URL_JSON = "https://shoecase2018.000webhostapp.com/shoecase/getItemsW.php";
    //private String URL_JSON = "http://shoecase.iamsaikatdey.cf/getItemsW.php";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime ;
    private RecyclerView myrv ;
    private String img_path = "";
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemgrid_activity);

        user  = getIntent().getExtras().getString("user");

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        lstAnime = new ArrayList<>();
        myrv = findViewById(R.id.rv);
        jsoncall();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void jsoncall() {

        ArrayRequest = new JsonArrayRequest(URL_JSON, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;


                for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();

                        anime.setId(jsonObject.getInt("id"));
                        anime.setName(jsonObject.getString("name"));
                        anime.setPrice(jsonObject.getString("price"));
                        anime.setCategory(jsonObject.getString("category"));
                        anime.setSize(jsonObject.getString("size"));
                        anime.setPath(jsonObject.getString("path"));
                        anime.setRating(jsonObject.getString("rating"));
                        //Toast.makeText(ItemGrid.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        lstAnime.add(anime);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //Toast.makeText(ItemGridW.this, "user " + user, Toast.LENGTH_SHORT).show();
                //Toast.makeText(ItemGridW.this,"Size of Liste "+String.valueOf(lstAnime.size()),Toast.LENGTH_SHORT).show();
                //Toast.makeText(ItemGridW.this,lstAnime.get(1).toString(),Toast.LENGTH_SHORT).show();

                setRvadapter(lstAnime);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(ItemGridW.this);
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<Anime> lst) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter (this,lst, user) ;
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);
    }

}
