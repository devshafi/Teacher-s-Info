package com.jaap.teacherhinfo.db;

import com.jaap.teacherhinfo.models.Person;

import java.util.List;

import io.realm.Realm;

public class RealmService {
    private Realm realm;
    public RealmService(Realm realm){
        this.realm = realm;
    }

    //add a new teacher
    public void addNewTeacher(Person person){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(person);
            }
        });
    }

    // get a single teacher
    public Person getASingleTeacher(String id){
        Person person = realm.where(Person.class).equalTo("id",id).findFirst();
        return person;
    }

    // get all the teacher
    public List<Person> getAllTeacher(){
        List<Person> teachersList = realm.where(Person.class).findAll();
        return teachersList;
    }


    //delete an existing teacher
    public void deleteTeacher(Person person){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                person.deleteFromRealm();
            }
        });
    }

    //method for update existing teacher info
    public void updateTeacher(Person person){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(person);
            }
        });
    }

}
