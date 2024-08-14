import com.airport.model.dao.impl.UserDaoImpl;
import com.airport.model.entity.User;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.testng.AssertJUnit.*;


public class AppTest {
    private final Properties properties = new Properties();
    private static Connection connection;


    private Connection getNewConnection() throws SQLException {
        try(InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"),
                properties.getProperty("db.password"));
    }

    @Test
    public void shouldCreateNewUser() throws SQLException {
        User user = new User(3,"Evgeniy", "+375297777777", 3);

        UserDaoImpl userDao = new UserDaoImpl();

        connection = getNewConnection();

        try (Statement statement = connection.createStatement()){

            userDao.create(user, statement.getConnection());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<User> users = userDao.read("user", connection);

        int lastUserId = users.get(user.getId() - 1).getId();
        assertEquals(lastUserId, user.getId());
    }
    @Test
    public void shouldReadTableUser() throws SQLException {
        UserDaoImpl userDao = new UserDaoImpl();

        connection = getNewConnection();

        List<User> users = userDao.read("user", connection);
        assertEquals(users.get(1).getId(), 2);
    }

    @Test
    public void shouldUpdateUserPhone() throws SQLException {
        UserDaoImpl userDao = new UserDaoImpl();

        connection = getNewConnection();
        List<User> users = userDao.update(2, "+375339218888", connection);
        assertEquals(users.get(1).getPhone(), "+375339218888");
    }

    @Test
    public void shouldDeleteUser() throws SQLException {
        UserDaoImpl userDao = new UserDaoImpl();

        connection = getNewConnection();
        userDao.delete(3, connection);
    }

    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try(Connection connection = getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    @Before
    public void init() throws SQLException {
        connection = getNewConnection();
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }
}
