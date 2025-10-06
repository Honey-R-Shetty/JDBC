package com.library.main;

import com.library.dao.LibraryDAO;
import com.library.model.Issue;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LibraryDAO dao = new LibraryDAO();

		while (true) {
			System.out.println("\n===== Library Management System =====");
			System.out.println("1. Issue Book");
			System.out.println("2. Return Book");
			System.out.println("3. View Student Issues");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			try {
				switch (choice) {
				case 1:
					System.out.print("Enter Student ID: ");
					int studentId = sc.nextInt();
					System.out.print("Enter Book ID: ");
					int bookId = sc.nextInt();
					boolean issued = dao.issueBook(studentId, bookId);
					if (issued) {
						System.out.println("Book issued successfully.");
					} else {
						System.out.println("Book could not be issued.");
					}
					break;

				case 2:
					System.out.print("Enter Issue ID: ");
					int issueId = sc.nextInt();
					boolean returned = dao.returnBook(issueId);
					if (returned) {
						System.out.println("Book returned successfully.");
					} else {
						System.out.println("Invalid Issue ID.");
					}
					break;

				case 3:
					System.out.print("Enter Student ID: ");
					int sid = sc.nextInt();
					List<Issue> issues = dao.getStudentIssues(sid);
					if (issues.isEmpty()) {
						System.out.println("No books issued for this student.");
					} else {
						for (Issue i : issues) {
							System.out.println("IssueID: " + i.issueId + ", BookID: " + i.bookId + ", Issue Date: "
									+ i.issueDate + ", Return Date: " + i.returnDate);
						}
					}
					break;

				case 4:
					System.out.println("Exiting Library System.");
					sc.close();
					return;

				default:
					System.out.println("Invalid choice.");
				}
			} catch (SQLException e) {
				System.out.println("Database error: " + e.getMessage());
			}
		}
	}
}
