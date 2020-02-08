
import lombok.Getter;
import org.junit.Test;

import java.sql.*;


public class SQLBaseTest extends BaseTest {
    String user;
    String password;
    String url;

    public SQLBaseTest(){
        user = getProperties().getProperty("sqlUser");
        password = getProperties().getProperty("sqlPassword");
        url = getProperties().getProperty("mySqlUrl");
    }


    @Test
    public void firstest() {
        try {
            connectDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void addRemoveData() {
        Connection con;
        Statement stmt;
        String sql;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            sql = "delete from MAIN.AGENTS where AGENT_CODE ='A0022';";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO MAIN.AGENTS VALUES ('A0022', 'TEST', 'Riga', '0.15', '778-32556178', '');";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();


            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            sql = "UPDATE main.agents " +
                    "SET " +
                    "    PHONE_NO = '777-11111111', " +
                    "    COUNTRY = 'Bangladesh' " +
                    "WHERE" +
                    "    AGENT_NAME = 'Mukesh';";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();


        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void connectDb() throws SQLException {

        Connection con = DriverManager.getConnection(url, user, password);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("CREATE TABLE `AGENTS`\n" +
                "   (\n" +
                "    `AGENT_CODE` CHAR(6) NOT NULL PRIMARY KEY,\n" +
                "\t`AGENT_NAME` CHAR(40),\n" +
                "\t`WORKING_AREA` CHAR(35),\n" +
                "\t`COMMISSION` DECIMAL(10,2),\n" +
                "\t`PHONE_NO` CHAR(15),\n" +
                "\t`COUNTRY` VARCHAR(25)\n" +
                "\t );\n" +
                "\n" +
                "INSERT INTO AGENTS VALUES ('A007', 'Ramasundar', 'Bangalore', '0.15', '077-25814763', '');\n" +
                "INSERT INTO AGENTS VALUES ('A003', 'Alex ', 'London', '0.13', '075-12458969', '');\n" +
                "INSERT INTO AGENTS VALUES ('A008', 'Alford', 'New York', '0.12', '044-25874365', '');\n" +
                "INSERT INTO AGENTS VALUES ('A011', 'Ravi Kumar', 'Bangalore', '0.15', '077-45625874', '');\n" +
                "INSERT INTO AGENTS VALUES ('A010', 'Santakumar', 'Chennai', '0.14', '007-22388644', '');\n" +
                "INSERT INTO AGENTS VALUES ('A012', 'Lucida', 'San Jose', '0.12', '044-52981425', '');\n" +
                "INSERT INTO AGENTS VALUES ('A005', 'Anderson', 'Brisban', '0.13', '045-21447739', '');\n" +
                "INSERT INTO AGENTS VALUES ('A001', 'Subbarao', 'Bangalore', '0.14', '077-12346674', '');\n" +
                "INSERT INTO AGENTS VALUES ('A002', 'Mukesh', 'Mumbai', '0.11', '029-12358964', '');\n" +
                "INSERT INTO AGENTS VALUES ('A006', 'McDen', 'London', '0.15', '078-22255588', '');\n" +
                "INSERT INTO AGENTS VALUES ('A004', 'Ivan', 'Torento', '0.15', '008-22544166', '');\n" +
                "INSERT INTO AGENTS VALUES ('A009', 'Benjamin', 'Hampshair', '0.11', '008-22536178', '');\n" +
                "\n" +
                "CREATE TABLE  `CUSTOMER`\n" +
                "   (\t`CUST_CODE` VARCHAR(6) NOT NULL PRIMARY KEY,\n" +
                "\t`CUST_NAME` VARCHAR(40) NOT NULL,\n" +
                "\t`CUST_CITY` CHAR(35),\n" +
                "\t`WORKING_AREA` VARCHAR(35) NOT NULL,\n" +
                "\t`CUST_COUNTRY` VARCHAR(20) NOT NULL,\n" +
                "\t`GRADE` DOUBLE,\n" +
                "\t`OPENING_AMT` DECIMAL(12,2) NOT NULL,\n" +
                "\t`RECEIVE_AMT` DECIMAL(12,2) NOT NULL,\n" +
                "\t`PAYMENT_AMT` DECIMAL(12,2) NOT NULL,\n" +
                "\t`OUTSTANDING_AMT` DECIMAL(12,2) NOT NULL,\n" +
                "\t`PHONE_NO` VARCHAR(17) NOT NULL,\n" +
                "\t`AGENT_CODE` CHAR(6) NOT NULL REFERENCES AGENTS\n" +
                ");\n" +
                "\n" +
                "INSERT INTO CUSTOMER VALUES ('C00013', 'Holmes', 'London', 'London', 'UK', '2', '6000.00', '5000.00', '7000.00', '4000.00', 'BBBBBBB', 'A003');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00001', 'Micheal', 'New York', 'New York', 'USA', '2', '3000.00', '5000.00', '2000.00', '6000.00', 'CCCCCCC', 'A008');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00020', 'Albert', 'New York', 'New York', 'USA', '3', '5000.00', '7000.00', '6000.00', '6000.00', 'BBBBSBB', 'A008');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00025', 'Ravindran', 'Bangalore', 'Bangalore', 'India', '2', '5000.00', '7000.00', '4000.00', '8000.00', 'AVAVAVA', 'A011');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00024', 'Cook', 'London', 'London', 'UK', '2', '4000.00', '9000.00', '7000.00', '6000.00', 'FSDDSDF', 'A006');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00015', 'Stuart', 'London', 'London', 'UK', '1', '6000.00', '8000.00', '3000.00', '11000.00', 'GFSGERS', 'A003');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00002', 'Bolt', 'New York', 'New York', 'USA', '3', '5000.00', '7000.00', '9000.00', '3000.00', 'DDNRDRH', 'A008');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00018', 'Fleming', 'Brisban', 'Brisban', 'Australia', '2', '7000.00', '7000.00', '9000.00', '5000.00', 'NHBGVFC', 'A005');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00021', 'Jacks', 'Brisban', 'Brisban', 'Australia', '1', '7000.00', '7000.00', '7000.00', '7000.00', 'WERTGDF', 'A005');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00019', 'Yearannaidu', 'Chennai', 'Chennai', 'India', '1', '8000.00', '7000.00', '7000.00', '8000.00', 'ZZZZBFV', 'A010');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00005', 'Sasikant', 'Mumbai', 'Mumbai', 'India', '1', '7000.00', '11000.00', '7000.00', '11000.00', '147-25896312', 'A002');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00007', 'Ramanathan', 'Chennai', 'Chennai', 'India', '1', '7000.00', '11000.00', '9000.00', '9000.00', 'GHRDWSD', 'A010');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00022', 'Avinash', 'Mumbai', 'Mumbai', 'India', '2', '7000.00', '11000.00', '9000.00', '9000.00', '113-12345678','A002');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00004', 'Winston', 'Brisban', 'Brisban', 'Australia', '1', '5000.00', '8000.00', '7000.00', '6000.00', 'AAAAAAA', 'A005');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00023', 'Karl', 'London', 'London', 'UK', '0', '4000.00', '6000.00', '7000.00', '3000.00', 'AAAABAA', 'A006');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00006', 'Shilton', 'Torento', 'Torento', 'Canada', '1', '10000.00', '7000.00', '6000.00', '11000.00', 'DDDDDDD', 'A004');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00010', 'Charles', 'Hampshair', 'Hampshair', 'UK', '3', '6000.00', '4000.00', '5000.00', '5000.00', 'MMMMMMM', 'A009');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00017', 'Srinivas', 'Bangalore', 'Bangalore', 'India', '2', '8000.00', '4000.00', '3000.00', '9000.00', 'AAAAAAB', 'A007');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00012', 'Steven', 'San Jose', 'San Jose', 'USA', '1', '5000.00', '7000.00', '9000.00', '3000.00', 'KRFYGJK', 'A012');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00008', 'Karolina', 'Torento', 'Torento', 'Canada', '1', '7000.00', '7000.00', '9000.00', '5000.00', 'HJKORED', 'A004');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00003', 'Martin', 'Torento', 'Torento', 'Canada', '2', '8000.00', '7000.00', '7000.00', '8000.00', 'MJYURFD', 'A004');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00009', 'Ramesh', 'Mumbai', 'Mumbai', 'India', '3', '8000.00', '7000.00', '3000.00', '12000.00', 'Phone No', 'A002');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00014', 'Rangarappa', 'Bangalore', 'Bangalore', 'India', '2', '8000.00', '11000.00', '7000.00', '12000.00', 'AAAATGF', 'A001');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00016', 'Venkatpati', 'Bangalore', 'Bangalore', 'India', '2', '8000.00', '11000.00', '7000.00', '12000.00', 'JRTVFDD', 'A007');\n" +
                "INSERT INTO CUSTOMER VALUES ('C00011', 'Sundariya', 'Chennai', 'Chennai', 'India', '3', '7000.00', '11000.00', '7000.00', '11000.00', 'PPHGRTS', 'A010');\n" +
                "\n" +
                "CREATE TABLE  `ORDERS`\n" +
                "   (\n" +
                "        `ORD_NUM` INT NOT NULL PRIMARY KEY,\n" +
                "\t`ORD_AMOUNT` DECIMAL(12,2) NOT NULL,\n" +
                "\t`ADVANCE_AMOUNT` DECIMAL(12,2) NOT NULL,\n" +
                "\t`ORD_DATE` VARCHAR(60) NOT NULL,\n" +
                "\t`CUST_CODE` VARCHAR(6) NOT NULL REFERENCES CUSTOMER,\n" +
                "\t`AGENT_CODE` CHAR(6) NOT NULL REFERENCES AGENTS,\n" +
                "\t`ORD_DESCRIPTION` VARCHAR(60) NOT NULL\n" +
                "   );\n" +
                "\n" +
                "INSERT INTO ORDERS VALUES('200100', '1000.00', '600.00', '08/01/2008', 'C00013', 'A003', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200110', '3000.00', '500.00', '04/15/2008', 'C00019', 'A010', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200107', '4500.00', '900.00', '08/30/2008', 'C00007', 'A010', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200112', '2000.00', '400.00', '05/30/2008', 'C00016', 'A007', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200113', '4000.00', '600.00', '06/10/2008', 'C00022', 'A002', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200102', '2000.00', '300.00', '05/25/2008', 'C00012', 'A012', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200114', '3500.00', '2000.00', '08/15/2008', 'C00002', 'A008', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200122', '2500.00', '400.00', '09/16/2008', 'C00003', 'A004', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200118', '500.00', '100.00', '07/20/2008', 'C00023', 'A006', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200119', '4000.00', '700.00', '09/16/2008', 'C00007', 'A010', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200121', '1500.00', '600.00', '09/23/2008', 'C00008', 'A004', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200130', '2500.00', '400.00', '07/30/2008', 'C00025', 'A011', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200134', '4200.00', '1800.00', '09/25/2008', 'C00004', 'A005', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200108', '4000.00', '600.00', '02/15/2008', 'C00008', 'A004', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200103', '1500.00', '700.00', '05/15/2008', 'C00021', 'A005', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200105', '2500.00', '500.00', '07/18/2008', 'C00025', 'A011', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200109', '3500.00', '800.00', '07/30/2008', 'C00011', 'A010', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200101', '3000.00', '1000.00', '07/15/2008', 'C00001', 'A008', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200111', '1000.00', '300.00', '07/10/2008', 'C00020', 'A008', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200104', '1500.00', '500.00', '03/13/2008', 'C00006', 'A004', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200106', '2500.00', '700.00', '04/20/2008', 'C00005', 'A002', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200125', '2000.00', '600.00', '10/10/2008', 'C00018', 'A005', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200117', '800.00', '200.00', '10/20/2008', 'C00014', 'A001', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200123', '500.00', '100.00', '09/16/2008', 'C00022', 'A002', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200120', '500.00', '100.00', '07/20/2008', 'C00009', 'A002', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200116', '500.00', '100.00', '07/13/2008', 'C00010', 'A009', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200124', '500.00', '100.00', '06/20/2008', 'C00017', 'A007', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200126', '500.00', '100.00', '06/24/2008', 'C00022', 'A002', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200129', '2500.00', '500.00', '07/20/2008', 'C00024', 'A006', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200127', '2500.00', '400.00', '07/20/2008', 'C00015', 'A003', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200128', '3500.00', '1500.00', '07/20/2008', 'C00009', 'A002', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200135', '2000.00', '800.00', '09/16/2008', 'C00007', 'A010', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200131', '900.00', '150.00', '08/26/2008', 'C00012', 'A012', 'SOD');\n" +
                "INSERT INTO ORDERS VALUES('200133', '1200.00', '400.00', '06/29/2008', 'C00009', 'A002', 'SOD');");
        System.out.println(resultSet);
        con.close();
    }
}
