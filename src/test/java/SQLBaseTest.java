import databases.DataBaseController;
import dtos.AgentDto;
import dtos.DataBaseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;


public class SQLBaseTest extends BaseTest {
    private DataBaseController dataBaseController;

    public SQLBaseTest() {
        super();

        //todo Create a feature with test - Before and after steps using cucumber – same as in Task 3
        //todo Create a feature with test – Use background steps instead of -Before and after steps – same as in Task 3 – to setup and drop data
        //todo MySQL server version for the right syntax to use near 'INSERT INTO AGENTS VALUES ('A003', 'Alex ', 'London', '0.13', '075-12458969', ''' at line 1
    }

    private AgentDto getAgentDto(ResultSet resultSet) throws SQLException {
        AgentDto agentDto = new AgentDto();
        agentDto.setId(resultSet.getString("AGENT_CODE"));
        agentDto.setName(resultSet.getString("AGENT_NAME"));
        agentDto.setWorkingArea(resultSet.getString("WORKING_AREA"));
        agentDto.setCommission(resultSet.getString("COMMISSION"));
        agentDto.setPhone(resultSet.getString("PHONE_NO"));
        agentDto.setCountry(resultSet.getString("COUNTRY"));

        return agentDto;
    }


    @Test
    public void firstTest() {
        try {
            ResultSet resultSet = dataBaseController.selectQueryRun("SELECT * FROM MAIN.AGENTS where AGENT_NAME ='Lucida'");
            assert resultSet.getFetchSize() <= 1;
            AgentDto agentDto = getAgentDto(resultSet);
            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(agentDto.getId()).as("Agent code").isEqualTo("A012");
            softAssertions.assertThat(agentDto.getName()).as("Agent Name").isEqualTo("Lucida");
            softAssertions.assertThat(agentDto.getCommission()).as("Commission").isEqualTo("0.12");
            softAssertions.assertThat(agentDto.getPhone()).as("Phone").isEqualTo("044-52981425");
            softAssertions.assertThat(agentDto.getWorkingArea()).as("Working Arrea").isEqualTo("San Jose");
            softAssertions.assertThat(agentDto.getCountry()).as("Country").isEmpty();
            softAssertions.assertAll();

        } catch (Exception e) {
            logger.error(e.getMessage());
            assert false;
        }

    }

    @Test
    public void addRemoveData() {
        try {
            dataBaseController.updateQueryRun("delete from MAIN.AGENTS where AGENT_CODE ='A0022';");
            dataBaseController.updateQueryRun("INSERT INTO MAIN.AGENTS VALUES ('A0022', 'TEST', 'Riga', '0.15', '778-32556178', '');");
            dataBaseController.updateQueryRun("UPDATE main.agents SET PHONE_NO = '777-11111111', COUNTRY = 'Bangladesh' WHERE AGENT_NAME = 'Mukesh';");
        } catch (Exception e) {
            logger.error(e.getMessage());
            assert false;
        }
    }

    @Test
    public void testThree() {
        // firstTest();
    }

    @Before
    public void initDb() throws SQLException {
        dataBaseController = new DataBaseController();
        dataBaseController.dBConnection(getSqlConfig());
        dataBaseController.updateQueryRun("CREATE TABLE IF NOT EXISTS MAIN.`AGENTS`" +
                "   (" +
                "    `AGENT_CODE` CHAR(6) NOT NULL PRIMARY KEY, " +
                "`AGENT_NAME` CHAR(40),  " +
                "`WORKING_AREA` CHAR(35),  " +
                "`COMMISSION` DECIMAL(10,2),  " +
                "`PHONE_NO` CHAR(15), " +
                "`COUNTRY` VARCHAR(25));");
        dataBaseController.updateQueryRun("INSERT INTO AGENTS VALUES ('A007', 'Ramasundar', 'Bangalore', '0.15', '077-25814763', ''); " +
                "INSERT INTO AGENTS VALUES ('A003', 'Alex ', 'London', '0.13', '075-12458969', ''); " +
                "INSERT INTO AGENTS VALUES ('A008', 'Alford', 'New York', '0.12', '044-25874365', ''); " +
                "INSERT INTO AGENTS VALUES ('A011', 'Ravi Kumar', 'Bangalore', '0.15', '077-45625874', ''); " +
                "INSERT INTO AGENTS VALUES ('A010', 'Santakumar', 'Chennai', '0.14', '007-22388644', ''); " +
                "INSERT INTO AGENTS VALUES ('A012', 'Lucida', 'San Jose', '0.12', '044-52981425', ''); " +
                "INSERT INTO AGENTS VALUES ('A005', 'Anderson', 'Brisban', '0.13', '045-21447739', ''); " +
                "INSERT INTO AGENTS VALUES ('A001', 'Subbarao', 'Bangalore', '0.14', '077-12346674', ''); " +
                "INSERT INTO AGENTS VALUES ('A002', 'Mukesh', 'Mumbai', '0.11', '029-12358964', ''); " +
                "INSERT INTO AGENTS VALUES ('A006', 'McDen', 'London', '0.15', '078-22255588', ''); " +
                "INSERT INTO AGENTS VALUES ('A004', 'Ivan', 'Torento', '0.15', '008-22544166', ''); " +
                "INSERT INTO AGENTS VALUES ('A009', 'Benjamin', 'Hampshair', '0.11', '008-22536178', '');");
    }

    @After
    public void dropAgentsTable() throws SQLException {
        dataBaseController.simpleQueryRun("DROP TABLE IF EXISTS MAIN.AGENTS");
    }

    private DataBaseDto getSqlConfig() {
        DataBaseDto dataBaseDto = new DataBaseDto();
        dataBaseDto.setDriverName("com.mysql.cj.jdbc.Driver");
        dataBaseDto.setUsername(getProperties().getProperty("sqlUser"));
        dataBaseDto.setPassword(getProperties().getProperty("sqlPassword"));
        dataBaseDto.setUrlPath(getProperties().getProperty("mySqlUrl"));
        return dataBaseDto;
    }
}
