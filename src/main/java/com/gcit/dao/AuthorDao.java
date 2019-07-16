
package com.gcit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.dto.Author;

public class AuthorDao {

	private Connection conn = null;
	public ArrayList<Author> geAllAuthor(){
		conn = JDBCDao.getConnection();
		ArrayList<Author> authors = new ArrayList<Author>();

		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_author;";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				Author tempAuthor = new Author();

				tempAuthor.setAuthorId(resultSet.getInt(1));
				tempAuthor.setAuthorName(resultSet.getString(2));
				authors.add(tempAuthor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return authors;
	}
	public Author getAuthorByID(int authorID) {
		// TODO Auto-generated method stub
		//PreparedStatement prepareStatement = null;
		conn = JDBCDao.getConnection();

		Author tempAuthor = new Author();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_author where authorId=?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, authorID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("author name is :: " + resultSet.getString("authorName"));
				tempAuthor.setAuthorId(resultSet.getInt(1));
				tempAuthor.setAuthorName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tempAuthor;
	}

	public void addByName(String authorName) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "INSERT INTO `lms`.`tbl_author` (`authorName`) VALUES (?)";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, authorName);
			prepareStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return null;
	}
	public String removeByName(String authorName) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();

		String sql = "delete from tbl_author where authorName = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, authorName);
			//execute
			prepareStatement.execute();
		} catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return authorName;
	}

	public void removeByID(int authorID) {
		conn = JDBCDao.getConnection();

		String sql = "delete from tbl_author where authorId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			
			prepareStatement.setInt(1, authorID);
			//execute
			prepareStatement.execute();
		} catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	public void updateByAuthorID(int authorID, String newAuthorName) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "update tbl_author set authorName = ? "
				+ "where authorId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, newAuthorName);
			prepareStatement.setInt(2, authorID);
			//execute
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

//
//import java.util.ArrayList;
//
//import com.gcit.dto.Author;
//
//public class AuthorDao {
//	ArrayList<Author> authors;
//	public AuthorDao() {
//		System.out.println("HIDF");
//		authors = new ArrayList<Author>();
//		authors.add(new Author (1, "author1"));
//		authors.add(new Author (2, "author2"));
//		authors.add(new Author (3, "author3"));
//		authors.add(new Author (4, "author4"));
//	}
//	public void createAuthor(Author newAuthor) {
//		System.out.println("hi");
//		authors.add(newAuthor);
//		System.out.println(authors);
//	}
//	public Author retrieveAuthorByID(int ID) {
//		return authors.get(ID);
//	}
//	public void updateAuthorName(int index, String newAuthorName) {
//		authors.get(index).setAuthorName(newAuthorName);
//	}
//	public Author deleteAuthorByID(int ID) {
//		return authors.remove(ID);
//	}
//	public ArrayList<Author> printAll() {
//		// TODO Auto-generated method stub
//		System.out.println(authors);
//
//		return authors;
//	}
//
//	
//}
