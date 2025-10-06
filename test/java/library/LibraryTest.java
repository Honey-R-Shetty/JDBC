package library;

import com.library.dao.LibraryDAO;
import com.library.model.Issue;
import com.util.DBConnection;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

	private static LibraryDAO dao;

	@BeforeAll
	static void init() {
		dao = new LibraryDAO();
	}

	@BeforeEach
	void setupTestData() throws SQLException {
		try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement()) {
			st.executeUpdate("DELETE FROM Issue");
			st.executeUpdate("DELETE FROM Student");
			st.executeUpdate("DELETE FROM Book");

			st.executeUpdate("INSERT INTO Student(student_id,name,branch) VALUES(1,'Alice','CSE')");
			st.executeUpdate(
					"INSERT INTO Book(book_id,title,author,available_copies) VALUES(101,'Java Book','James Gosling',2)");
			st.executeUpdate("INSERT INTO Issue(issue_id,student_id,book_id,issue_date) VALUES(1,1,101,CURDATE())");
		}
	}

	@Test
	void testIssueBookPositive() throws SQLException {
		boolean result = dao.issueBook(1, 101);
		assertTrue(result);
	}

	@Test
	void testIssueBookNegative_NoCopies() throws SQLException {
		boolean result = dao.issueBook(1, 9999);
		assertFalse(result);
	}

	@Test
	void testReturnBookPositive() throws SQLException {
		boolean result = dao.returnBook(1);
		assertTrue(result);
	}

	@Test
	void testReturnBookNegative_InvalidId() throws SQLException {
		boolean result = dao.returnBook(9999);
		assertFalse(result);
	}

	@Test
	void testGetStudentIssuesNegative_NoIssues() throws SQLException {
		List<Issue> issues = dao.getStudentIssues(9999);
		assertTrue(issues.isEmpty());
	}
}
