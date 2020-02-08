package databases;

import dtos.DataBaseDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbConnector {
    public void dBConnection(DataBaseDto dataBaseDto);
    public void simpleQueryRun(String query) throws SQLException;
    public void updateQueryRun(String query) throws SQLException;
    public ResultSet selectQueryRun(String query) throws SQLException;
    public void simpleParameterValidation(int index, String validateValue);
    public void countSimpleValidation(int countAmount);

}
