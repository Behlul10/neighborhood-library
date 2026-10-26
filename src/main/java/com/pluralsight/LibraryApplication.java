package com.pluralsight;
import java.util.Scanner;

public class LibraryApplication {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        Book[] booksList = new Book[20];
        int count = 0;

        for(int i = 0; i < booksList.length; i++){
            booksList[i] = new Book(
                    i + 1,
                    "ISBN-" + (i + 1),
                    "Book Title " + (i + 1),
                    false,
                    ""
            );
        }
        System.out.println("First Book ID: " + booksList[0].getId());
        System.out.println("Last Book title: " + booksList[19].getTitle());

        booksList[0].checkOut("Davey");
        System.out.println("Who has book 1? " + booksList[0].getCheckedOutTo());
    }
}
