package com.arniko.studentcrud;

public class Student {

    int id, roll;
    String name, faculty;



    public Student(int i, String string, long aLong, int anInt) {
        super();
    }

    public Student(int i, String name, int roll, String faculty) {
        super();
        this.id = i;
        this.name = name;
        this.faculty = faculty;
        this.roll = roll;

    }

    public Student(){
        super();
    }

    public Student(String name,  String faculty, int roll) {
        super();
        this.name = name;
        this.faculty = faculty;
        this.roll = roll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getRoll(){ return roll;}

    public void setName(String name) {
        this.name = name;
    }
    public void setFaculty(String name) {
        this.faculty = faculty;
    }
    public void setRoll(int roll) {
        this.roll = roll;
    }

}
