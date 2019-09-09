package com.example.saikatdey.shoecase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText etmail, etpass;
    private static final String GET_URL = "http://shoecase.iamsaikatdey.cf/checkUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etmail = (EditText) findViewById(R.id.etEmail);
        etpass = (EditText) findViewById(R.id.etPassword);


    }

   /* public void loginCheck(View view)
    {
        final String user = this.etmail.getText().toString().trim();
        final String password = this.etpass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = null;
                            success = jsonObject.getString("success");

                            if(success.equals("1")){
                                // Toast.makeText(MainActivity.this, "Register Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, Home.class));
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "Login Error!", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Register Error! Try Again.", Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("u_name", user);
                params.put("u_pass", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void callSignin(View view) {
        startActivity(new Intent(Login.this,MainActivity.class));
    }*/

}
