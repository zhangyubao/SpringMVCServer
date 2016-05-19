package com.zjlianhe.android.mapping;

import java.util.List;

import com.zjlianhe.android.bean.Book;

public interface BookMapper {

	/**
	 * ��������
	 * 
	 * @param books
	 */
	void insertList(List<Book> books);

	/**
	 * ����Bookid��ѯ
	 * 
	 * @param bookid
	 * @return
	 */
	Book getBookByID(int bookid);

	/**
	 * �������߲�ѯ
	 * 
	 * @return
	 */
	Book getBookByAuthor(String bookNmae);

	/**
	 * ����
	 * 
	 * @param book
	 */
	void addBook(Book book);

	/**
	 * ��ѯ
	 * 
	 * @return
	 */
	List<Book> getBook();

	/**
	 * ɾ��
	 * 
	 * @param book
	 * @return
	 */
	int deleteBook(int bookid);

	/**
	 * ����
	 * 
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
}
