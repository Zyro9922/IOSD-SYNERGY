package com.example.mohitkumar.originalsynergygo.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mohitkumar.originalsynergygo.Adapters.MySingleton;
import com.example.mohitkumar.originalsynergygo.R;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Office extends AppCompatActivity {



    EditText name,designation,mobile,joinDate,desigApp,noYears,companyNature,remarks,detsalary,orgname;
    String sname;
    String sdesignation;
    String smobile;
    String sjoinDate;
    String sdesigApp;
    String snoYears;
    String scompanyNature;
    String sremarks;
    String sjobType;
    String sworkOrg;
    String sjobTransfer;
    String sdetsalary;
    EditText detvarsal;
    String filestr,sdetvarsal,applorcoappl;
    ProgressDialog dialog;
    Button refresh;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat,lng;
    public static final int LOCATION_REQ_CODE = 100;
    public static final int EXTERNAL_STORAGE_CODE = 101;
    LocationManager locationManager;
    Spinner jobType,workOrg,jobTransfer,recomm;
    ArrayAdapter<CharSequence> jobtypeadapter;
    ArrayAdapter<CharSequence> workorgadapter;
    ArrayAdapter<CharSequence> jobtransferadapter,recommadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        applorcoappl = getIntent().getStringExtra("appl_coappl");
        Log.d("TAG",applorcoappl);
        filestr = getIntent().getStringExtra("uniid");
        name=(EditText)findViewById(R.id.personconteditText);
        designation=(EditText)findViewById(R.id.desigPCeditText);
        mobile=(EditText)findViewById(R.id.mobPCeditText);
        //joinDate=(EditText)findViewById(R.id.DateAppjoineditText);
        desigApp=(EditText)findViewById(R.id.DesgAppeditText);
        noYears=(EditText)findViewById(R.id.yearseditText);
        companyNature=(EditText)findViewById(R.id.CompanyNatureeditText);
        remarks=(EditText)findViewById(R.id.OtherRemarkseditText);
        jobType=(Spinner)findViewById(R.id.jobtypespinner);
        workOrg=(Spinner)findViewById(R.id.workingAsspinner);
        jobTransfer=(Spinner)findViewById(R.id.transferspinner);
        detsalary = (EditText)findViewById(R.id.det_ver);
        detvarsal = (EditText) findViewById(R.id.desig_ver);
        orgname = (EditText) findViewById(R.id.nameoforganisation);
        recomm = (Spinner)findViewById(R.id.recomm);

        jobtypeadapter=ArrayAdapter.createFromResource(this,R.array.jobtype,R.layout.support_simple_spinner_dropdown_item);
        jobtypeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobType.setAdapter(jobtypeadapter);

        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {

                    case 0:
                        sjobType="Permanent";
                        break;
                    case 1:
                        sjobType="Probation";
                        break;
                    case 2:
                        sjobType="Contract Worker";
                        break;
                    case 3:
                        sjobType="Temporary Worker";
                        break;
                    case 4:
                        sjobType="Others";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        workorgadapter=ArrayAdapter.createFromResource(this,R.array.workorg,R.layout.support_simple_spinner_dropdown_item);
        workorgadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        workOrg.setAdapter(workorgadapter);

        workOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        sworkOrg="Typist";
                        break;
                    case 1:
                        sworkOrg="Stenographer";
                        break;
                    case 2:
                        sworkOrg="Supervisor";
                        break;
                    case 3:
                        sworkOrg="Junior Management";
                        break;
                    case 4:
                        sworkOrg="Middle Management";
                        break;
                    case 5:
                        sworkOrg="Senior Management";
                        break;
                    case 6:
                        sworkOrg="Other Management";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        jobtransferadapter=ArrayAdapter.createFromResource(this,R.array.transfer,R.layout.support_simple_spinner_dropdown_item);
        jobtransferadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTransfer.setAdapter(jobtransferadapter);

        jobTransfer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i)
                {
                    case 0:
                        sjobTransfer="Yes";
                        break;
                    case 1:
                        sjobTransfer="No";
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recommadapter=ArrayAdapter.createFromResource(this,R.array.recom_or_not,R.layout.support_simple_spinner_dropdown_item);
        recommadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        recomm.setAdapter(jobtransferadapter);

        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        refresh = (Button) findViewById(R.id.refresh);

        boolean permissionCheck = LocationPhoto.Utility.checkPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLocationServicesAvailable(Office.this)) {

                    Log.d("THIS","HERE");
                    dialog = new ProgressDialog(Office.this);
                    dialog.setMessage("Getting Your location....");
                    dialog.show();
                    if (ActivityCompat.checkSelfPermission(Office.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Office.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, locationListener);
                    dialog.dismiss();
                } else {
                    Log.d("THIS","HERE 2");
                    if (ActivityCompat.checkSelfPermission(Office.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Office.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(Office.this);
                    final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
                    final String message = "Enable either GPS or any other location"
                            + " service to find current location.  Click OK to go to"
                            + " location services settings to let you do so.";
                    builder.setTitle("Enable Location");

                    builder.setMessage(message)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface d, int id) {
                                            startActivity(new Intent(action));
                                            d.dismiss();
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface d, int id) {
                                            d.cancel();
                                        }
                                    }).show();
                }

            }
        });

    }

    public void onClickNextso1(View view) {
        filestr=getIntent().getStringExtra("uniid");
        sname=name.getText().toString().trim();
        sdesignation=designation.getText().toString();
        smobile=mobile.getText().toString().trim();
//        sjoinDate=joinDate.getText().toString().trim();
        sworkOrg = workOrg.getSelectedItem().toString();
        sdesigApp=desigApp.getText().toString().trim();
        snoYears=noYears.getText().toString().trim();
        scompanyNature=companyNature.getText().toString().trim();
        sremarks=remarks.getText().toString().trim();
        sdetsalary = detsalary.getText().toString().trim();
        sdetvarsal = detvarsal.getText().toString().trim();
        final String sorgname = orgname.getText().toString();
        final String recom = recomm.getSelectedItem().toString();
        final String latt = lat.getText().toString();
        final String longi = lng.getText().toString();
        Log.d("LONGITUDE",longi);
        Calendar c = Calendar.getInstance();

        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        final String time = hour+":"+minutes+":"+seconds;



        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        final String date = year+"/"+month+"/"+day;

        Log.d("DATE",date);

        String server_url = "http://142.93.217.100/repignite/android/addtotable.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                if(applorcoappl.equals("APPLICANT")) {
                    params.put("tablename","appl_employment");
                } else if(applorcoappl.equals("COAPPLICANT")){
                    params.put("tablename","coappl_employment");
                } else if(applorcoappl.equals("COAPPLICANT2")){
                    params.put("tablename","coappl2_employment");
                }
              

                params.put("REFNO",filestr);
                params.put("PERSONMET",sname);
                params.put("DESIGNAPPL",sdesigApp);
                params.put("PERSONDESIGN",sdesignation);
                params.put("PERSONPHONE",smobile);
                params.put("NOOFYEARS",snoYears);
                params.put("ORGNAME",sorgname);
                params.put("ORGNATURE",scompanyNature);
                params.put("JOBTYPE",sjobType);
                params.put("WORKINGAS",sworkOrg);
                params.put("TRANSFERABLE",sjobTransfer);
                params.put("SALARYPERSON",sdetsalary);
                params.put("SALARYDESIGN",sdetvarsal);
                params.put("RECOMM",recom);
                params.put("REMARKS",sremarks);
                Log.d("LONG",longi);
                params.put("LATITUDE",latt);
                params.put("LONGITUDE",longi);

                params.put("DATEVISIT",date);
                params.put("TIMEVISIT",time);
                return params;

            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        Intent intent = new Intent(Office.this,LocationPhoto.class);
        intent.putExtra("REFNO",filestr);
        intent.putExtra("ADDRESS","EMPLOYMENT");
        intent.putExtra("APPL",applorcoappl);
        startActivity(intent);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                Address address=addresses.get(0);
                String useradd="";
                for(int i=0;i<address.getMaxAddressLineIndex();i++)
                    useradd=useradd+address.getAddressLine(i).toString()+"\n";
                useradd=useradd+(address.getCountryName().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
            latitude = location.getLatitude();
            longitude =location.getLongitude();
            if (latitude != 0 && longitude != 0){

                lat.setText(""+location.getLatitude());
                lng.setText("" +location.getLongitude());

                dialog.dismiss();
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            dialog.dismiss();
        }

        @Override
        public void onProviderDisabled(String provider) {
            dialog.dismiss();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_REQ_CODE){
            if(permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION)  {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)  {
                    dialog.setMessage("Getting Coordinates");
                    dialog.show();
                    //noinspection MissingPermission
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                    dialog = new ProgressDialog(Office.this);
                }
            }
        }
    }

    public static boolean isLocationServicesAvailable(Context context) {
        int locationMode = 0;
        String locationProviders;
        boolean isAvailable = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            isAvailable = (locationMode != Settings.Secure.LOCATION_MODE_OFF);
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            isAvailable = !TextUtils.isEmpty(locationProviders);
        }

        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);

        return isAvailable && (coarsePermissionCheck || finePermissionCheck);
    }

}
