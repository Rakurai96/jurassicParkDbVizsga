package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        String SQL = "SELECT * FROM dinosaur WHERE actual > expected ORDER BY breed ASC";

        List<String> breeds = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String breed = resultSet.getString("breed");
                breeds.add(breed);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return breeds;
    }

}
