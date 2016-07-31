package com.example.book.presenter;

import com.example.book.entity.Book;


public interface ICartPresenter extends IPresenter{

	/**
	 * 添加图书
	 */
	public boolean addBook(Book book);

	/**
	 * 删除图书
	 */
	public void deleteBook(int bookId);

	/**
	 * 修改谋一本书的数量
	 */
	public void modifyNum(int bookId, int num);

	/**
	 * 加载总价
	 */
	public void loadTotalPrice();

}
