package com.example.book.presenter;


public interface IStorePresenter extends IPresenter{

	/**
	 * 通过接口获取编辑推荐列表
	 */
	public void loadRecommendBooks();

	/**
	 * 通过接口获取编辑推荐列表
	 */
	public void loadHotBooks();

	/**
	 * 通过接口获取编辑推荐列表
	 */
	public void loadNewBooks();


}
