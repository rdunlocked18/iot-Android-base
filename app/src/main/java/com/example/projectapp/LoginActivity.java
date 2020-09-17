package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;

import com.example.projectapp.databinding.ActivityLoginBinding;


import org.json.JSONException;
import org.json.JSONObject;



public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    ActivityLoginBinding activityLoginBinding;

    RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        // get from json !
        String mainUsersGetUrl = "https://raw.githubusercontent.com/rdunlocked18/jsonParser/master/db.json";


        requestQueue = Volley.newRequestQueue(getApplicationContext());




        activityLoginBinding.btnLoginGet.setOnClickListener(view1 -> {


            ProgressDialog dialog = ProgressDialog.show(this, "Please Wait", "Checking Creds...",
                    true);
            dialog.show();


            Handler handler = new Handler();
            handler.postDelayed(() -> {

                jsonArrayRequest = new JsonArrayRequest(mainUsersGetUrl,response -> {
                    JSONObject jsonObject = null;
                    Log.d(TAG,"i am From json");
                    for (int i=0;i< response.length();i++){


                        try {  jsonObject = response.getJSONObject(i);

                            if(activityLoginBinding.emailInputLogin.getText().toString().equals(jsonObject.getString("email"))
                                    && activityLoginBinding.passwordInputLogin.getText().toString().equals(jsonObject.getString("password"))) {

                                String code = jsonObject.getString("macId");
                                Toast.makeText(this, "Sent back Your Device MacID!"+code, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("macid" , code);
                                startActivity(intent);

                            }else {
                                Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } , error -> {
                    Toast.makeText(this, "Database API connection Error !", Toast.LENGTH_SHORT).show();
                });requestQueue.add(jsonArrayRequest);
                dialog.dismiss();

            },1000);












        }); //end clicker



   }
}
