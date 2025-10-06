package com.library.model;

import java.sql.Date;

public class Issue {
	public int issueId, studentId, bookId;
	public Date issueDate, returnDate;

	public Issue(int issueId, int studentId, int bookId, Date issueDate, Date returnDate) {
		this.issueId = issueId;
	}
}
