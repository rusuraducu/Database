package PetShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class SQLManager {

    private static String QUERY_INSERT_PETS = "INSERT INTO PETS (Name, Person_ID, Birthdate,  Gender, type) values (?,?,?,?, ?);";
    private static String QUERY_GET_PETS = "Select pets.* from pets, persons,pettypes where pets.Person_id=persons.ID" +
            " and pets.Type=Pettypes.id and pettypes.name=? and persons.Age<? and persons.Gender=?";

    public List<Pet> getPets(String petType, int age, char gender) {
        List<Pet> petList = new ArrayList<>();
        MyConnection pool = new MyConnection();
        Connection connection = pool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_PETS)) {
            preparedStatement.setString(1, petType);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, String.valueOf(gender));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Date birthdate = resultSet.getDate("birthdate");
                char pGender = resultSet.getString("gender").toCharArray()[0];
                int personId = resultSet.getInt("person_id");
                int type = resultSet.getInt("type");
                petList.add(new Pet(name, birthdate, pGender, type, personId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.closeConnection(connection);
        }
        return petList;
    }

    public void insertNewPet(Pet pet) {
        MyConnection pool = new MyConnection();
        Connection connection = pool.getConnection();
        System.out.println(pool.getAvailableConnections());
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_PETS)) {
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setInt(2, pet.getPersonId());
            preparedStatement.setDate(3, pet.getBirthdate());
            preparedStatement.setString(4, String.valueOf(pet.getGender()));
            preparedStatement.setInt(5, pet.getType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.closeConnection(connection);
            System.out.println(pool.getAvailableConnections());
        }
    }
}
