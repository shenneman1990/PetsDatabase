/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpetstore;

import static finalpetstore.Pet.pcount;
import static finalpetstore.Pet.petarraylist;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author samhenneman
 */
public class PetsDatabase {
    
    public String file = "pet.txt";                                             //txt file for pets
    private ArrayList<Pet> pets = new ArrayList<Pet>();
    public int size;
    
    public PetsDatabase() {
        Scanner s = new Scanner(System.in);                                     //new scanner
        this.pets = new ArrayList<>();                                          //Array list for pets
        this.size = 0;                                                          //initialize size to 0
    }
    
    public void load() {
        Scanner fileReader;
        
        try {
            fileReader = new Scanner(new File(file));                           //filereader for pet.txt
            while(fileReader.hasNextLine()) {                                   //while file has next line
                String line = fileReader.nextLine().trim();                     
                String[] data = line.split(" ");                                //space to seperate name and age
                this.pets.add(new Pet(name, age));                              //add pet to file 
            } fileReader.close();                                               //close filereader
            
            } catch (FileNotFoundException f) {                                 //catch statement for file not found
                    System.out.println("Error: Cannot find" + file + ".\n");
        }
        
    }
    
    public void showPets(){                                                     //display pets for choice 1 to user
        if(this.pets.isEmpty())
            System.out.println("There are no pets to show");
        else {
        System.out.println("+-------------------------+");
        System.out.printf("|%-3s |%-12s | %2s|","ID","NAME","AGE");             //table layout for output
        System.out.println("");
        System.out.println("+-------------------------+");
        
        for(int i = 0; i < pcount; i++) {                           
            
            System.out.printf("|%-3d |%-12s |  %2d|", i, petarraylist[i].getName(), petarraylist[i].getAge());
            System.out.println("");
            
        } 
        System.out.println("+-------------------------+");                      //finish table layout for output
        System.out.println(pcount + " pets added to the list.");                //let user know how many pets were added succesfully
    }
  }
    
    public void addNewPets() {                                                  //method for adding a pet (name and age) to the array petarraylist
        Scanner s = new Scanner(System.in);
        String line;                                                            //line used for next line in file
        String name = s.next();
        int age = s.nextInt();
        int count = 0;
        
        do {
        System.out.print("Please add your pet here (name, age):  ");
        line = s.nextLine().trim();
        if(line.equalsIgnoreCase())
            break;
        
        if(size >= 5) {                                                         //limit of 5 pets can be in database
            System.out.println("Error: No more pets can be added");
            return;
        }
        
        if(line.split("").length < 2) {                                         //statement for checking valid input 
                System.out.print("Error " + line + "is not a valid input");
                System.out.print("add pet(name,age)");                          //bring users back to choice for valid input
                line = s.nextLine().trim(); 

        }
        
        this.pets.add(new Pet(name,age));                                       //add to array list for pet
        pcount++;                                                               //add pet to array
        }
        while (true);
                System.out.println(size + "pets added.");

    }
    
    
    public void removePets() {                                                  //class for removing pets
        showPets();                                                             //showpets 
        
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Id of the pet you want to remove: ");       //user input for which pet to remove
        int Id = Integer.parseInt(s.nextLine().trim());                     
        if(Id < 0) {                                                            //if id is less than 0 display error for valid input needed
            System.out.println("Error: ID" + Id + "does not exist.");
        } else {
            String remName = this.pets.get(Id).getName();                       //get name to be removed
            int remAge = this.pets.get(Id).getAge();                            //get age to be removed
            
            this.pets.remove(Id);                                               //find pet to remove by Id
            size--;                                                             //reduce size from data
            System.out.println(remName + " " + remAge + " is removed.");        //display which pet was removed
            
        }
    }
    
    public void writeToFile() {                                                 //class for writing to file
        FileWriter f;                                                           //filewriter to f
        PrintWriter p;                                                          //printwriter to p
        
        try {
            f = new FileWriter(new File(file));                                 //write to txt file 
            p = new PrintWriter(f);
            
            for(Pet pets : this.pets) {
                p.write(pets.getName() + " " + pets.getAge());                  //add pets from input to file   
            }
        } catch (IOException ie) {
            System.out.println("Error writing to file" + file + "-");           //catch statement if can't write to file
            
        }
        
    }
    
}
