package com.example.saikatdey.shoecase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends AppCompatActivity {

    private String URL_JSON ;
    //String URL ="http://mentormentee.gear.host/android_api/Message.aspx?key="+keyvalue;
    //private String URL_JSON = "http://shoecase.iamsaikatdey.cf/getItems.php";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<MyData> lstMyData ;
    private RecyclerView myrv ;
    private String grand = "";
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);    

        user  = getIntent().getExtras().getString("user");
        URL_JSON = "https://shoecase2018.000webhostapp.com/shoecase/orders.php?user="+user;

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        lstMyData = new ArrayList<>();
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
                        MyData anime = new MyData();

                        anime.setId(jsonObject.getInt("id"));
                        anime.setUname(jsonObject.getString("username"));
                        anime.setItemName(jsonObject.getString("item_name"));
                        anime.setDate(jsonObject.getString("order_date"));
                        anime.setGrand(jsonObject.getString("grandTotal"));
                        anime.setStatus(jsonObject.getString("status"));
                        anime.setPrice(jsonObject.getString("price"));
                        anime.setPath(jsonObject.getString("path"));
                        //Toast.makeText(Cart.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        lstMyData.add(anime);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //Toast.makeText(Cart.this, "user " + user, Toast.LENGTH_SHORT).show();
                //Toast.makeText(Cart.this,lstMyData.get(1).toString(),Toast.LENGTH_SHORT).show();

                setRvadapter(lstMyData);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //name, description, price, category, rating, image_url;
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user", user);
                return params;
            }
        };


        requestQueue = Volley.newRequestQueue(Cart.this);
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<MyData> lst) {

        OrderViewAdapter myAdapter = new OrderViewAdapter (this,lst, user) ;
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);
    }
}
