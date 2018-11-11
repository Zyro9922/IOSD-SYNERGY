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



    EditText caseNo,location,applicantName,address,alternateTelephone, dsaClientName,fosName,
            companyName,landmark,designationofApplicant,personMet,designationOfPersonMet,personContact,
            officeTelephone,dateOfJoining,noOfEmp, personcontacted, designation,
            nameOfreportManager, designationOfReportManager, contactOfReportManager, salary, tpcPersonName;

    String scaseNo,slocation,sapplicantName,saddress,salternateTelephone, sdsaClientName,sfosName,
            scompanyName,slandmark,sdesignationofApplicant,spersonMet,sdesignationOfPersonMet,spersonContact,
            sofficeTelephone,sdateOfJoining,snoOfEmp, spersoncontacted, sdesignation,
            snameOfreportManager, sdesignationOfReportManager, scontactOfReportManager, ssalary, stpcPersonName;

    Spinner pdaNo,typeOfLocality, addressConfirmed, doesapplicantWork, officeNameBoardSeen, typeOfOrganisation, visitingCardObtained,
            natureOfBusiness, jobType, workingAs, whetherJobisTransferrable, easetoLocate, tcpConfirmation, overallStatus,
            reasonForNegative;

    String spdaNo,stypeOfLocality, saddressConfirmed, sdoesapplicantWork, sofficeNameBoardSeen, stypeOfOrganisation, svisitingCardObtained,
            snatureOfBusiness, sjobType, sworkingAs, swhetherJobisTransferrable, seasetoLocate, stcpConfirmation, soverallStatus, sreasonForNegative;

    String filestr,sdetvarsal,applorcoappl;

    ProgressDialog dialog;

    Button refresh;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat,lng;
    public static final int LOCATION_REQ_CODE = 100;
    public static final int EXTERNAL_STORAGE_CODE = 101;
    LocationManager locationManager;
//    Spinner jobType,workOrg,jobTransfer,recomm;

    ArrayAdapter<CharSequence> pdaNoadapter,typeOfLocalityadapter, addressConfirmedadapter, doesapplicantWorkadapter, officeNameBoardSeenadapter, typeOfOrganisationadapter, visitingCardObtainedadapter,
            natureOfBusinessadapter, jobtypeadapter, workingAsadapter, whetherJobisTransferrableadapter, easetoLocateadapter, tcpConfirmationadapter, overallStatusadapter, reasonForNegativeadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        applorcoappl = getIntent().getStringExtra("appl_coappl");
        Log.d("TAG",applorcoappl);
        filestr = getIntent().getStringExtra("uniid");
        caseNo=(EditText)findViewById(R.id.caseNo);
        designation=(EditText)findViewById(R.id.designation);
        location=(EditText)findViewById(R.id.location);
        applicantName=(EditText)findViewById(R.id.applicantName);
        address=(EditText)findViewById(R.id.address);
        alternateTelephone=(EditText)findViewById(R.id.altTele);
        dsaClientName=(EditText)findViewById(R.id.dsaClientName);
        fosName=(EditText)findViewById(R.id.fosName);
        companyName=(EditText)findViewById(R.id.companyName);
        landmark=(EditText)findViewById(R.id.landmark);
        designationofApplicant=(EditText)findViewById(R.id.designationOfApplicant);
        personMet=(EditText)findViewById(R.id.personMet);
        designationOfPersonMet=(EditText)findViewById(R.id.desigOfPersonMet);
        personContact=(EditText)findViewById(R.id.personContactNo);
        officeTelephone=(EditText)findViewById(R.id.officeTelephone);
        dateOfJoining=(EditText)findViewById(R.id.dateOfJoining);
        noOfEmp=(EditText)findViewById(R.id.noOfEmp);
        personcontacted=(EditText)findViewById(R.id.personContacted);
        nameOfreportManager=(EditText)findViewById(R.id.nameOfReportingManager);
        designationOfReportManager=(EditText)findViewById(R.id.designOfReportingManager);
        contactOfReportManager=(EditText)findViewById(R.id.contactNoOfReportingManager);
        salary=(EditText)findViewById(R.id.salary);
        tpcPersonName=(EditText)findViewById(R.id.tpcPersonName);

        pdaNo = (Spinner) findViewById(R.id.pdaNo);
        typeOfLocality = (Spinner) findViewById(R.id.localityType);
        addressConfirmed = (Spinner) findViewById(R.id.addressConfirmed);
        doesapplicantWork = (Spinner) findViewById(R.id.doesApplicantWork);
        officeNameBoardSeen = (Spinner) findViewById(R.id.officeNameBoardSeen);
        typeOfOrganisation = (Spinner) findViewById(R.id.organisationType);
        visitingCardObtained = (Spinner) findViewById(R.id.visitingCardObtained);
        natureOfBusiness = (Spinner) findViewById(R.id.natureOfBusi);
        jobType = (Spinner) findViewById(R.id.typeOfJob);
        workingAs = (Spinner) findViewById(R.id.workingAs);
        whetherJobisTransferrable = (Spinner) findViewById(R.id.jobTransferable);
        easetoLocate = (Spinner) findViewById(R.id.easeToLocate);
        tcpConfirmation = (Spinner) findViewById(R.id.tpcConfirmation);
        overallStatus = (Spinner) findViewById(R.id.overallStatus);
        reasonForNegative = (Spinner) findViewById(R.id.reasonfornegativeFI);





        jobtypeadapter=ArrayAdapter.createFromResource(this,R.array.jobtype,R.layout.support_simple_spinner_dropdown_item);
        jobtypeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobType.setAdapter(jobtypeadapter);

        pdaNoadapter=ArrayAdapter.createFromResource(this,R.array.jobtype,R.layout.support_simple_spinner_dropdown_item);
        pdaNoadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pdaNo.setAdapter(pdaNoadapter);

        typeOfLocalityadapter=ArrayAdapter.createFromResource(this,R.array.typeOfLocalityDD,R.layout.support_simple_spinner_dropdown_item);
        typeOfLocalityadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeOfLocality.setAdapter(typeOfLocalityadapter);

        addressConfirmedadapter=ArrayAdapter.createFromResource(this,R.array.transfer,R.layout.support_simple_spinner_dropdown_item);
        addressConfirmedadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        addressConfirmed.setAdapter(addressConfirmedadapter);

        doesapplicantWorkadapter=ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        doesapplicantWorkadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        doesapplicantWork.setAdapter(doesapplicantWorkadapter);

        officeNameBoardSeenadapter=ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        officeNameBoardSeenadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        officeNameBoardSeen.setAdapter(officeNameBoardSeenadapter);

        typeOfOrganisationadapter=ArrayAdapter.createFromResource(this,R.array.typeofOrganisationDD,R.layout.support_simple_spinner_dropdown_item);
        typeOfOrganisationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeOfOrganisation.setAdapter(typeOfOrganisationadapter);

        visitingCardObtainedadapter=ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        visitingCardObtainedadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        visitingCardObtained.setAdapter(visitingCardObtainedadapter);

        natureOfBusinessadapter=ArrayAdapter.createFromResource(this,R.array.natureOfBusi,R.layout.support_simple_spinner_dropdown_item);
        natureOfBusinessadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        natureOfBusiness.setAdapter(natureOfBusinessadapter);

        workingAsadapter=ArrayAdapter.createFromResource(this,R.array.workingAs,R.layout.support_simple_spinner_dropdown_item);
        workingAsadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        workingAs.setAdapter(workingAsadapter);

        whetherJobisTransferrableadapter=ArrayAdapter.createFromResource(this,R.array.yesNo,R.layout.support_simple_spinner_dropdown_item);
        whetherJobisTransferrableadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        whetherJobisTransferrable.setAdapter(whetherJobisTransferrableadapter);

        easetoLocateadapter=ArrayAdapter.createFromResource(this,R.array.easy_locateDD,R.layout.support_simple_spinner_dropdown_item);
        easetoLocateadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        easetoLocate.setAdapter(easetoLocateadapter);

        tcpConfirmationadapter=ArrayAdapter.createFromResource(this,R.array.positiveNegativeDD,R.layout.support_simple_spinner_dropdown_item);
        tcpConfirmationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        tcpConfirmation.setAdapter(tcpConfirmationadapter);

        overallStatusadapter=ArrayAdapter.createFromResource(this,R.array.recom_or_notDD,R.layout.support_simple_spinner_dropdown_item);
        overallStatusadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        overallStatus.setAdapter(overallStatusadapter);

        reasonForNegativeadapter=ArrayAdapter.createFromResource(this,R.array.reasonForNegative,R.layout.support_simple_spinner_dropdown_item);
        reasonForNegativeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        reasonForNegative.setAdapter(reasonForNegativeadapter);


        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sjobType = jobType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sjobType = "";
            }
        });

        pdaNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spdaNo = pdaNo.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spdaNo = "";
            }
        });

        typeOfLocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stypeOfLocality = typeOfLocality.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stypeOfLocality = "";
            }
        });

        addressConfirmed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saddressConfirmed = addressConfirmed.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                saddressConfirmed = "";
            }
        });

        doesapplicantWork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sdoesapplicantWork = doesapplicantWork.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sdoesapplicantWork = "";
            }
        });

        officeNameBoardSeen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sofficeNameBoardSeen = officeNameBoardSeen.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sofficeNameBoardSeen = "";
            }
        });

        typeOfOrganisation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stypeOfOrganisation = typeOfOrganisation.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stypeOfOrganisation = "";
            }
        });

        visitingCardObtained.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                svisitingCardObtained= visitingCardObtained.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                svisitingCardObtained = "";
            }
        });

        natureOfBusiness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                snatureOfBusiness = natureOfBusiness.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                snatureOfBusiness = "";
            }
        });

        workingAs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sworkingAs = workingAs.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sworkingAs = "";
            }
        });

        whetherJobisTransferrable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                swhetherJobisTransferrable = whetherJobisTransferrable.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                swhetherJobisTransferrable = "";
            }
        });

        easetoLocate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seasetoLocate = easetoLocate.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                seasetoLocate = "";
            }
        });

        tcpConfirmation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stcpConfirmation = tcpConfirmation.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stcpConfirmation = "";
            }
        });

        overallStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                soverallStatus = overallStatus.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                soverallStatus = "";
            }
        });

        reasonForNegative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sreasonForNegative = reasonForNegative.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sreasonForNegative = "";
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

        scaseNo = caseNo.getText().toString();
        slocation = caseNo.getText().toString();
        sapplicantName = caseNo.getText().toString();
        saddress = caseNo.getText().toString();
        salternateTelephone = caseNo.getText().toString();
        sdsaClientName = caseNo.getText().toString();
        sfosName = caseNo.getText().toString();
        scompanyName = caseNo.getText().toString();
        slandmark = caseNo.getText().toString();
        sdesignationofApplicant = caseNo.getText().toString();
        spersonMet = caseNo.getText().toString();
        sdesignationOfPersonMet = caseNo.getText().toString();
        spersonContact = caseNo.getText().toString();
        sofficeTelephone = caseNo.getText().toString();
        sdateOfJoining = caseNo.getText().toString();
        snoOfEmp = caseNo.getText().toString();
        spersoncontacted = caseNo.getText().toString();
        sdesignation = caseNo.getText().toString();
        snameOfreportManager = caseNo.getText().toString();
        sdesignationOfReportManager = caseNo.getText().toString();
        scontactOfReportManager = caseNo.getText().toString();
        ssalary = caseNo.getText().toString();
        stpcPersonName = caseNo.getText().toString();


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

                 params.put("CASENO",scaseNo);
                params.put("LOCATION",slocation);
                params.put("NAME",sapplicantName);
                params.put("ADDRESS",saddress);
                params.put("ALTTEL",salternateTelephone);
                params.put("CLEINTNAME",sdsaClientName);
                params.put("FOSNAME",sfosName);
                params.put("COMPANTYNAME",scompanyName);
                params.put("LANDMARK",slandmark);
                params.put("DESIGN",sdesignationofApplicant);
                params.put("PERSONMET",spersonMet);
                params.put("DESIGNOFPERMET",sdesignationOfPersonMet);
                params.put("PERSONCONTACT",spersonContact);
                params.put("OFFICETELE",sofficeTelephone);
                params.put("DATEOFJOINING",sdateOfJoining);
                params.put("NOOFEMP",snoOfEmp);
                params.put("PERSONCONTACTED",spersoncontacted);
                params.put("DESIGNATION",sdesignation);
                params.put("NAMEOFREPMANAGER",snameOfreportManager);
                params.put("DESIGOFREPMANAGER",sdesignationOfReportManager);
                params.put("CONTOFREPMANAGER",scontactOfReportManager);
                params.put("SALARY",ssalary);
                params.put("STPCPERSONNAME",stpcPersonName);

                params.put("PDANO",spdaNo);
                params.put("TYPEOFLOC",stypeOfLocality);
                params.put("ADDRESSCONF",saddressConfirmed);
                params.put("APPICATIONWORK",sdoesapplicantWork);
                params.put("OFFICENAMEBOARD",sofficeNameBoardSeen);
                params.put("TYPEOFORG",stypeOfOrganisation);
                params.put("VISITINGCARD",svisitingCardObtained);
                params.put("NATUREOFBUSI",snatureOfBusiness);
                params.put("JOBTYPE",sjobType);
                params.put("WORKINGAS",sworkingAs);
                params.put("JOBTRANS",swhetherJobisTransferrable);
                params.put("EASETOLOC",seasetoLocate);
                params.put("STCPCONFIRM",stcpConfirmation);
                params.put("OVERALLSTAT",soverallStatus);
                params.put("REASONFORNEG",sreasonForNegative);

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
