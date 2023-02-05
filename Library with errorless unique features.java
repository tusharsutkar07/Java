/*
Date: 5-02-2023
Description:
This is a library program with errorless unique features.
This Library will handle each user's activity.
And there are so many feature in this Library, which you have to explore.
*/

package com.comany;
import java.util.*;

class library {
    List<String> availableBooks = new ArrayList<>();
    Map<String,List<String>> borrowedBooks = new HashMap<>();

    public void availableBooks(){
        System.out.println("Available books in the bellow:");
        System.out.println(availableBooks);
        exitOrMenu();
    }

    public void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book name to add: ");
        String book = sc.next();

        boolean cond=true;

        Set<String> keys = borrowedBooks.keySet();
        for(int j=0;j<borrowedBooks.size();j++){
            String key = (String) keys.toArray()[j];

            if(borrowedBooks.get(key).contains(book)){
                System.out.println("You entered book is already exist and\n" +
                        "borrowed by '"+key+"' named user");
                cond=false;
            }
        }

        if (cond){
            if(borrowedBooks.containsKey(book)){
                System.out.println("This book is already exist and borrowed by someone");
            }
            else if (availableBooks.contains(book)){
                System.out.println("This book is already exist in the library");
            }
            else{
                availableBooks.add(book.toLowerCase());
                System.out.println("'" + book + "' named book added successfully");
                System.out.println(availableBooks);
            }
        }
        exitOrMenu();
    }

    public void removeBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println(availableBooks);
        System.out.print("Enter book name to remove: ");
        String book = sc.next();
        availableBooks.remove(book.toLowerCase());
        System.out.println("'" + book + "' named book removed successfully");
        System.out.println(availableBooks);
        exitOrMenu();
    }

    public void borrowBook(String userName) {
        Scanner sc=new Scanner(System.in);
        System.out.println(availableBooks);
        System.out.print("Enter book name to borrow: ");
        String bookName=sc.next();

        if (availableBooks.contains(bookName)){
            if(borrowedBooks.containsKey(userName)){
                borrowedBooks.get(userName).add(bookName);
            } else {
                List<String> list = new ArrayList<>();
                list.add(bookName);
                borrowedBooks.put(userName, list);
            }
            System.out.println("You borrowed '"+bookName+"' named book");
            availableBooks.remove(bookName);
        }
        else if(!Objects.equals(isBookInBorrowedBook(bookName), "0")){
            String name=isBookInBorrowedBook(bookName);
            System.out.println("You enter book is borrowed by '"+name+"' named user");
        }
        else {
            System.out.println("This book not available in the library");
        }
        exitOrMenu();
    }

    public void showBorrowedBooks(){
        Set<String> keys = borrowedBooks.keySet();
        for(int j=0;j<borrowedBooks.size();j++){
            String key = (String) keys.toArray()[j];
            System.out.println("'"+key+"' named user borrowed books in the bellow:\n"+ borrowedBooks.get(key) );
        }
        if(borrowedBooks.isEmpty()){
            System.out.println("No one borrows any book for now");
        }
        exitOrMenu();
    }

    public String isBookInBorrowedBook(String bookName) {
        Set<String> keys = borrowedBooks.keySet();
        String userName="0";
        for (int j = 0; j < borrowedBooks.size(); j++) {
            String key = (String) keys.toArray()[j];
            if (borrowedBooks.get(key).contains(bookName)) {
                userName=key;
            }
        }
        return userName;
    }

    public void returnBorrowedBook(String userName){
        Scanner sc=new Scanner(System.in);
        boolean cond=true;
        try {
            if (!borrowedBooks.get(userName).isEmpty()){
                System.out.println("You borrowed bellow books:");
                System.out.println(borrowedBooks.get(userName));
            }
        }
        catch (Exception e){
            System.out.println("You never borrow any book to return");
            cond=false;
        }

        if (cond){
            System.out.print("Enter book name to return: ");
            String bookName=sc.next();

            if (!borrowedBooks.get(userName).contains(bookName)){
                System.out.println("You entered book not borrowed by you");
            }

            try{
                if (borrowedBooks.get(userName).contains(bookName)){
                    availableBooks.add(bookName);
                    borrowedBooks.get(userName).remove(bookName);
                    System.out.println("'"+bookName+"' named book returned successfully");
                }
                if(availableBooks.contains(bookName)){
                    System.out.println("You entered book is available in the library");
                }
                if(!Objects.equals(isBookInBorrowedBook(bookName), "0")){
                    String name=isBookInBorrowedBook(bookName);
                    System.out.println("You enter book is borrowed by '"+name+"' named user");
                }
                if(Objects.equals(isBookInBorrowedBook(bookName), "0") & !availableBooks.contains(bookName)){
                    System.out.println("This book is not available");
                }
                if (borrowedBooks.get(userName).isEmpty()){  // if you remove this if statement then
                    borrowedBooks.remove(userName);          // you can see previous book borrowed empty entries.
                }
            }
            catch (Exception e){
                System.out.println("error");
            }
        }
        exitOrMenu();
    }

    public void exitOrMenu(){
        Scanner sc = new Scanner(System.in);
        boolean cond=true;
        while (cond){
            System.out.println("\nPress m to Main menu");
            System.out.println("Press e to exit");
            String userInput = sc.next();
            if (Objects.equals(userInput, "e")){
                System.out.println("Exited");
                System.exit(0);
            } else if (Objects.equals(userInput, "m")) {
                cond=false;
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }
}
public class tut1 {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        library l=new library();

        List<String> defaultBookList = Arrays.asList("python","java","c++");
        l.availableBooks.addAll(defaultBookList);

        while (true){
            System.out.println("* Welcome to the Home page of Library *");
            System.out.print("Enter your name: ");
            String userName=sc.next();
            System.out.println("Welcome to the library '"+userName+"'");

            boolean cond=true;
            while(cond){

                System.out.println("\n\t* Main menu *");
                System.out.println("Press 1 to See available books");
                System.out.println("Press 2 to Add new book");
                System.out.println("Press 3 to Remove a book");
                System.out.println("Press 4 to Borrow a book");
                System.out.println("Press 5 to See borrowed books");
                System.out.println("Press 6 to Return borrowed books");
                System.out.println("Press 0 to the HomePage of library");
                System.out.println("Press 00 to Exit library");

                String choice= sc.next();

                if (Objects.equals(choice, "0")){
                    cond=false;
                }
                else if (Objects.equals(choice, "00")){
                    System.out.println("Exited Library");
                    System.exit(0);
                }
                else if (Objects.equals(choice, "1")){
                    l.availableBooks();
                }
                else if (Objects.equals(choice, "2")){
                    l.addBook();
                }
                else if (Objects.equals(choice, "3")){
                    l.removeBook();
                }
                else if (Objects.equals(choice, "4")){
                    l.borrowBook(userName);
                }
                else if (Objects.equals(choice, "5")){
                    l.showBorrowedBooks();
                }
                else if (Objects.equals(choice, "6")){
                    l.returnBorrowedBook(userName);
                }
                else{
                    System.out.println("Invalid input");
                }
            }
        }
    }
}
