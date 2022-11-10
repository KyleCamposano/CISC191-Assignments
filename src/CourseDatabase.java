import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Loads JDBC driver, connects to a database, creates statements,
 * executes statements, iterates through the result, prints the
 * table, and then closes the connection.
 */

public class CourseDatabase {
    public static void main(String [] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded.");

        String dbUrl="jdbc:mysql://localhost:3306/mySQL";
        //String driver = "com.mysql.cj.jdbc.Driver";
        String username = "test";
        String password = "Pa$$word";
        Connection connection = DriverManager.getConnection(dbUrl,username,password);
        System.out.println("Database connected.");

        Statement statement = connection.createStatement();

        // comment if table is already created.
        statement.execute("create table Course(courseID int, subjectID char(4), courseNumber int, title text, numOfCredits int)");

        System.out.println("Table created.");

        /*
            .execute(), .executeQuery(), .executeUpdate() runs different SQL statements
            refer to https://learn.microsoft.com/en-us/sql/connect/jdbc/reference/execute-method-sqlserverstatement
         */

        // Course is red because there is no table yet. Creation of the table is part of the program.
        statement.executeUpdate("insert into Course(courseID, subjectID, courseNumber, title, numOfCredits) " +
                "values ('0001', 'CISC', '191', 'Database', '4')");

        System.out.println("Values assigned.\n");

        statement.executeQuery("show tables");

        //  use sql console to easily access tables with columns & rows.
        //  same behavior : statement.executeQuery("show columns from Course");

        System.out.println("Contents of table Course:");

        ResultSet resultSet2 = statement.executeQuery("select courseID, subjectID, courseNumber, title, numofcredits from Course");

        while (resultSet2.next()) {
            System.out.print(resultSet2.getString(1) + "  " + resultSet2.getString(2) + "  "
                    + resultSet2.getString(3) + "  " + resultSet2.getString(4) + "  "
                    + resultSet2.getString(5) + "\n");
        }
        // comment to test (check db tables content)
        statement.executeUpdate("delete from Course");

        // comment to keep table.
        statement.executeUpdate("drop table Course");

        connection.close();
    }
}