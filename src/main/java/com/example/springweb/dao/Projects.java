package com.example.springweb.dao;

import java.io.Serializable;
import java.sql.Date;

public class Projects implements Serializable {
    private String pid;
    private String uid;
    private boolean checked;
    private boolean allcheck;
    private Date applytime;
    private String companyname;
    private String appname;
    private boolean apncheck;
    private String appcatone,appcattwo,appcatthree;
    private boolean apccheck;
    private String appsafe;
    private boolean apscheck;
    private int applevel;
    private boolean aplcheck;

    public Projects(){
        pid = uid = companyname = appname = appcatone = appcattwo = appcatthree = appsafe = null;
        allcheck = apccheck = aplcheck = apncheck = apscheck =  checked = false;
        applytime = null;
        applevel = 0;
    }

    public Projects(String pid, String uid,boolean checked, boolean allcheck, Date applytime, String companyname, String appname,
                    boolean apncheck, String appcatone, String appcattwo, String appcatthree, boolean apccheck,
                    String appsafe, boolean apscheck, int applevel,boolean aplcheck){
        this.pid = pid; this.uid = uid; this.allcheck = allcheck; this.applytime = applytime; this.companyname = companyname;
        this.appname = appname; this.apncheck = apncheck; this.appcatone = appcatone; this.appcattwo = appcattwo;
        this.appcatthree = appcatthree; this.apccheck = apccheck; this.appsafe = appsafe; this.apscheck = apscheck;
        this.applevel = applevel; this.aplcheck = aplcheck;
    }

    /* ---------- get msg -----------*/
    public String getPid() {return this.pid;}
    public String getUid() {
        return uid;
    }
    public boolean isAllcheck() {
        return allcheck;
    }
    public Date getApplytime() {
        return applytime;
    }
    public String getCompanyname() {
        return companyname;
    }
    public boolean isChecked() {
        return checked;
    }

    public String getAppname() {
        return appname;
    }
    public boolean isApncheck() {
        return apncheck;
    }

    public String getAppcatone() {
        return appcatone;
    }
    public String getAppcattwo() {
        return appcattwo;
    }
    public String getAppcatthree() {
        return appcatthree;
    }
    public boolean isApccheck() {
        return apccheck;
    }

    public String getAppsafe() {
        return appsafe;
    }
    public boolean isApscheck() {
        return apscheck;
    }

    public int getApplevel() {
        return applevel;
    }
    public boolean isAplcheck() {
        return aplcheck;
    }


    /* ---------- set msg ----------*/

}
