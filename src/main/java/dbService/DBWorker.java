package dbService;

import com.example.model.AccountUser;

import java.sql.*;
import java.util.Properties;

public class DBWorker {
    private final Connection connection;

    public DBWorker() {
        connection = getPostgressConnection();
    }

    public AccountUser getUser(String login){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login=?");
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();

            if (!result.next()) {
                return null;
            }

            return new AccountUser(result.getString(1), result.getString(2), result.getString(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addUser(AccountUser user) {
        try{
            String sql = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection getPostgressConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Users";
            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "1315");
            return DriverManager.getConnection(url, authorization);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return  null;
    }
}
