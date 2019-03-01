package com.jaap.teacherhinfo.utils;

import com.jaap.teacherhinfo.models.Person;

import java.util.ArrayList;

public class GeneratePersonList {

    public static ArrayList<Person> getPersonList(){

        Person person1 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person2 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person3 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person4 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person5 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person6 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person7 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person8 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person9 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");
        Person person10 = new Person("John Doe","Developer","Viborg,South Dakota",
                "Programming","johnDoe@example.com","01xxxxxxxxxxx");

        ArrayList<Person> personArrayList = new ArrayList<>();

        personArrayList.add(person1);
        personArrayList.add(person2);
        personArrayList.add(person3);
        personArrayList.add(person4);
        personArrayList.add(person5);
        personArrayList.add(person6);
        personArrayList.add(person7);
        personArrayList.add(person8);
        personArrayList.add(person9);
        personArrayList.add(person10);

        return personArrayList;

    }
}
