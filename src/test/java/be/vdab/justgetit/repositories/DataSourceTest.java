package be.vdab.justgetit.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
<<<<<<< HEAD
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
=======
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
<<<<<<< HEAD
@DataJpaTest
=======
@JdbcTest
>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;
<<<<<<< HEAD

    @Test
    public void getConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
=======
    @Test
    public void getConnection()throws SQLException {
        try(Connection connection = dataSource.getConnection()){

>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
        }
    }
}
