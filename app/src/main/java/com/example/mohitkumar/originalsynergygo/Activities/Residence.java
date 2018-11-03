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

    EditText caseNo,location,applicantName,address,alternateTelephone,fosName,age,landmark,stayingSince,personContacted,noFamilyMem,working,dependMemAdult, dependMemChild,retiredorpensioner,spouseEmp, neibhourName1, neibhourName2, address1, address2, addProof;

    String scaseNo,slocation,sapplicantName,saddress,salternateTelephone,sfosName,sage,slandmark,sstayingSince,spersonContacted,snoFamilyMem,sworking,sdependMemAdult, sdependMemChild,sretiredorpensioner,sspouseEmp, sneibhourName1, sneibhourName2, saddress1, saddress2, saddProof;

    Spinner pdaNo, easetoLocate,typeOfLocality,typeOfHouse,houseCondition,ownership,standardOfLiving,doesApplicantStay, relationship ,
            exterior, spouseEarning, maritalStatus, educationQualification, neighbourFeedback,accomodationType,vehicleSeen, politicalLink,
            overallStatus, resonfornegativeFI;

    String spdaNo, seasetoLocate,stypeOfLocality,stypeOfHouse,shouseCondition,sownership,sstandardOfLiving,sdoesApplicantStay,srelationship ,
            sexterior, sspouseEarning, smaritalStatus, seducationQualification, sneighbourFeedback,saccomodationType,svehicleSeen, spoliticalLink,
            soverallStatus, sresonfornegativeFI;

    ArrayAdapter<CharSequence> pdaNoadapter, easetoLocateadapter,typeOfLocalityadapter,typeOfHouseadapter,houseConditionadapter,ownershipadapter,standardOfLivingadapter,doesApplicantStayadapter, relationshipadapter ,exterioradapter, spouseEarningadapter, maritaladapter, educationQualificationadapter, neighbourFeedbackadapter,accomodationTypeadapter,vehicleSeenadapter, politicalLinkadapter, overallStatusadapter, resonfornegativeFIadapter;

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
        caseNo= (EditText)findViewById(R.id.caseNo);
        noFamilyMem=(EditText)findViewById(R.id.noOfFamilyMember);
        location=(EditText)findViewById(R.id.location);
        applicantName=(EditText)findViewById(R.id.applicantName);
        address=(EditText)findViewById(R.id.address);
        spouseEmp=(EditText)findViewById(R.id.SpouseeditText);
        pdaNo =(Spinner) findViewById(R.id.pdaNo);
        maritalStatus=(Spinner) findViewById(R.id.MaritalStatusSpinner);
        easetoLocate=(Spinner) findViewById(R.id.easeToLocate);
        alternateTelephone= (EditText)findViewById(R.id.altTele);
        fosName= (EditText)findViewById(R.id.fosName);
        //politicalInflu= (EditText)findViewById(R.id.PoliticaleditText);
        age= (EditText)findViewById(R.id.age);
        landmark=(EditText) findViewById(R.id.landmark);
        stayingSince = (EditText) findViewById(R.id.stayingSince);
        typeOfLocality = (Spinner) findViewById(R.id.localityType);
        typeOfHouse = (Spinner) findViewById(R.id.houseType);
        houseCondition = (Spinner) findViewById(R.id.houseCondition);
        personContacted = (EditText) findViewById(R.id.personContacted);
        ownership = (Spinner) findViewById(R.id.ownership);
        noFamilyMem = (EditText) findViewById(R.id.noOfFamilyMember);
        working = (EditText) findViewById(R.id.working);
        standardOfLiving = (Spinner) findViewById(R.id.standardOfLiving);
        doesApplicantStay = (Spinner) findViewById(R.id.doesApplicantStay);
        relationship = (Spinner) findViewById(R.id.relationship);
        accomodationType = (Spinner) findViewById(R.id.accomodationType);
        dependMemAdult = (EditText) findViewById(R.id.dependentAdult);
        exterior = (Spinner)findViewById(R.id.exterior);
        dependMemChild = (EditText) findViewById(R.id.dependentChild);
        retiredorpensioner = (EditText) findViewById(R.id.retiredorpensioner);
        spouseEmp = (EditText) findViewById(R.id.spouseWorkingDetail);
        spouseEarning = (Spinner)findViewById(R.id.spouseEarning);
        maritalStatus = (Spinner)findViewById(R.id.maritialStatus);
        educationQualification = (Spinner)findViewById(R.id.educationQulification);
        neibhourName1 = (EditText) findViewById(R.id.neighbouName1);
        neibhourName2 = (EditText) findViewById(R.id.neighbouName2);
        address1 = (EditText) findViewById(R.id.address1);
        address2 = (EditText) findViewById(R.id.address2);
        addProof = (EditText) findViewById(R.id.addproof);
        neighbourFeedback = (Spinner)findViewById(R.id.neighbourhoodFeedback);
        vehicleSeen = (Spinner)findViewById(R.id.vehicalSeen);
        politicalLink = (Spinner)findViewById(R.id.politicalLink);
        overallStatus = (Spinner)findViewById(R.id.overallStatus);
        resonfornegativeFI = (Spinner)findViewById(R.id.reasonfornegativeFI);


        pdaNoadapter=ArrayAdapter.createFromResource(this,R.array.resstatus,R.layout.support_simple_spinner_dropdown_item);
        pdaNoadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pdaNo.setAdapter(pdaNoadapter);

        pdaNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spdaNo = pdaNo.getSelectedItem().toString();
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
                smaritalStatus = maritalStatus.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        easetoLocateadapter=ArrayAdapter.createFromResource(this,R.array.easy_locate,R.layout.support_simple_spinner_dropdown_item);
        easetoLocateadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        easetoLocate.setAdapter(easetoLocateadapter);

        typeOfLocalityadapter=ArrayAdapter.createFromResource(this,R.array.localityType,R.layout.support_simple_spinner_dropdown_item);
        typeOfLocalityadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeOfLocality.setAdapter(typeOfLocalityadapter);

        typeOfHouseadapter=ArrayAdapter.createFromResource(this,R.array.houseType,R.layout.support_simple_spinner_dropdown_item);
        typeOfHouseadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeOfHouse.setAdapter(typeOfHouseadapter);

        houseConditionadapter = ArrayAdapter.createFromResource(this,R.array.exteriors,R.layout.support_simple_spinner_dropdown_item);
        houseConditionadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        houseCondition.setAdapter(houseConditionadapter);

        ownershipadapter = ArrayAdapter.createFromResource(this,R.array.ownership,R.layout.support_simple_spinner_dropdown_item);
        ownershipadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ownership.setAdapter(ownershipadapter);

        standardOfLivingadapter = ArrayAdapter.createFromResource(this,R.array.exteriors,R.layout.support_simple_spinner_dropdown_item);
        standardOfLivingadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        standardOfLiving.setAdapter(standardOfLivingadapter);

        doesApplicantStayadapter = ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        doesApplicantStayadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        doesApplicantStay.setAdapter(doesApplicantStayadapter);

        relationshipadapter = ArrayAdapter.createFromResource(this,R.array.rel_appl,R.layout.support_simple_spinner_dropdown_item);
        relationshipadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        relationship.setAdapter(relationshipadapter);

        exterioradapter = ArrayAdapter.createFromResource(this,R.array.exteriors,R.layout.support_simple_spinner_dropdown_item);
        exterioradapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        exterior.setAdapter(exterioradapter);

        spouseEarningadapter = ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        spouseEarningadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spouseEarning.setAdapter(spouseEarningadapter);

//        maritaladapter = ArrayAdapter.createFromResource(this,R.array.transfer,R.layout.support_simple_spinner_dropdown_item);
//        maritaladapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        maritalStatus.setAdapter(maritaladapter);

        educationQualificationadapter = ArrayAdapter.createFromResource(this,R.array.educationQualification,R.layout.support_simple_spinner_dropdown_item);
        educationQualificationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        educationQualification.setAdapter(educationQualificationadapter);

        neighbourFeedbackadapter = ArrayAdapter.createFromResource(this,R.array.neighbourFeedback,R.layout.support_simple_spinner_dropdown_item);
        neighbourFeedbackadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        neighbourFeedback.setAdapter(neighbourFeedbackadapter);

        accomodationTypeadapter = ArrayAdapter.createFromResource(this,R.array.accomodationType,R.layout.support_simple_spinner_dropdown_item);
        accomodationTypeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        accomodationType.setAdapter(accomodationTypeadapter);

        vehicleSeenadapter = ArrayAdapter.createFromResource(this,R.array.vehicaldd,R.layout.support_simple_spinner_dropdown_item);
        vehicleSeenadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vehicleSeen.setAdapter(vehicleSeenadapter);

        politicalLinkadapter = ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        politicalLinkadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        politicalLink.setAdapter(politicalLinkadapter);

        overallStatusadapter = ArrayAdapter.createFromResource(this,R.array.recom_or_notDD,R.layout.support_simple_spinner_dropdown_item);
        overallStatusadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        overallStatus.setAdapter(overallStatusadapter);

        resonfornegativeFIadapter = ArrayAdapter.createFromResource(this,R.array.negative,R.layout.support_simple_spinner_dropdown_item);
        resonfornegativeFIadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        resonfornegativeFI.setAdapter(resonfornegativeFIadapter);




        easetoLocate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seasetoLocate = easetoLocate.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        typeOfLocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stypeOfLocality = typeOfLocality.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        typeOfHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stypeOfHouse = typeOfHouse.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        houseCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shouseCondition = houseCondition.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sownership = ownership.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        standardOfLiving.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sstandardOfLiving = standardOfLiving.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        doesApplicantStay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sdoesApplicantStay = doesApplicantStay.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        relationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                srelationship = relationship.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        exterior.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sexterior = exterior.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spouseEarning.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sspouseEarning = spouseEarning.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                smaritalStatus = maritalStatus.getSelectedItem().toString();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        educationQualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seducationQualification = educationQualification.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        neighbourFeedback.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sneighbourFeedback = neighbourFeedback.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        accomodationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saccomodationType = accomodationType.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        vehicleSeen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                svehicleSeen = vehicleSeen.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        politicalLink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spoliticalLink = politicalLink.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        overallStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                soverallStatus = overallStatus.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        resonfornegativeFI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sresonfornegativeFI = resonfornegativeFI.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

//        EditText caseNo,location,applicantName,address,alternateTelephone,fosName,age,landmark,stayingSince,
// personContacted,noFamilyMem,working,dependMemAdult, dependMemChild,retiredorpensioner,spouseEmp, neibhourName1,
// neibhourName2, address1, address2, addProof;


        scaseNo = caseNo.getText().toString();
        snoFamilyMem = noFamilyMem.getText().toString();
        slocation = location.getText().toString();
        sapplicantName = applicantName.getText().toString();
        saddress = address.getText().toString();
        sspouseEmp = spouseEmp.getText().toString();
        smaritalStatus = maritalStatus.getSelectedItem().toString();
        salternateTelephone = alternateTelephone.getText().toString();
        sfosName = fosName.getText().toString();
        //spoliticalInflu = politicalInflu.getText().toString();
        sage = age.getText().toString();
        slandmark = landmark.getText().toString();
        sstayingSince = stayingSince.getText().toString();
        spersonContacted = personContacted.getText().toString();
        snoFamilyMem = noFamilyMem.getText().toString();
        sworking = working.getText().toString();
        sdependMemAdult = dependMemAdult.getText().toString();
        sretiredorpensioner = retiredorpensioner.getText().toString();
        sspouseEmp = spouseEmp.getText().toString();
        sneibhourName1 = neibhourName1.getText().toString();
        sneibhourName2 = neibhourName2.getText().toString();
        saddress1 = address1.getText().toString();
        saddress2 = address2.getText().toString();
        saddProof = addProof.getText().toString();




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

//                params.put("REFNO",filestr);
//                params.put("DATEVISIT",date);
//                params.put("TIMEVISIT",time);
//                params.put("PERSONMET",sname);
//                params.put("RELATIONAPPL",srelapp);
//                params.put("PERSONPHONE",smob);
//                params.put("NOOFYEARS",snoyears);
//                params.put("DOBAPPL",sdoba);
//                params.put("EDUQUAL",seduqual);
//                params.put("RESISTATUS",sresidence);
//                params.put("MARITALSTATUS",smaritalStatus);
//                params.put("NOOFFAMILY",snoFamilyMem);
//                params.put("WORKING",sworkingMem);
//                params.put("ADULTSDEP",sdependMem);
//                params.put("CHILDDEP",schildren);
//                params.put("SPOUSEWORK",sspousework);
//                params.put("SPOUSEEMP",sspouseEmp);
//                params.put("COOPERATIVE",sclientcoop);
//                params.put("NEIGHBORHOOD",sncheck);
//                params.put("LOCALITY",slocality);
//                params.put("AMBIENCE",sambience);
//                params.put("CARPETAREA",scarpetArea);
//                params.put("NAPPLSTAY",saddlock);
//                params.put("NNOOFFAMILY",snnoff);
//                params.put("WHEELER2",stwo);
//                params.put("WHEELER4",sfour);
//                Log.d("LATT",latt);
//                params.put("LATITUDE",latt);
//                params.put("LONGITUDE",longi);
//
//                params.put("RECOMM",srecomm);
//                params.put("REMARKS",sotherRemarks);

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
