package com.example.saikatdey.shoecase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AnimeActivity extends AppCompatActivity {

    String name, description, price, category, rating, image_url, user, itemId;
    int id;
    private JsonArrayRequest ArrayRequest ;

    private static String URL_REGIST = "https://shoecase2018.000webhostapp.com/shoecase/addcart.php";
    //private static String URL_REGIST = "http://shoecase.iamsaikatdey.cf/addcart.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        getSupportActionBar().hide();

        // Recieve data
        id  = getIntent().getExtras().getInt("id");
        name  = getIntent().getExtras().getString("anime_name");
        description = getIntent().getExtras().getString("anime_size");
        price = getIntent().getExtras().getString("anime_studio") ;
        category = getIntent().getExtras().getString("anime_category");
        rating = getIntent().getExtras().getString("anime_rating");
        image_url = getIntent().getExtras().getString("anime_img") ;
        user = getIntent().getExtras().getString("user") ;

        itemId = Integer.toString(id);
        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_categorie) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating  = findViewById(R.id.aa_rating) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(price);

        //collapsingToolbarLayout.setTitle(name);
        //collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);



        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);
    }

    //adding to cart
    public void addToCart(View view)
    {
        final ProgressDialog progressDialog = new ProgressDialog(AnimeActivity.this);
        progressDialog.setMessage("Adding to cart, please wait..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        // Matching server responce message to our text.
                        if (response.equalsIgnoreCase("success")) {
                            Toast.makeText(AnimeActivity.this, "Item is added successfully!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AnimeActivity.this, "Error while saving the item!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.cancel();

                        Toast.makeText(AnimeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        //loading.setVisibility(View.GONE);
                        //signin.setVisibility(View.VISIBLE);
                    }
                }) {
                        //name, description, price, category, rating, image_url;
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("user", user);
                            params.put("itemId", itemId);
                            params.put("name", name);
                            params.put("price", price);
                            return params;
                        }
                    };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
