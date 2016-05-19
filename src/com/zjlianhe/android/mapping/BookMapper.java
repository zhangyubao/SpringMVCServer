package com.zjlianhe.android.mapping;

import java.util.List;

import com.zjlianhe.android.bean.Book;

public interface BookMapper {

	/**
	 * 批量插入
	 * 
	 * @param books
	 */
	void insertList(List<Book> books);

	/**
	 * 根据Bookid查询
	 * 
	 * @param bookid
	 * @return
	 */
	Book getBookByID(int bookid);

	/**
	 * 根据作者查询
	 * 
	 * @return
	 */
	Book getBookByAuthor(String bookNmae);

	/**
	 * 新增
	 * 
	 * @param book
	 */
	void addBook(Book book);

	/**
	 * 查询
	 * 
	 * @return
	 */
	List<Book> getBook();

	/**
	 * 删除
	 * 
	 * @param book
	 * @return
	 */
	int deleteBook(int bookid);

	/**
	 * 更新
	 * 
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
}
