package com.example.saikatdey.shoecase;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    //private ProgressBar loading;
    Button signin;
    LinearLayout linearLayout;

    String data;

    private static String URL_REGIST = "https://shoecase2018.000webhostapp.com/shoecase/addUser.php";
    //private static String URL_REGIST = "http://shoecase.iamsaikatdey.cf/addUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_password);
        //loading = findViewById(R.id.loading);
        signin = findViewById(R.id.signin);
        TextView mShowDialog = (TextView) findViewById(R.id.login);

    }


    public void signin(View view) {
        Register();
    }

    public void Register()
    {
        //loading.setVisibility(View.VISIBLE);
        //signin.setVisibility(View.GONE);
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Sign in");
        progressDialog.setMessage("Signing in, please wait..");
        progressDialog.show();

        final String email = this.email.getText().toString().trim();
        final String pass = this.password.getText().toString().trim();

        //assigning the username to global variable
        data = email;

        if(!email.isEmpty() && !pass.isEmpty()) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = null;
                                success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    // Toast.makeText(MainActivity.this, "Register Successful!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, Home.class);
                                    i.putExtra("user",email);
                                    startActivity(i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this, "Register Error!", Toast.LENGTH_SHORT).show();
                                //loading.setVisibility(View.GONE);
                                //signin.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.cancel();
                            Toast.makeText(MainActivity.this, "Register Error! Try Again.", Toast.LENGTH_SHORT).show();
                            //loading.setVisibility(View.GONE);
                            //signin.setVisibility(View.VISIBLE);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("pass", pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(this, "Please fill the fields for signing in.", Toast.LENGTH_SHORT).show();
        }


    }

    // calling intent for Log in handler
    public void callLogin(View view)
    {
        Intent i = new Intent(MainActivity.this, CheckLogin.class);
        i.putExtra("user",data);
        startActivity(i);
        //startActivity(new Intent(MainActivity.this, CheckLogin.class));
    }
}
