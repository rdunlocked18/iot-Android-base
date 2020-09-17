package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectapp.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ActivityMainBinding activityMainBinding;
    RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        requestQueue = Volley.newRequestQueue(this);
       // private JsonArrayRequest jsonArrayRequest;

        String[] logarr = {"Connecting..","Server Connected..","User Already Validated..","MacId Captured...","Your MACID is : "};
        String mainUsersGetUrl = "https://raw.githubusercontent.com/rdunlocked18/jsonParser/master/db.json";
        //textView = findViewById(R.id.textView);
        @SuppressLint("HardwareIds")
//        String ID = Settings.Secure.getString(getContentResolver(),
//        Settings.Secure.ANDROID_ID);
       // textView.setText("Device ID: "+ ID);
        final String SECURE_SETTINGS_BLUETOOTH_ADDRESS = "bluetooth_address";
        String macAddressBT = Settings.Secure.getString(getContentResolver(), SECURE_SETTINGS_BLUETOOTH_ADDRESS);



          for (String s : logarr) {

                activityMainBinding.console.append("\n" + s);
            }

          activityMainBinding.console.append("\t"+macAddressBT);


        jsonArrayRequest = new JsonArrayRequest(mainUsersGetUrl, response -> {
            JSONObject jsonObject = null;
            for (int i=0;i< response.length();i++){
                try {
                    jsonObject = response.getJSONObject(i);
                    String code = jsonObject.getString("macId");
                    Toast.makeText(this, "Sent back Your Device MacID!"+code, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } , error -> Toast.makeText(this, "API connection Error !", Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);






    }

    public void sendMacID(String macIDGet){

        String url = "";
        StringRequest request = new StringRequest(Request.Method.POST,url, response -> {

        } ,error -> {

        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<>();
                    map.put("deviceValid","true");
                    map.put("_id","");
                    map.put("macID","");
                    map.put("authId","");
                    map.put("loginTime","");
                    map.put("__v","");



                return map;
            }
        };
            requestQueue.add(request);








    }




}
