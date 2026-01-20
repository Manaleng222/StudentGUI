/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentgui;

/**
 *
 * @author USER
 */
public class Student {
    
     private final int id;
    private final String name;
    private final String surname;
    private final int age;
    private final String email;
    private final String course;
    
    
    Student(int id,String name,String surname,int age,String email,String course){
        this.id = id;
        this.name= name;
        this.surname =surname;
        this.age = age;
        this.email = email;
        this.course= course;
    }

   

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public int getAge(){
        return age;
    }
    public String getEmail(){
        return email;
    }
    public String getCourse(){
        return course;
    }
    @Override
    public String toString(){
        return "id: " +id+" ,name: " +name+", Surname: "+surname+", Age:  "+age+" ,Email"+email+",Course: "+ course;
    }
}
