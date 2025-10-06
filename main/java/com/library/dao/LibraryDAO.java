package com.library.dao;

import com.library.model.Issue;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

	public boolean issueBook(int studentId, int bookId) throws SQLException {
		String checkSql = "SELECT available_copies FROM Book WHERE book_id=?";
		String updateBookSql = "UPDATE Book SET available_copies=available_copies-1 WHERE book_id=?";
		String insertIssueSql = "INSERT INTO Issue(student_id, book_id, issue_date) VALUES(?, ?, NOW())";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement checkPs = con.prepareStatement(checkSql)) {

			checkPs.setInt(1, bookId);
			ResultSet rs = checkPs.executeQuery();
			if (!rs.next() || rs.getInt("available_copies") <= 0) {
				return false;
			}

			try (PreparedStatement updateBookPs = con.prepareStatement(updateBookSql);
					PreparedStatement insertIssuePs = con.prepareStatement(insertIssueSql)) {

				updateBookPs.setInt(1, bookId);
				updateBookPs.executeUpdate();

				insertIssuePs.setInt(1, studentId);
				insertIssuePs.setInt(2, bookId);
				insertIssuePs.executeUpdate();

				return true;
			}
		}
	}

	public boolean returnBook(int issueId) throws SQLException {
		String getBookSql = "SELECT book_id FROM Issue WHERE issue_id=?";
		String updateBookSql = "UPDATE Book SET available_copies=available_copies+1 WHERE book_id=?";
		String updateIssueSql = "UPDATE Issue SET return_date=NOW() WHERE issue_id=?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement getBookPs = con.prepareStatement(getBookSql)) {

			getBookPs.setInt(1, issueId);
			ResultSet rs = getBookPs.executeQuery();
			if (!rs.next())
				return false;
			int bookId = rs.getInt("book_id");

			try (PreparedStatement updateBookPs = con.prepareStatement(updateBookSql);
					PreparedStatement updateIssuePs = con.prepareStatement(updateIssueSql)) {

				updateBookPs.setInt(1, bookId);
				updateBookPs.executeUpdate();

				updateIssuePs.setInt(1, issueId);
				updateIssuePs.executeUpdate();

				return true;
			}
		}
	}

	public List<Issue> getStudentIssues(int studentId) throws SQLException {
		List<Issue> list = new ArrayList<>();
		String sql = "SELECT * FROM Issue WHERE student_id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Issue(rs.getInt("issue_id"), rs.getInt("student_id"), rs.getInt("book_id"),
						rs.getDate("issue_date"), rs.getDate("return_date")));
			}
		}
		return list;
	}
}
