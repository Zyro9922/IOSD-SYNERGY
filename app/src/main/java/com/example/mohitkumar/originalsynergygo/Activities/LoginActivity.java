package com.example.mohitkumar.originalsynergygo.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mohitkumar.originalsynergygo.Adapters.MySingleton;
import com.example.mohitkumar.originalsynergygo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    EditText pass;
    String server_url = "http://142.93.217.100/repignite/android/fetchtable.php";
    Spinner agentId;
    ArrayAdapter<String> agentAdapter;
    String id,passw,tablename;
    public String AgentIDin,PassIn;
    ArrayList<String> list = new ArrayList<String>();
    public static final int EXTERNAL_STORAGE_CODE = 101;
    HashMap<String,String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("TAG","In oncreate");

        agentId=(Spinner) findViewById(R.id.AgenitIspinner);
        pass=(EditText)findViewById(R.id.PasseditText);

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Gathering Info");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (isNetworkAvailable(getApplicationContext())) {

            Log.d("IN NETWORK","YES");


            final ArrayList<String> arrayList = new ArrayList<String>();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("TAG1",response);
                        JSONArray jsonArray = new JSONArray(response);
                        int count = 0;
                        while(count<jsonArray.length()){
                            JSONObject jsonObject = jsonArray.getJSONObject(count);
                            String name = jsonObject.getString("FOSNAME");
                            String pass = jsonObject.getString("FOSPASS");
                            list.add(pass);
                            arrayList.add(name);
                            map.put(name,pass);
                            Log.d("NAMES",name);
                            count++;
                        }

                        String[] names = arrayList.toArray(new String[arrayList.size()]);
                        agentAdapter = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,names);
                        agentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        agentId.setAdapter(agentAdapter);



                        //Log.d("passw",passw);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    tablename = params.put("tablename","fosdetails");

                    return params;
                }
            };

            MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);


            progressDialog.dismiss();
//            String[] names = list.toArray(new String[list.size()]);
//            agentAdapter = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,names);
//            agentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else {
            Log.d("IN NETWORK","YES");
        }


    }

    public boolean isNetworkAvailable(final Context context) {
        Log.d("Inside","INIT");
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void onClickLogin(View view) {

        AgentIDin = agentId.getSelectedItem().toString();
        PassIn = pass.getText().toString();

        Log.d("PassIn",PassIn);
        if (AgentIDin.equals("") || PassIn.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_LONG).show();
        } else {

            Log.d("AGENT", AgentIDin);
//
            passw = map.get(AgentIDin);

            if (passw != null) {
                if (passw.equals(PassIn)) {
                    Intent intent = new Intent(LoginActivity.this, AssignmentChoose.class);
                    intent.putExtra("Agent", AgentIDin);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Details please try again.", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.d("PASSWORD","NULL");
            }
        }
          //Toast.makeText(getApplicationContext(), "Check your Network", Toast.LENGTH_LONG).show();

//        Intent intent = new Intent(LoginActivity.this, AssignmentChoose.class);
//        startActivity(intent);
    }

//    public void fetchDetails() {
//
//        final ArrayList<String> arrayList = new ArrayList<String>();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    //Log.d("TAG",response);
//                    JSONArray jsonArray = new JSONArray(response);
//                    int count = 0;
//                    while(count<jsonArray.length()){
//                        JSONObject jsonObject = jsonArray.getJSONObject(count);
//                        String name = jsonObject.getString("FOSNAME");
//                        arrayList.add(name);
//                        Log.d("NAMES",name);
//                    }
//
//                    String[] names = arrayList.toArray(new String[arrayList.size()]);
//                    agentAdapter = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,names);
//                    agentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    agentId.setAdapter(agentAdapter);
//
//                    Log.d("passw",passw);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                tablename = params.put("tablename","fosdetails");
//
//                return params;
//            }
//        };
//
//        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
//       // return arrayList;
//    }
}
