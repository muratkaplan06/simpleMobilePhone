package com.company;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner=new Scanner(System.in);
    private static MobilePhone mobilePhone=new MobilePhone("05444996655");
    public static void main(String[] args) {
	boolean quit=false;
    startPhone();
    printAction();
    while (!quit){
        System.out.println("\nEnter action(0-6): ");
        int action=scanner.nextInt();
        scanner.nextLine();

        switch (action){
            case 0:
                System.out.println("\nShutting down...");
                quit=false;
                break;
            case 1:
                mobilePhone.printContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                updateContact();
                break;
            case 4:
                removeContact();
                break;
            case 5:
                queryContact();
                break;
            case 6:
                printActions();
                break;
        }
    }
    }
    private static void printActions(){
        mobilePhone.printContacts();
    }

    private static void queryContact(){
        System.out.println("Enter existing contact name: ");
        String name=scanner.nextLine();
        Contact existingContactRecord=mobilePhone.queryContact(name);
        if(existingContactRecord==null)
            System.out.println("Contact not found");
        System.out.println("Name: "+existingContactRecord.getName()+" phone number is "+existingContactRecord.getPhoneNumber());
    }
    private static void removeContact(){
        System.out.println("Enter existing contact name: ");
        String name=scanner.nextLine();
        Contact existingContactRecord=mobilePhone.queryContact(name);
        if(existingContactRecord==null)
            System.out.println("Contact not found");
        if(mobilePhone.removeContact(existingContactRecord))
            System.out.println("Successfully deleted");
        else
            System.out.println("Error deleting contact");
    }
    private static void updateContact(){
        System.out.println("Enter existing contact name");
        String name=scanner.nextLine();

        Contact existingContactName= mobilePhone.queryContact(name);
        if(existingContactName==null){
            System.out.println("Contact not found");
        }
        System.out.println("Enter new contact name: ");
        String newName=scanner.nextLine();
        System.out.println("Enter new contact phone number: ");
        String newNumber=scanner.nextLine();
        Contact newContact=Contact.createContact(newName,newNumber);
        if(mobilePhone.updateContact(existingContactName,newContact))
            System.out.println("Successfully updated record");
        else
            System.out.println("Error updating record");
    }
    private static void addNewContact(){
        System.out.println("Enter new contact name: ");
        String name=NameControl(scanner.nextLine());
        System.out.println("Enter phone number: ");
        String phoneNumber=NumberControl(scanner.nextLine());
        Contact newContact=new Contact(name,phoneNumber);
        if(mobilePhone.addNewContact(newContact))
            System.out.println("New contact added: "+name+",phone "+phoneNumber);
        else
            System.out.println("Can not add "+name+" already on file");
    }
    private static void printContact(){
        mobilePhone.printContacts();
    }
    private static void startPhone(){
        System.out.println("starting phone...");
    }
    public static void printAction(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 -to shut down\n"+
                           "1 -to print contact\n"+
                           "2 -to add new contact\n"+
                           "3 -to update existing contact\n"+
                           "4 -to remove existing contact\n"+
                           "5 -to query if an existing contact\n"+
                           "6 -to print available actions");
        System.out.println("chose your action");
    }
    public static String NumberControl(String number){

        while (!Pattern.matches("[5]{1}\\d{9}",number))
            {
                System.out.println("invalid number!");
                System.out.println("Enter phone number again: ");
                number=scanner.nextLine();

            }
        return number;
    }
    public static String NameControl(String name){

        while (!Pattern.matches("\\D+",name))
        {
            System.out.println("invalid name!");
            System.out.println("Enter name again: ");
            name=scanner.nextLine();

        }
        return name;
    }
}
