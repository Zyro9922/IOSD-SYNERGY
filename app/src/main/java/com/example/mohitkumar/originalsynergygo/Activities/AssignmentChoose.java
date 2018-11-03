package com.example.mohitkumar.originalsynergygo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mohitkumar.originalsynergygo.Models.CardDetails;
import com.example.mohitkumar.originalsynergygo.Adapters.MySingleton;
import com.example.mohitkumar.originalsynergygo.R;
import com.example.mohitkumar.originalsynergygo.Adapters.RecyclerCardAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignmentChoose extends AppCompatActivity {


    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    public static String AgentID;
    ArrayList<String> fi = new ArrayList<String>();
    ArrayList<CardDetails> list = new ArrayList<CardDetails>();
    public static final int EXTERNAL_STORAGE_CODE = 101;
    String json_url = "http://142.93.217.100/repignite/android/fetchallocations.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_choose);


        AgentID = getIntent().getStringExtra("Agent");
        getSupportActionBar().setTitle("Agent ID : " + AgentID);
        recyclerView = (RecyclerView)findViewById(R.id.recyc_view);

        if(isNetworkAvailable(getApplicationContext())) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Wait...");
            progressDialog.show();
            progressDialog.setCancelable(true);

            layoutManager = new LinearLayoutManager(AssignmentChoose.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
          //  BackgroundTask backGroundTask = new BackgroundTask(AssignmentChoose.this,AgentID);

            StringRequest stringRequest = new StringRequest(Request.Method.POST,json_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("TAG",response.toString());
                    int count = 0;
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        Log.d("Length", String.valueOf(jsonArray.length()));
                        while(count<jsonArray.length()) {
                            JSONObject jsonObject = jsonArray.getJSONObject(count);
                            CardDetails cardDetails = new CardDetails(jsonObject.getString("REFNO"),jsonObject.getString("DOR"),jsonObject.getString("NAME"),jsonObject.getString("ADDR"),jsonObject.getString("MOBILE"),jsonObject.getString("FOS"),
                                    jsonObject.getString("APPLORCO"),jsonObject.getString("TYPE"));
                            list.add(cardDetails);
                            count++;
                            Log.d("TAG 1 ",jsonObject.getString("REFNO"));
                        }
                        Log.d("IN HERE","IN HERE");
                        adapter = new RecyclerCardAdapter(AssignmentChoose.this,list);
                        recyclerView.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"No connection",Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    Log.d("FIRST HERE",AgentID);
                    params.put("fosname",AgentID);
                    return params;

                }
            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

          //  Log.d("list",list.toString());

            progressDialog.dismiss();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No Internet Connection...")
                    .setMessage("Click Here to set Active connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.error)
                    .show();
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    //    public ArrayList<CardDetails> getList() {
//        final ArrayList<CardDetails> arrayList = new ArrayList<CardDetails>();
//        ProgressDialog progressDialog = new ProgressDialog(AssignmentChoose.this);
//        progressDialog.setTitle("Wait..");
//        progressDialog.setMessage("Adding you to our network");
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,json_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("TAG",response.toString());
//                int count = 0;
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    Log.d("Length", String.valueOf(jsonArray.length()));
//                    while(count<jsonArray.length()) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(count);
//                        CardDetails cardDetails = new CardDetails(jsonObject.getString("REFNO"),jsonObject.getString("DOR"),jsonObject.getString("NAME"),jsonObject.getString("ADDR"),jsonObject.getString("MOBILE"),jsonObject.getString("FOS"),
//                                jsonObject.getString("APPLORCO"),jsonObject.getString("TYPE"));
//                        arrayList.add(cardDetails);
//                        count++;
//                        Log.d("TAG 1 ",jsonObject.getString("REFNO"));
//                    }
//                    Log.d("IN HERE","IN HERE");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"No connection",Toast.LENGTH_LONG).show();
//                error.printStackTrace();
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                Log.d("FIRST HERE",AgentID);
//                params.put("fosname",AgentID);
//                return params;
//
//            }
//        };
//        Log.d("String","Returned Arraylist");
//        return arrayList;
//    }
}
