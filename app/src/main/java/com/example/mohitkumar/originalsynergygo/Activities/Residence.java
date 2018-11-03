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
import android.widget.LinearLayout;
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

public class Residence extends AppCompatActivity {

    EditText name,noFamilyMem,workingMem,dependMem,children,twowheeler,fourwheeler,spouseEmp,registration,carpetArea,otherRemarks,mob,noyears,doba,nnoff;
    String sname,snoFamilyMem,sworkingMem,sdependMem,schildren,sspouseEmp,sresidence,smaritalStatus,slocality,sspousework,srecomm,snnoff;
    Spinner residence,maritalStatus,locality,resambience,ncheck,clientcoop,spousework,addlock,relapp,recomm,eduqual;
    ArrayAdapter<CharSequence> residenceadapter,ambienceadapter,educAdapter;
    ArrayAdapter<CharSequence> maritaladapter,ncheckadapter,recommadapter;
    ArrayAdapter<CharSequence> localityadapter,spouseworkadapter,clientcoopadapter,addlockadapter,relationadapter;
    LinearLayout linearLayout;
    Button refresh;
    ProgressDialog dialog;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat,lng;
    public static final int LOCATION_REQ_CODE = 100;
    public static final int EXTERNAL_STORAGE_CODE = 101;
    LocationManager locationManager;
    String sregistration,scarpetArea,spoliticalInflu,sotherRemarks,saddlock,srelapp,smob,snoyears,sdoba,seduqual;
    String filestr,agentid,applorcoappl;
    ArrayAdapter<CharSequence> vehicleadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence);

        getSupportActionBar().setTitle("Fill the Details");

        applorcoappl = getIntent().getStringExtra("appl_coappl");
        filestr = getIntent().getStringExtra("uniid");

        Log.d("TAG",applorcoappl);

        linearLayout = (LinearLayout) findViewById(R.id.lin_s_emp);
        name= (EditText)findViewById(R.id.Personnameet);
        noFamilyMem=(EditText)findViewById(R.id.FamilymemeditText);
        workingMem=(EditText)findViewById(R.id.workingmemeditText);
        dependMem=(EditText)findViewById(R.id.dependenteditText);
        children=(EditText)findViewById(R.id.ChildreneditText);
        spouseEmp=(EditText)findViewById(R.id.SpouseeditText);
        residence =(Spinner) findViewById(R.id.ResStatusSpinner);
        maritalStatus=(Spinner) findViewById(R.id.MaritalStatusSpinner);
        locality=(Spinner) findViewById(R.id.Localityspinner);
        registration= (EditText)findViewById(R.id.RegNoeditText);
        carpetArea= (EditText)findViewById(R.id.CarpetAreaeditText);
        //politicalInflu= (EditText)findViewById(R.id.PoliticaleditText);
        otherRemarks= (EditText)findViewById(R.id.OtherRemarkseditText);
        twowheeler=(EditText) findViewById(R.id.two_wheeler);
        fourwheeler = (EditText) findViewById(R.id.fourwheelerseen);
        resambience = (Spinner) findViewById(R.id.res_ambience);
        relapp = (Spinner) findViewById(R.id.rel_Appl);
        mob = (EditText) findViewById(R.id.mobno);
        recomm = (Spinner) findViewById(R.id.recomm);
        noyears = (EditText) findViewById(R.id.noyears);
        doba = (EditText) findViewById(R.id.dobapp);
        addlock = (Spinner) findViewById(R.id.add_check);
        eduqual = (Spinner) findViewById(R.id.eduqual);
        ncheck = (Spinner) findViewById(R.id.neighbour_check);
        clientcoop = (Spinner) findViewById(R.id.coopspinner);
        nnoff = (EditText) findViewById(R.id.snnoffam);
        spousework = (Spinner)findViewById(R.id.SpouseWorkSpinner);

        residenceadapter=ArrayAdapter.createFromResource(this,R.array.resstatus,R.layout.support_simple_spinner_dropdown_item);
        residenceadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        residence.setAdapter(residenceadapter);

        residence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        sresidence="Self Owned";
                        break;
                    case 1:
                        sresidence="Owned By Relatives";
                        break;
                    case 2:
                        sresidence=" Rented";
                        break;
                    case 3:
                        sresidence="Paying Guest";
                        break;
                    case 4:
                        sresidence="Owned by Parents";
                        break;
                    case 5:
                        sresidence=" Owned by Friends";
                        break;
                    case 6:
                        sresidence="Company Accommodation";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        maritaladapter=ArrayAdapter.createFromResource(this,R.array.marital,R.layout.support_simple_spinner_dropdown_item);
        maritaladapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        maritalStatus.setAdapter(maritaladapter);

        maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        smaritalStatus = "Single";
                        break;
                    case 1:
                        smaritalStatus = "Married";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clientcoopadapter=ArrayAdapter.createFromResource(this,R.array.polite_rude,R.layout.support_simple_spinner_dropdown_item);
        clientcoopadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        clientcoop.setAdapter(clientcoopadapter);

        ncheckadapter=ArrayAdapter.createFromResource(this,R.array.n_check,R.layout.support_simple_spinner_dropdown_item);
        ncheckadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ncheck.setAdapter(ncheckadapter);

        addlockadapter=ArrayAdapter.createFromResource(this,R.array.transfer,R.layout.support_simple_spinner_dropdown_item);
        addlockadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        addlock.setAdapter(addlockadapter);

        spouseworkadapter = ArrayAdapter.createFromResource(this,R.array.transfer,R.layout.support_simple_spinner_dropdown_item);
        spouseworkadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spousework.setAdapter(spouseworkadapter);

        spousework.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        sspousework = "YES";
                        spouseEmp.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        sspousework = "NO";
                        linearLayout.setVisibility(View.GONE);
                        spouseEmp.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ambienceadapter = ArrayAdapter.createFromResource(this,R.array.ambience,R.layout.support_simple_spinner_dropdown_item);
        ambienceadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        resambience.setAdapter(ambienceadapter);

        educAdapter = ArrayAdapter.createFromResource(this, R.array.edu, R.layout.support_simple_spinner_dropdown_item);
        educAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        eduqual.setAdapter(educAdapter);

        recommadapter = ArrayAdapter.createFromResource(this, R.array.recom_or_not, R.layout.support_simple_spinner_dropdown_item);
        recommadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        recomm.setAdapter(recommadapter);

        localityadapter=ArrayAdapter.createFromResource(this,R.array.locality,R.layout.support_simple_spinner_dropdown_item);
        localityadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locality.setAdapter(localityadapter);

        locality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {

                    case 0:
                        slocality="Posh Locality";
                        break;
                    case 1:
                        slocality="Village Area";
                        break;
                    case 2:
                        slocality="Lodging";
                        break;
                    case 3:
                        slocality="Upper Middle Class";
                        break;
                    case 4:
                        slocality="Lower Middle Class";
                        break;
                    case 5:
                        slocality="Slum Locality";
                        break;
                    case 6:
                        slocality="Middle Class";
                        break;
                    case 7:
                        slocality="Residential Complex";
                        break;
                    case 8:
                        slocality="Others";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        relationadapter = ArrayAdapter.createFromResource(this,R.array.rel_appl,R.layout.support_simple_spinner_dropdown_item);
        relationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        relapp.setAdapter(relationadapter);


        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        refresh = (Button) findViewById(R.id.refresh);

        boolean permissionCheck = LocationPhoto.Utility.checkPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLocationServicesAvailable(Residence.this)) {

                    Log.d("THIS","HERE");
                    dialog = new ProgressDialog(Residence.this);
                    dialog.setMessage("Getting Your location....");
                    dialog.show();
                    if (ActivityCompat.checkSelfPermission(Residence.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Residence.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, locationListener);
                    dialog.dismiss();
                } else {
                    Log.d("THIS","HERE 2");
                    if (ActivityCompat.checkSelfPermission(Residence.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Residence.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(Residence.this);
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

    public void onClicknextsr1(View view) {

        sname = name.getText().toString();
        snoFamilyMem = noFamilyMem.getText().toString();
        sworkingMem = workingMem.getText().toString();
        sdependMem = dependMem.getText().toString();
        schildren = children.getText().toString();
        sspouseEmp = spouseEmp.getText().toString();
        sresidence = residence.getSelectedItem().toString();
        smaritalStatus = maritalStatus.getSelectedItem().toString();
        slocality = locality.getSelectedItem().toString();
        sregistration = registration.getText().toString();
        scarpetArea = carpetArea.getText().toString();
        //spoliticalInflu = politicalInflu.getText().toString();
        sotherRemarks = otherRemarks.getText().toString();
        srelapp = relapp.getSelectedItem().toString();
        smob = mob.getText().toString();
        snoyears = noyears.getText().toString();
        sdoba = doba.getText().toString();
        seduqual = eduqual.getSelectedItem().toString();
        saddlock = addlock.getSelectedItem().toString();
        srecomm = recomm.getSelectedItem().toString();
        snnoff = nnoff.getText().toString();
        final String sncheck =ncheck.getSelectedItem().toString();
        final String sclientcoop = clientcoop.getSelectedItem().toString();
        final String stwo = twowheeler.getText().toString();
        final String sfour = fourwheeler.getText().toString();
        sspousework = spousework.getSelectedItem().toString();
        final String sambience = resambience.getSelectedItem().toString();
        final String latt = lat.getText().toString();
        Log.d("LATITUDE",latt);
        final String longi = lng.getText().toString();

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
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"No connection",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                Log.d("APPL",applorcoappl);
                //Toast.makeText(getApplicationContext(),"Inside the post method",Toast.LENGTH_LONG).show();

                if(applorcoappl.equals("APPLICANT")) {
                    params.put("tablename","appl_residence");
                } else if(applorcoappl.equals("COAPPLICANT")){
                    params.put("tablename","coappl_residence");
                } else if(applorcoappl.equals("COAPPLICANT2")){
                    params.put("tablename","coappl2_residence");
                }

                params.put("REFNO",filestr);
                params.put("DATEVISIT",date);
                params.put("TIMEVISIT",time);
                params.put("PERSONMET",sname);
                params.put("RELATIONAPPL",srelapp);
                params.put("PERSONPHONE",smob);
                params.put("NOOFYEARS",snoyears);
                params.put("DOBAPPL",sdoba);
                params.put("EDUQUAL",seduqual);
                params.put("RESISTATUS",sresidence);
                params.put("MARITALSTATUS",smaritalStatus);
                params.put("NOOFFAMILY",snoFamilyMem);
                params.put("WORKING",sworkingMem);
                params.put("ADULTSDEP",sdependMem);
                params.put("CHILDDEP",schildren);
                params.put("SPOUSEWORK",sspousework);
                params.put("SPOUSEEMP",sspouseEmp);
                params.put("COOPERATIVE",sclientcoop);
                params.put("NEIGHBORHOOD",sncheck);
                params.put("LOCALITY",slocality);
                params.put("AMBIENCE",sambience);
                params.put("CARPETAREA",scarpetArea);
                params.put("NAPPLSTAY",saddlock);
                params.put("NNOOFFAMILY",snnoff);
                params.put("WHEELER2",stwo);
                params.put("WHEELER4",sfour);
                Log.d("LATT",latt);
                params.put("LATITUDE",latt);
                params.put("LONGITUDE",longi);

                params.put("RECOMM",srecomm);
                params.put("REMARKS",sotherRemarks);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        Log.d("REFNO",filestr);
        Intent intent = new Intent(Residence.this,LocationPhoto.class);
        intent.putExtra("REFNO",filestr);
        intent.putExtra("ADDRESS","RESIDENCE");
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
                lng.setText(""+location.getLongitude());

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
                    dialog = new ProgressDialog(Residence.this);
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
