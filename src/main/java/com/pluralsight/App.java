package com.pluralsight;

import java.sql.*;

public class App {

    public static void main(String[] args) {

        //did we pass in a username and password
        //if not, the application must die
        if (args.length != 2) {
            //display a message to the user
            System.out.println("Application needs two args to run: A username and a password for the db");
            //exit the app due to failure because we dont have a username and password from the command line
            System.exit(1);
        }

        //get the username and password from args[]
        String username = args[0];
        String password = args[1];

        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password)) {

            while (true) {

                System.out.println("""
                         What do you want to do?
                             1) Display All Products
                             2) Display All Customers
                             0) Exit App                       \s
                        \s""");

                switch (scan.nextInt()) {
                    case (1):
                        displayAllProducts(c);
                        break;
                    case (2):
                        displayAllCustomers(c);
                        break;
                    case (0):
                        System.out.println("Bye");
                        System.exit(0);
                    default:
                        System.out.println("Try Again");
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong" + e);
        }


//
//        //try(
//                //Create the connection(similar to opening mySQL workbench)
//                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
//
//                PreparedStatement q = c.prepareStatement("""
//
//                    SELECT
//                        ProductName
//                    FROM
//                        Products
//
//                    """);
//                ResultSet results = q.executeQuery();
//        ){
//            printResults(results);
//
//        }catch(SQLException e){
//            System.out.println("Something went wrong" + e);
//        }
//    }

        //This method will be used inside of the display methods to print results to the screen
        public static void printResults (ResultSet r) throws SQLException {

            //Get the meta data so we have access to the field names
            ResultSetMetaData metaData = r.getMetaData();

            //Get the number of rows returned
            int columnCount = metaData.getColumnCount();

            while (r.next()) {
                //loop over each column in the row and display the data
                int productID = r.getInt("ProductID");
                String productName = r.getString("ProductName");
                double unitPrice = r.getDouble("UnitPrice");
                int unitsInStock = r.getInt("UnitsInStock");

                System.out.printf("%-3d | %-35s | %-8.2f | %-10d", productID, productName, unitPrice, unitsInStock);
//print empty line to make results display prettier
                System.out.println();

                //print empty line to make results display prettier
                System.out.println();
            }
        }
    }
}

//package com.pluralsight;
//
//import java.awt.*;
//import java.util.Scanner;
//import java.sql.*;
//
//public class App {
//
//    public static void main(String[] args) {
//
//        //did we pass in a username and password
//        //if not, the application must die
//        if (args.length != 2) {
//            //display a message to the user
//            System.out.println("Application needs two args to run: A username and a password for the db");
//            //exit the app due to failure because we dont have a usernamne and password from the command line
//            System.exit(1);
//        }
//
//        //get the username and password from args[]
//        String username = args[0];
//        String password = args[1];
//
//        //create a scanner to ask the user some questions from our menu
//        Scanner myScanner = new Scanner(System.in);
//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password)) {
//
//            while (true) {
//
//                System.out.println("""
//                        What do you want to do?
//                            1) Display All Products
//                        """);
//
//                switch (myScanner.nextInt()) {
//                    case 1:
//                        displayAllProducts(connection);
//                        break;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Could not connect to DB");
//            System.exit(1);
//        }
//    }
//    public static void displayAllProducts(Connection connection) {
//
//        try (
//                PreparedStatement preparedStatement = connection.prepareStatement("""
//                SELECT
//                    ProductID,
//                    ProductName,
//                    UnitPrice,
//                    UnitsInStock
//                FROM
//                    Products
//                ORDER BY
//                    ProductID
//            """);
//
//                ResultSet results = preparedStatement.executeQuery();
//        ) {
//
//            printResults(results);
//
//        } catch (SQLException e) {
//            System.out.println("Could not get all the products");
//            System.exit(1);
//        }
//    }
//
//    //this method will be used in the displayMethods to actually print the results to the screen
//    public static void printResults(ResultSet results) throws SQLException {
//
//        System.out.printf("%-5s %-30s %-10s %-10s\n",
//                "ID", "Name", "Price", "Stock");
//        System.out.println("--------------------------------------------------------------");
//
//        while (results.next()) {
//            int id = results.getInt("ProductID");
//            String name = results.getString("ProductName");
//            double price = results.getDouble("UnitPrice");
//            int stock = results.getInt("UnitsInStock");
//
//            System.out.printf("%-5d %-30s %-10.2f %-10d\n",
//                    id, name, price, stock);
//        }
//    }
//}