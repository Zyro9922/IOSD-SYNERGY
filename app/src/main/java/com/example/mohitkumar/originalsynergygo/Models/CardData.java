package com.example.mohitkumar.originalsynergygo.Models;

/**
 * Created by mohitkumar on 11/04/17.
 */

public class CardData {
    private String name,file,address,addtype,landmark,pcontact,scontact,agentid,uniid;

    public CardData(String name, String file, String address, String addtype, String landmark, String pcontact, String scontact, String agentid, String uniid) {
        this.setAddress(address);
        this.setAddtype(addtype);
        this.setFile(file);
        this.setLandmark(landmark);
        this.setName(name);
        this.setScontact(scontact);
        this.setPcontact(pcontact);
        this.setAgentid(agentid);
        this.setUniid(uniid);
    }

    public String getUniid() {
        return uniid;
    }

    public String getAgentid() {
        return agentid;
    }

    public String getAddress() {
        return address;
    }

    public String getAddtype() {
        return addtype;
    }

    public String getFile() {
        return file;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getName() {
        return name;
    }

    public String getPcontact() {
        return pcontact;
    }

    public String getScontact() {
        return scontact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddtype(String addtype) {
        this.addtype = addtype;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPcontact(String pcontact) {
        this.pcontact = pcontact;
    }

    public void setScontact(String scontact) {
        this.scontact = scontact;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public void setUniid(String uniid) {
        this.uniid = uniid;
    }
}
