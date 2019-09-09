package com.example.saikatdey.shoecase;

import android.app.ProgressDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckLogin extends AppCompatActivity {

    EditText etmail, etpass;
    String user;
    private static final String GET_URL = "https://shoecase2018.000webhostapp.com/shoecase/checkUser.php";
    //private static final String GET_URL = "http://shoecase.iamsaikatdey.cf/checkUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);

        user  = getIntent().getExtras().getString("user");

        etmail = (EditText) findViewById(R.id.etEmail);
        etpass = (EditText) findViewById(R.id.etPassword);
    }

    public void callSignin(View view) {
        startActivity(new Intent(CheckLogin.this,MainActivity.class));
    }

    public void authLogin(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(CheckLogin.this);
        progressDialog.setTitle("Log in");
        progressDialog.setMessage("Logging in, please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final String user = this.etmail.getText().toString().trim();
        final String password = this.etpass.getText().toString().trim();

        if(!user.isEmpty() && !password.isEmpty())
        {
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
                                    //startActivity(new Intent(CheckLogin.this, Home.class));
                                    Intent i = new Intent(CheckLogin.this, Home.class);
                                    i.putExtra("user",user);
                                    startActivity(i);
                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(CheckLogin.this, "Login Error!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(CheckLogin.this, "Log in Error! Try Again.", Toast.LENGTH_SHORT).show();

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
        else{
            Toast.makeText(this, "Please fill the fields for logging in.", Toast.LENGTH_SHORT).show();
        }


    }
}
