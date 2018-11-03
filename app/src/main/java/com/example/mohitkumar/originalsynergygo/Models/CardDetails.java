package com.example.mohitkumar.originalsynergygo.Models;

/**
 * Created by mohitkumar on 20/04/17.
 */

public class CardDetails {

    public String refno,dor,name,address,mobile,fos,applorco,type;

    public CardDetails(String refno,String dor,String name,String address,String mobile,String fos,String applorco,String type) {
        this.setAddress(address);
        this.setApplorco(applorco);
        this.setDor(dor);
        this.setFos(fos);
        this.setRefno(refno);
        this.setName(name);
        this.setType(type);
        this.setMobile(mobile);
    }

    public String getAddress() {
        return address;
    }

    public String getApplorco() {
        return applorco;
    }

    public String getDor() {
        return dor;
    }

    public String getFos() {
        return fos;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getRefno() {
        return refno;
    }

    public String getType() {
        return type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApplorco(String applorco) {
        this.applorco = applorco;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public void setFos(String fos) {
        this.fos = fos;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public void setType(String type) {
        this.type = type;
    }
}
