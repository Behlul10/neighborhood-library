package com.pluralsight;

import java.io.PushbackReader;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class LibraryApplication {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        Book[] booksList = new Book[20];
        int count = 0;
        int command = 0;

        for (int i = 0; i < booksList.length; i++) {
            booksList[i] = new Book(
                    i + 1,
                    "ISBN-" + (i + 1),
                    "Book Title " + (i + 1),
                    false,
                    ""
            );
        }
        do {
            System.out.println(""" 
                    ------ HOME ------
                    Choose an option below:
                    1 - Show available Books
                    2 - Show Checked out Books
                    3 - Exit out of the program
                    """);
            command = Integer.parseInt(myScanner.nextLine());
            System.out.println("selected command: " + command);

            switch (command) {
                case 1 -> {
                    listAllBook(booksList, myScanner);
                    break;
                }
                case 2 -> {
                    showAllCheckedOutBooks(booksList, myScanner);
                    break;
                }
                case 3 -> {
                    System.out.println("Well then... GoodBye!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong input! Try Again");
                }
            }
        } while (command != 3);
    /*
        System.out.println("First Book ID: " + booksList[0].getId());
        System.out.println("Last Book title: " + booksList[19].getTitle());

        booksList[0].checkOut("Davey");
        System.out.println("Who has book 1? " + booksList[0].getCheckedOutTo());

     */
    }


    public static void listAllBook(Book[] listOfBooks, Scanner methodScanner) {
        String userInput;
        int command;
        System.out.println("######################################################");
        System.out.println("Here is the list of all available books you can check out below!");

        outerLoop:
        for (Book item : listOfBooks) {
            if (item != null) {
                if (!item.isCheckedOut()) {
                    System.out.println(
                            "_______________________________ \n" +
                                    "Book Title: " + item.getTitle() + "\n" +
                                    "ID: " + item.getId() + "\n" +
                                    "ISBN: " + item.getIsbn() + "\n" +
                                    "Checked out to: " + item.getCheckedOutTo() + "\n"
                    );
                }
            }
        }
        System.out.println("""
                Choose an option below:
                    1 - Check out a book
                    2 - Go back to Home menu
                """);
        command = Integer.parseInt(methodScanner.nextLine());
        switch (command) {
            case 1 -> {
                checkOutBook(listOfBooks, methodScanner);
                break;
            }
            case 2 -> {
                return;
            }
            default -> {
                System.out.println("invalid Option");
            }
        }
    }

    public static void showAllCheckedOutBooks(Book[] listofBooks, Scanner methodScanner) {
        boolean hasCheckedOutBooks = false;

        System.out.println("#######################################");
        System.out.println("Here's the list of all currently checked out books:");

        for (Book item : listofBooks) {
            //print all available books
            if (item != null && item.isCheckedOut()) {
                System.out.println(
                        "_______________________________ \n" +
                                "Book Title: " + item.getTitle() + "\n" +
                                "ID: " + item.getId() + "\n" +
                                "ISBN: " + item.getIsbn() + "\n" +
                                "Checked out to: " + item.getCheckedOutTo() + "\n"
                                + "_______________________________ "
                );
                hasCheckedOutBooks = true;
            }
        }
        if(!hasCheckedOutBooks){
            System.out.println("No books are currently checked out.");
        }

        //proompt the user once after displaying the list if they wanna check in a book or exit.
        String userInput;
        System.out.println("""
                    ##################################
                    select an option:
                    C - to check in a book
                    X - to go back to the home screen
                    """);
        userInput = methodScanner.nextLine().toUpperCase();

        switch (userInput) {
            case "C" -> {
                checkInBook(listofBooks, methodScanner);
            }
            case "X" -> {
                return;
            }
            default -> {
                System.out.println("Invalid Input");
            }
        }
    }

    public static void checkOutBook(Book[] listOfBooks, Scanner methodScanner) {
        String userInput;
        String userName;
        int bookId;
        boolean found = false;

        System.out.println("What is your name? ");
        userName = methodScanner.nextLine();

        System.out.println("What is the Book ID You want to check out?");
        userInput = methodScanner.nextLine();
        bookId = Integer.parseInt(userInput);

        for (Book item : listOfBooks) {
            if (item != null && bookId == item.getId()) {
                if (!item.isCheckedOut()) {
                    item.checkOut(userName);
                    System.out.println("You have successfully checked out " + item.getTitle());
                    found = true;
                    break;
                } else {
                    System.out.println("The book is already checked out.");
                    return;
                }
            }
        }
        if(!found){
            System.out.println("Book with ID " + bookId + " was not found.");
        }
    }

    public static void checkInBook(Book[] listOfBooks, Scanner methodScanner) {
        String userInput;
        int bookId;
        System.out.println("what is the id of the book you want to check in?: ");
        userInput = methodScanner.nextLine();
        bookId = Integer.parseInt(userInput);

        for (Book item : listOfBooks) {
            if (item.getId() == bookId && item.isCheckedOut()) {
                item.checkIn();
                System.out.println("You have successfully checked out your book!");
            }
        }
    }
}