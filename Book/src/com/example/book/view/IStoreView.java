package com.example.book.view;

import java.util.List;

import com.example.book.entity.Book;


@SuppressWarnings("DefaultFileTemplate")
public interface IStoreView extends IView{

	/**
	 * 更新推荐图书列表
	 * @param books
	 */
	void updateRecommendList(List<Book> books);

	/**
	 * 更新热销图书列表
	 * @param books
	 */
	void updateHotList(List<Book> books);

	/**
	 * 更新最新上架图书列表
	 * @param books
	 */
	void updateNewList(List<Book> books);

}
