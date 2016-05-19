package com.zjlianhe.android.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zjlianhe.android.bean.Book;
import com.zjlianhe.android.mapping.BookMapper;

public class MyBatisTest {

	/**
	 * Mybatis使用说明： 1、配置mybatis的配置文件 mybatis.xml文件中包含 数据库的名称 用户名
	 * 密码等信息，同时还要包含要查询数据的映射文件
	 * 2.配置映射文件：映射文件实际上就是配置数据的库的增删改查的SQL语句(包含命名空间、mapper中id一定要唯一)
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// 1、 加载mybatis配置文件(
		// //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）)
		InputStream inputStream = MyBatisTest.class.getClassLoader()
				.getResourceAsStream("mybatis.xml");
		// 2、 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// 3、创建能执行映射文件中sql的sqlSession
		SqlSession sqlSession = sessionFactory.openSession();
		// getBookByAuthor(sqlSession);
		// addBook(sqlSession);
		// getBook(sqlSession);
		// updateBook(sqlSession);
		// deleteBook(sqlSession);
		// getBookByID(sqlSession);
		insertList(sqlSession);
	}

	/**
	 * 批量插入操作
	 * 
	 * @param sqlSession
	 */
	private static void insertList(SqlSession sqlSession) {
		List<Book> books = new ArrayList<Book>();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		try {
			for (int i = 200; i < 350; i++) {
				Book book = new Book();
				book.setAuthor("张玉保-" + i + "-ZYB");
				book.setBookName("MYBATIS使用详解--" + i);
				book.setDate(new Date());
				book.setPublisher("北京大学第-" + i + "-出版社");
				books.add(book);
			}
			mapper.insertList(books);
			/**
			 * 此处一定要提交 否则新增、删除、修改都是无效的操作
			 */
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 资源用完之后一定要关闭资源
			sqlSession.close();
		}
	}

	/**
	 * 根据bookid查询
	 * 
	 * @param sqlSession
	 */
	private static void getBookByID(SqlSession sqlSession) {
		try {
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			Book book = mapper.getBookByID(1107);
			System.err.println(book.toString() + "--------------getBookByID");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 根据作者查询
	 * 
	 * @param mapper
	 */
	private static void getBookByAuthor(SqlSession sqlSession) {
		try {
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			Book book = mapper.getBookByAuthor("ZYB张玉保ZYB");
			System.err.println(book.toString()
					+ "--------------getBookByAuthor");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 新增
	 * 
	 * @param mapper
	 */
	private static void addBook(SqlSession sqlSession) {
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		try {
			Book book = new Book();
			book.setAuthor("ZYB张玉保ZYB");
			book.setBookName("MYBATIS使用详解");
			book.setDate(new Date());
			book.setPublisher("北京大学出版社");
			mapper.addBook(book);
			/**
			 * 此处一定要提交 否则新增、删除、修改都是无效的操作
			 */
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 资源用完之后一定要关闭资源
			sqlSession.close();
		}

	}

	/**
	 * 删除
	 * 
	 * @param mapper
	 */
	private static void deleteBook(SqlSession sqlSession) {
		try {
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			mapper.deleteBook(1107);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}

	/**
	 * 更新
	 * 
	 * @param mapper
	 */
	private static void updateBook(SqlSession sqlSession) {
		try {
			// 首先要查询到
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			Book book = mapper.getBookByAuthor("张玉保ZYB");
			System.err.println(book.toString()
					+ "--------------getBookByAuthor");
			if (book != null) {
				book.setAuthor("ZYB张玉保ZYB");
				mapper.updateBook(book);
				sqlSession.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}

	/**
	 * 简单查询
	 * 
	 * @param mapper
	 */
	private static void getBook(SqlSession sqlSession) {
		/**
		 * 映射sql的标识字符串， com.zjlianhe.android.book_mapping是book_mapping.
		 * xml文件中mapper标签的namespace属性的值，
		 * getBook是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		// String statement =
		// "com.zjlianhe.android.mapping.book_mapping.getBook";// 映射sql的标识字符串
		// 执行查询返回一个唯一user对象的sql
		try {
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			List<Book> books = mapper.getBook();
			for (Book book : books) {
				System.err.println(book.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}
}
