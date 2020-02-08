package databases;

import dtos.DataBaseDto;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public class DataBaseController implements DbConnector {

    private Connection connection;
    private Statement statement;
    private DataBaseDto dataBaseDto;

    public void dBConnection(DataBaseDto dataBaseDto) {
        this.dataBaseDto = dataBaseDto;
        try {
            Class.forName(dataBaseDto.getDriverName());
        } catch (ClassNotFoundException e) {
            //logger.error(e.getMessage());
        }
        connect();
    }

    public void simpleQueryRun(String query) throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void updateQueryRun(String query) throws SQLException {
        connect();
        statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        connection.close();
    }

    public ResultSet selectQueryRun(String query) throws SQLException {
        connect();
        statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public void simpleParameterValidation(int index, String validateValue) {

    }

    public void countSimpleValidation(int countAmount) {

    }

    private boolean connect() {
        if (dataBaseDto == null) {
            return false;
        }
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dataBaseDto.getUrlPath(),
                        dataBaseDto.getUsername(),
                        dataBaseDto.getPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(dataBaseDto.getUrlPath(),
                            dataBaseDto.getUsername(),
                            dataBaseDto.getPassword());
                    return true;
                } else {
                    connection.close();
                    connection = DriverManager.getConnection(dataBaseDto.getUrlPath(),
                            dataBaseDto.getUsername(),
                            dataBaseDto.getPassword());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }

    public void readInitScript() throws FileNotFoundException {
        connect();
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader("init.sql"));
        sr.runScript(reader);
    }
}
