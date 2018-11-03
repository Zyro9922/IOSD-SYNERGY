package com.example.mohitkumar.originalsynergygo.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mohitkumar.originalsynergygo.Models.CardDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohitkumar on 12/04/17.
 */

public class BackgroundTask {

    Context context;
    String name;
    ArrayList<CardDetails> arrayList = new ArrayList<CardDetails>();

    String json_url = "http://142.93.217.100/repignite/android/fetchallocations.php";



    public BackgroundTask(Context context,String name) {
        this.context = context;
        this.name = name;
    }

    public ArrayList<CardDetails> getList() {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Wait..");
        progressDialog.setMessage("Adding you to our network");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url, new Response.Listener<String>() {
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
                           arrayList.add(cardDetails);
                        count++;
                        Log.d("TAG 1 ",jsonObject.getString("REFNO"));
                   }
                   Log.d("IN HERE","IN HERE");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"No connection",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                Log.d("FIRST HERE", name);
                params.put("fosname", name);
                return params;

            }
        };
        Log.d("String","Returned Arraylist");
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        return arrayList;
    }
}

//         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (JSONArray) null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                int count = 0;
//                Log.d("NOW HERE","IN HERE");
//                while(count<response.length()) {
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(count);
//                        CardDetails cardDetails = new CardDetails(jsonObject.getString("REFNO"),jsonObject.getString("DOR"),jsonObject.getString("NAME"),jsonObject.getString("ADDRESS"),jsonObject.getString("MOBILE"),jsonObject.getString("FOS"),
//                              jsonObject.getString("APPLORCO"),jsonObject.getString("TYPE"));
//                           arrayList.add(cardDetails);
//                        count++;
//                        Log.d("TAG",jsonObject.getString("REFNO"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })


//             @Override
//             protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
//
//                 try {
//                     String jsonString = new String(response.data,
//                             HttpHeaderParser
//                                     .parseCharset(response.headers));
//                     return Response.success(new JSONArray(jsonString),
//                             HttpHeaderParser
//                                     .parseCacheHeaders(response));
//                 } catch (UnsupportedEncodingException e) {
//                     return Response.error(new ParseError(e));
//                 } catch (JSONException je) {
//                     return Response.error(new ParseError(je));
//                 }
//
//             }