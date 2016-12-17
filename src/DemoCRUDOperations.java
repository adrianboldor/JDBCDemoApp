import java.sql.*;



/**
 * Created by condor on 26/02/15.
 * FastTrackIT, 2015
 * <p/>
 * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
 *
 * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
 * repeating the same common code multiple times
 *
 * BTW, exercise 1: how we reorg this/refactor in small pieces
 */
public class DemoCRUDOperations {


    public static void main(String[] args) {
        System.out.println("Hello database users! We are going to call DB from Java");
        try {
            //demo CRUD operations
            //demoCreate();
            demoRead();
            //demoUpdate();
            //demoDelete();

           // demoBlobInsert();
           // demoBlobRead();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void demoCreate() throws ClassNotFoundException, SQLException {

        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/5adrian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO USERS (NAME, PASSWORD, AGE) VALUES (?,?,?)");
        pSt.setString(1, "ionel");
        pSt.setString(2, "password1");
        pSt.setInt(3,42);

        // 5. execute a prepared statement
        //int rowsInserted = pSt.executeUpdate();

        pSt = conn.prepareStatement("INSERT INTO agenda (fname,lname,phone,mark,gender,country,fk_user) VALUES (?,?,?,?,?,?,?)");
        pSt.setString(1, "ionel");
        pSt.setString(2, "condor");
        pSt.setString(3, "414145141421");
        pSt.setInt(4, 2);
        pSt.setString(5, "m");
        pSt.setString(6, "Romania");
        pSt.setInt(7,3);

        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    private static void demoRead() throws ClassNotFoundException, SQLException {
               // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/5adrian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT name,password,age FROM users");

        // 6. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("name").trim());
            System.out.print("---");
            System.out.print(rs.getString("password").trim());
            System.out.print("---");
            System.out.println(rs.getInt("age"));
        }



        //SkeletonJava.readStringConsole("For what user to display the agenda?:");

        String query = "SELECT users.name as n, agenda.fname as nn, agenda.phone from users,agenda " +
                "where users.NAME like 'Doina' and " +
                "users.id=agenda.fk_user";

        rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.print(rs.getString("nn").trim());
            System.out.print("---");
            //System.out.print(rs.getString("lname").trim());
            //System.out.print("---");
            System.out.println(rs.getString("phone"));
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }

    private static void demoUpdate() throws ClassNotFoundException, SQLException {

                // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/5adrian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE USERS SET NAME=? WHERE NAME=?"); //so we have 3 params
        pSt.setString(1, "dorel");
        //pSt.setString(2, "password1");
        pSt.setString(2, "ionel");

        // 5. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }


    private static void demoDelete() throws ClassNotFoundException, SQLException {

               // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/5adrian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM USERS WHERE PK_USER=?");
        pSt.setLong(1, 1);

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 6. close the objects
        pSt.close();
        conn.close();
    }
}

