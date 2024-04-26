package peaksoft.dao;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public void createUsersTable() {
        String query = """
                create table users(
                id serial primary key,
                first_name varchar,
                last_name varchar,
                age smallint
                )
                """;

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Table " + query + " success added!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    public void dropUsersTable() {
        String query = """
                drop table users
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.execute();
            System.out.println("Table " + query + " success deleted!" );
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {


        String query = """
                insert into users(first_name,last_name,age)
                values(?,?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " success added!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void removeUserById(long id) {
        String query = """
                delete from users where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Deleted it is ID: " + id + " success!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = """
                select * from users u
                """;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Byte age = resultSet.getByte("age");
                User user = new User(id,name,lastName,age);
                list.add(user);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        String query = """
                delete from users
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Tables students success cleaned!" );
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}