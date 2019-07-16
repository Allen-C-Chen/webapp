package com.gcit.service;

import java.util.ArrayList;

import com.gcit.dao.AuthorDao;
import com.gcit.dto.Author;

public class AuthorService {
	AuthorDao authorDao;
	public AuthorService() {
		authorDao = new AuthorDao();
	}
	public void createAuthor(String newAuthorName) {
		authorDao.addByName(newAuthorName);
	}
	public Author retrieveAuthorByID(int ID) {
		return authorDao.getAuthorByID(ID-1);  
	}
	public void updateAuthorName(int index, String newAuthorName) {
		authorDao.updateByAuthorID(index, newAuthorName);
	}
	public void deleteAuthorByID(int ID) {
		authorDao.removeByID(ID);
	}
	public ArrayList<Author> getAllAuthors(){
		return authorDao.geAllAuthor();
	}

}
