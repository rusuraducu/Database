package PetShop;

import com.itextpdf.text.pdf.PdfWriter;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Pet pet = new Pet("NewPet", new Date(timestamp.getTime()), 'M', 6, 1);
        SQLManager sqlManager = new SQLManager();
        sqlManager.insertNewPet(pet);
        List<Pet> pets = sqlManager.getPets("caine", 30, 'M');
        for(Pet p:pets){
            System.out.println(p);
        }
    }

//    a. inserting new pets (INSERT INTO PETS (Name, Person_ID, Birtdate, Age, Gender)
//    values (?,?,?,?,?);
//    b. inserting new persons (INSERT INTO PERSONS (First_Name, Last_Name, Age,
//                              Gender) values (?,?,?,?);
//    c. assigning pets to persons (Update PETS set Person_ID=? where ID=?;)



}
