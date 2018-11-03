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

public class Business extends AppCompatActivity {

    EditText caseNo,location,applicantName,address,altTele,fosName,compName,desigOfAppli,workingSince,
            personContacted,desigOfPersonMet,noOfEmp,landmark,noOfBranches,noOfYears,contactVer1,contactVer2,addproof;

    Spinner pdaNo,easeLoc,officeOwnership,typeLocality,natureOfBusi,busiSetup,busiBoard,visitingCard,
            appliVerifiedfrom,verifFeed,polLink,negative,status;


    String scaseNo,slocation,sapplicantName,saddress,saltTele,sfosName,scompName,sdesigOfAppli,sworkingSince,
            spersonContacted,sdesigOfPersonMet,snoOfEmp,slandmark,snoOfBranches,snoOfYears,scontactVer1,scontactVer2,saddproof;

    String spdaNo,seaseLoc,sofficeOwnership,stypeLocality,snatureOfBusi,sbusiSetup,sbusiBoard,svisitingCard,
            sappliVerifiedfrom,sverifFeed,spolLink,snegative,sstatus;


    String filestr,agenti,applorcoappl;
    ProgressDialog dialog;
    Button refresh;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat,lng;
    public static final int LOCATION_REQ_CODE = 100;
    public static final int EXTERNAL_STORAGE_CODE = 101;
    LocationManager locationManager;
    String stypeCompany,snameboard,sambience,sexterior,sbact,slocate,sempsighted,spolaffl;

    /**
     * ARRAY ADAPTERS
     */

    ArrayAdapter<CharSequence> easeLocAdapter,officeOwnershipAdapter,typeLocalityAdapter,
            natureOfBusiAdapter, busiSetupAdapter, busiBoardAdapter,visitingCardAdapter,appliVerifiedfromAdapter,
            veriFeedAdapter,polLinkAdapter,negativeAdapter,statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        applorcoappl = getIntent().getStringExtra("appl_coappl");
        filestr = getIntent().getExtras().getString("uniid");

        Log.d("TAG",applorcoappl);
        Log.d("TAG56",filestr);


        //EDITTEXTS
        caseNo= (EditText)findViewById(R.id.caseNo);
        location= (EditText)findViewById(R.id.location);
        applicantName= (EditText)findViewById(R.id.applicantName);
        address= (EditText)findViewById(R.id.address);
        altTele= (EditText)findViewById(R.id.altTele);
        fosName= (EditText)findViewById(R.id.fosName);
        compName= (EditText)findViewById(R.id.compName);
        desigOfAppli= (EditText)findViewById(R.id.desigOfAppli);
        workingSince= (EditText)findViewById(R.id.workingSince);
        personContacted= (EditText)findViewById(R.id.personContacted);
        desigOfPersonMet= (EditText)findViewById(R.id.desigOfPersonMet);
        noOfEmp= (EditText)findViewById(R.id.noOfEmp);
        landmark= (EditText)findViewById(R.id.landmark);
        noOfBranches= (EditText)findViewById(R.id.noOfBranches);
        noOfYears= (EditText)findViewById(R.id.noOfYears);
        contactVer1= (EditText)findViewById(R.id.contactVer1);
        contactVer2= (EditText)findViewById(R.id.contactVer2);
        addproof= (EditText)findViewById(R.id.addproof);

        //SPINNERS
        pdaNo = (Spinner)findViewById(R.id.pdaNo);
        easeLoc = (Spinner)findViewById(R.id.easeLoc);
        officeOwnership = (Spinner)findViewById(R.id.officeOwnership);
        typeLocality = (Spinner)findViewById(R.id.typeLocality);
        natureOfBusi = (Spinner)findViewById(R.id.natureOfBusi);
        busiSetup = (Spinner)findViewById(R.id.busiSetup);
        busiBoard = (Spinner)findViewById(R.id.busiBoard);
        visitingCard = (Spinner)findViewById(R.id.visitingCard);
        appliVerifiedfrom = (Spinner)findViewById(R.id.appliVerifiedfrom);
        verifFeed = (Spinner) findViewById(R.id.verifFeed);
        polLink = (Spinner)findViewById(R.id.polLink);
        negative = (Spinner)findViewById(R.id.negative);
        status = (Spinner)findViewById(R.id.status);

        /**
         * ADAPTERS
         */
        //LEft space for pda



        easeLocAdapter = ArrayAdapter.createFromResource(this, R.array.easeLoc, R.layout.support_simple_spinner_dropdown_item);
        easeLocAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        easeLoc.setAdapter(easeLocAdapter);

        officeOwnershipAdapter = ArrayAdapter.createFromResource(this, R.array.officeOwnership, R.layout.support_simple_spinner_dropdown_item);
        officeOwnershipAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        officeOwnership.setAdapter(officeOwnershipAdapter);

        typeLocalityAdapter = ArrayAdapter.createFromResource(this, R.array.officeOwnership, R.layout.support_simple_spinner_dropdown_item);
        typeLocalityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeLocality.setAdapter(typeLocalityAdapter);

        natureOfBusiAdapter = ArrayAdapter.createFromResource(this, R.array.natureOfBusi, R.layout.support_simple_spinner_dropdown_item);
        natureOfBusiAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        natureOfBusi.setAdapter(natureOfBusiAdapter);

        busiSetupAdapter = ArrayAdapter.createFromResource(this, R.array.officeOwnership, R.layout.support_simple_spinner_dropdown_item);
        busiSetupAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        busiSetup.setAdapter(busiSetupAdapter);

        busiBoardAdapter = ArrayAdapter.createFromResource(this, R.array.busiBoard, R.layout.support_simple_spinner_dropdown_item);
        busiBoardAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        busiBoard.setAdapter(busiBoardAdapter);

        visitingCardAdapter = ArrayAdapter.createFromResource(this, R.array.visitingCard, R.layout.support_simple_spinner_dropdown_item);
        visitingCardAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        visitingCard.setAdapter(visitingCardAdapter);

        appliVerifiedfromAdapter = ArrayAdapter.createFromResource(this, R.array.appliVerifiedfrom, R.layout.support_simple_spinner_dropdown_item);
        appliVerifiedfromAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        appliVerifiedfrom.setAdapter(appliVerifiedfromAdapter);

        veriFeedAdapter = ArrayAdapter.createFromResource(this, R.array.verifFeed, R.layout.support_simple_spinner_dropdown_item);
        veriFeedAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        appliVerifiedfrom.setAdapter(veriFeedAdapter);

        polLinkAdapter = ArrayAdapter.createFromResource(this, R.array.polLink, R.layout.support_simple_spinner_dropdown_item);
        polLinkAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        polLink.setAdapter(polLinkAdapter);

        negativeAdapter = ArrayAdapter.createFromResource(this, R.array.negative, R.layout.support_simple_spinner_dropdown_item);
        negativeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        negative.setAdapter(negativeAdapter);

        statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, R.layout.support_simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);


//        typecompadapter = ArrayAdapter.createFromResource(this, R.array.company, R.layout.support_simple_spinner_dropdown_item);
//        typecompadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        typeCompany.setAdapter(typecompadapter);
//
//        nameBoardadapter = ArrayAdapter.createFromResource(this, R.array.name_board, R.layout.support_simple_spinner_dropdown_item);
//        nameBoardadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        nameboard.setAdapter(nameBoardadapter);

        /**
         * SPINNER SET ON ITEM SELECTED LISTENER
         */

        easeLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seaseLoc = easeLoc.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                seaseLoc = "";
            }
        });

        officeOwnership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sofficeOwnership = officeOwnership.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sofficeOwnership = "";
            }
        });

        typeLocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stypeLocality = typeLocality.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stypeLocality = "";
            }
        });

        natureOfBusi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                snatureOfBusi = natureOfBusi.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                snatureOfBusi = "";
            }
        });

        busiSetup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sbusiSetup = busiSetup.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sbusiSetup = "";
            }
        });

        busiBoard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sbusiBoard = busiBoard.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sbusiBoard = "";
            }
        });

        visitingCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                svisitingCard = visitingCard.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                svisitingCard = "";
            }
        });

        appliVerifiedfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sappliVerifiedfrom = appliVerifiedfrom.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sappliVerifiedfrom = "";
            }
        });

        verifFeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sverifFeed = verifFeed.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sverifFeed = "";
            }
        });

        officeOwnership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        polLink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spolLink = polLink.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spolLink = "";
            }
        });

        negative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                snegative = negative.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                snegative = "";
            }
        });

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sstatus = status.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sstatus = "";
            }
        });

        /**
         * END OF SPINNER THING
         */


        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        refresh = (Button) findViewById(R.id.refresh);

        boolean permissionCheck = LocationPhoto.Utility.checkPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLocationServicesAvailable(Business.this)) {

                    Log.d("THIS","HERE");
                    dialog = new ProgressDialog(Business.this);
                    dialog.setMessage("Getting Your location....");
                    dialog.show();
                    if (ActivityCompat.checkSelfPermission(Business.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Business.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                    if (ActivityCompat.checkSelfPermission(Business.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Business.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                            new AlertDialog.Builder(Business.this);
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

    public void onClickNextb1(View view) {

        ProgressDialog progressDialog = new ProgressDialog(Business.this);
        progressDialog.setTitle("Uploadng");
        progressDialog.setMessage("Pushing your details....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //SETTING STRINGS FROM EDITITEXTS
        scaseNo = caseNo.getText().toString().trim();
        slocate = location.getText().toString().trim();
        sapplicantName = applicantName.getText().toString().trim();
        saddress = address.getText().toString().trim();
        saltTele = altTele.getText().toString().trim();
        sfosName = fosName.getText().toString().trim();
        scompName = compName.getText().toString().trim();
        sdesigOfAppli = desigOfAppli.getText().toString().trim();
        sworkingSince = workingSince.getText().toString().trim();
        spersonContacted = personContacted.getText().toString().trim();
        sdesigOfAppli = desigOfAppli.getText().toString().trim();
        sdesigOfPersonMet = desigOfPersonMet.getText().toString().trim();
        snoOfEmp = noOfEmp.getText().toString().trim();
        snoOfBranches = noOfBranches.getText().toString().trim();
        snoOfYears = noOfYears.getText().toString().trim();
        scontactVer1 = contactVer1.getText().toString().trim();
        scontactVer2 = contactVer2.getText().toString().trim();
        saddproof = addproof.getText().toString().trim();

        final String latt = lat.getText().toString();
        final String longi = lng.getText().toString();
        Log.d("LONGITUDE",longi);
        Calendar c = Calendar.getInstance();

        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        final String time = hour+":"+minutes+":"+seconds;


        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        final String date = year+"/"+month+"/"+day;
        Log.d("DATE",date);

        String server_url = "http://142.93.217.100/repignite/android/addtotable.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE",response);
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
                Map<String,String> params = new HashMap<>();

                if(applorcoappl.equals("APPLICANT")) {
                    params.put("tablename","appl_business");
                } else if(applorcoappl.equals("COAPPLICANT")){
                    params.put("tablename","coappl_business");
                } else if(applorcoappl.equals("COAPPLICANT2")){
                    params.put("tablename","coappl2_business");
                }

                Log.d("IN HERE","Reached");

                Log.d("REFNO",filestr);

                params.put("REFNO",filestr);
                params.put("DATEVISIT",date);
                params.put("TIMEVISIT",time);


                params.put("CASENO",scaseNo);
                params.put("LOCATION",slocation);
                params.put("APPLICANTNAME",sapplicantName);
                params.put("ADDRESS",saddress);
                params.put("ALTTELE",saltTele);
                params.put("FOSNAME",sfosName);
                params.put("COMPNAME",scompName);
                params.put("DESIGOFAPPLI",sdesigOfAppli);
                params.put("WORKINGSINCE",sworkingSince);
                params.put("PERSONCONTACTED",spersonContacted);
                params.put("DESIGPERSONMENT",sdesigOfPersonMet);
                params.put("NOOFEMP",snoOfEmp);
                params.put("LANDMARK",slandmark);
                params.put("NOOFBRANCHES",snoOfBranches);
                params.put("NOOFYEARS",snoOfYears);
                params.put("CONTACTVER1",scontactVer1);
                params.put("CONTACTVER2",scontactVer2);
                params.put("ADDPROOF",saddproof);
//                params.put(,pdaNo);
                params.put("EASELOC",seaseLoc);
                params.put("OFFICEOWNERSHIP",sofficeOwnership);
                params.put("TYPELOCALITY",stypeLocality);
                params.put("NATUREOFBUSINESS",snatureOfBusi);
                params.put("BUSINESSSETUP",sbusiSetup);
                params.put("BUSINESSBOARD",sbusiBoard);
                params.put("VISTINGCARD",svisitingCard);
                params.put("APPLIVERIFIEDFROM",sappliVerifiedfrom);
                params.put("VERIFEED",sverifFeed);
                params.put("POLLINK",spolLink);
                params.put("NEGATIVE",snegative);
                params.put("STATUS",sstatus);


                params.put("ORGTYPE", stypeCompany);
                params.put("BOOLBOARD",snameboard);
                params.put("AMBIENCE",sambience);
                params.put("EXTERIOR",sexterior);
                params.put("EASELOCATE",slocate);
                params.put("ORGACTIVITY",sbact);
                params.put("POLPARTYAFFL",spolaffl);
                params.put("SEENNOOFEMPL",sempsighted);
                Log.d("LONG",longi);
                params.put("LATITUDE",latt);
                params.put("LONGITUDE",longi);
                return params;
            }
        };

        MySingleton.getInstance(Business.this).addToRequestQueue(stringRequest);

        progressDialog.dismiss();

        Log.d("REFNO",filestr);
        Intent intent = new Intent(Business.this,LocationPhoto.class);
        intent.putExtra("REFNO",filestr);
        intent.putExtra("ADDRESS","BUSINESS");
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
                    dialog = new ProgressDialog(Business.this);
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
