package PetShop;

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
}
