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
	 * Mybatisʹ��˵���� 1������mybatis�������ļ� mybatis.xml�ļ��а��� ���ݿ������ �û���
	 * �������Ϣ��ͬʱ��Ҫ����Ҫ��ѯ���ݵ�ӳ���ļ�
	 * 2.����ӳ���ļ���ӳ���ļ�ʵ���Ͼ����������ݵĿ����ɾ�Ĳ��SQL���(���������ռ䡢mapper��idһ��ҪΨһ)
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// 1�� ����mybatis�����ļ�(
		// //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���)
		InputStream inputStream = MyBatisTest.class.getClassLoader()
				.getResourceAsStream("mybatis.xml");
		// 2�� ����sqlSession�Ĺ���
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// 3��������ִ��ӳ���ļ���sql��sqlSession
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
	 * �����������
	 * 
	 * @param sqlSession
	 */
	private static void insertList(SqlSession sqlSession) {
		List<Book> books = new ArrayList<Book>();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		try {
			for (int i = 200; i < 350; i++) {
				Book book = new Book();
				book.setAuthor("����-" + i + "-ZYB");
				book.setBookName("MYBATISʹ�����--" + i);
				book.setDate(new Date());
				book.setPublisher("������ѧ��-" + i + "-������");
				books.add(book);
			}
			mapper.insertList(books);
			/**
			 * �˴�һ��Ҫ�ύ ����������ɾ�����޸Ķ�����Ч�Ĳ���
			 */
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ��Դ����֮��һ��Ҫ�ر���Դ
			sqlSession.close();
		}
	}

	/**
	 * ����bookid��ѯ
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
	 * �������߲�ѯ
	 * 
	 * @param mapper
	 */
	private static void getBookByAuthor(SqlSession sqlSession) {
		try {
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			Book book = mapper.getBookByAuthor("ZYB����ZYB");
			System.err.println(book.toString()
					+ "--------------getBookByAuthor");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * ����
	 * 
	 * @param mapper
	 */
	private static void addBook(SqlSession sqlSession) {
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		try {
			Book book = new Book();
			book.setAuthor("ZYB����ZYB");
			book.setBookName("MYBATISʹ�����");
			book.setDate(new Date());
			book.setPublisher("������ѧ������");
			mapper.addBook(book);
			/**
			 * �˴�һ��Ҫ�ύ ����������ɾ�����޸Ķ�����Ч�Ĳ���
			 */
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ��Դ����֮��һ��Ҫ�ر���Դ
			sqlSession.close();
		}

	}

	/**
	 * ɾ��
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
	 * ����
	 * 
	 * @param mapper
	 */
	private static void updateBook(SqlSession sqlSession) {
		try {
			// ����Ҫ��ѯ��
			BookMapper mapper = sqlSession.getMapper(BookMapper.class);
			Book book = mapper.getBookByAuthor("����ZYB");
			System.err.println(book.toString()
					+ "--------------getBookByAuthor");
			if (book != null) {
				book.setAuthor("ZYB����ZYB");
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
	 * �򵥲�ѯ
	 * 
	 * @param mapper
	 */
	private static void getBook(SqlSession sqlSession) {
		/**
		 * ӳ��sql�ı�ʶ�ַ����� com.zjlianhe.android.book_mapping��book_mapping.
		 * xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
		 * getBook��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
		 */
		// String statement =
		// "com.zjlianhe.android.mapping.book_mapping.getBook";// ӳ��sql�ı�ʶ�ַ���
		// ִ�в�ѯ����һ��Ψһuser�����sql
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
