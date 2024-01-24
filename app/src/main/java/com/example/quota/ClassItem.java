package com.example.quota;


public class ClassItem {
    private long cid;
    private int roll;

    public ClassItem(long cid, String sectionName, String gradeLevel) {
        this.cid = cid;
        this.sectionName = sectionName;
        this.gradeLevel = gradeLevel;
    }

    private String sectionName;
    private String gradeLevel;
    private String status;

    public ClassItem (String section, String grade) {
        this.sectionName = section;
        this.gradeLevel = grade ;
    }

    public String getSection() { return sectionName; }

    public String getStatus() {return status;}

    public String getGrade() { return this.gradeLevel;  }

    public void setSectionName(String sectionName){
        this.sectionName = sectionName;
    }
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }


    public long getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRoll(){ this.roll = roll;
        return roll;
    }
    }

