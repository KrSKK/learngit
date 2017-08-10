package com.zzx.protoHandlers;

import com.zzx.protoClasses.AddressBookProtos.AddressBook;
import com.zzx.protoClasses.AddressBookProtos.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
/**
 * Created by wenkai on 8/9/17.
 */
public class PersonHandler {
    public PersonHandler(){

    }

    // This function fills in a Person message based on user input.
    private Person PromptForAddress(BufferedReader stdin,
                                    PrintStream stdout) throws IOException {
        Person.Builder person = Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");
            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            Person.PhoneNumber.Builder phoneNumber =
                    Person.PhoneNumber.newBuilder().setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            if (type.equals("mobile")) {
                phoneNumber.setType(Person.PhoneType.MOBILE);
            } else if (type.equals("home")) {
                phoneNumber.setType(Person.PhoneType.HOME);
            } else if (type.equals("work")) {
                phoneNumber.setType(Person.PhoneType.WORK);
            } else {
                stdout.println("Unknown phone type.  Using default.");
            }

            person.addPhone(phoneNumber);
        }

        return person.build();
    }

    private Person RandomPerson(int id){
        Person.Builder person = Person.newBuilder();
        person.setId(id);
        person.setName(Integer.toString(id));
        person.setEmail(Integer.toString(id*123));
        return person.build();
    }

    public void MsgParser(AddressBook addressBook) {
        for (Person person: addressBook.getPersonList()) {
            System.out.println("Person ID: " + person.getId());
            System.out.println("Name: " + person.getName());
            if (person.hasEmail()) {
                System.out.println("E-mail address: " + person.getEmail());
            }

            for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
                switch (phoneNumber.getType()) {
                    case MOBILE:
                        System.out.print("Mobile phone #: ");
                        break;
                    case HOME:
                        System.out.print("Home phone #: ");
                        break;
                    case WORK:
                        System.out.print("Work phone #: ");
                        break;
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }

    public byte[] MsgBuilder(int id){
        AddressBook.Builder addressBook = AddressBook.newBuilder();
        try{
            //addressBook.addPerson(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));
            addressBook.addPerson(RandomPerson(id));
        }catch(Exception e){
            System.out.println(e);
        }
        byte[] data = addressBook.build().toByteArray();
        return data;
    }

    public void streamMaker(OutputStream output){
        AddressBook.Builder addressBook = AddressBook.newBuilder();
        try{
            addressBook.addPerson(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));
        }catch(Exception e){
            System.out.println(e);
        }
        try{
            addressBook.build().writeTo(output);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
