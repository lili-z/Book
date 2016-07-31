package com.example.book.presenter.impl;

import java.util.List;

import com.example.book.entity.Book;
import com.example.book.model.IModel;
import com.example.book.model.IStoreModel;
import com.example.book.model.impl.StoreModel;
import com.example.book.presenter.IStorePresenter;
import com.example.book.view.IStoreView;


public class StorePresenter implements IStorePresenter {
	private IStoreModel storeModel;
	private IStoreView storeView;

	public StorePresenter(IStoreView storeView) {
		this.storeModel = new StoreModel();
		this.storeView = storeView;
	}

	@Override
	public void loadRecommendBooks() {
		storeModel.getRecommendList(new IModel.AsyncCallback() {
			@Override
			public void onSuccess(Object obj) {
				List<Book> books = (List<Book>) obj;
				storeView.updateRecommendList(books);
			}

			@Override
			public void onError(Object error) {

			}
		});
	}

	@Override
	public void loadHotBooks() {
		storeModel.getHotList(new IModel.AsyncCallback() {
			@Override
			public void onSuccess(Object obj) {
				List<Book> books = (List<Book>) obj;
				storeView.updateHotList(books);
			}

			@Override
			public void onError(Object error) {

			}
		});
	}

	@Override
	public void loadNewBooks() {
		storeModel.getNewList(new IModel.AsyncCallback() {
			@Override
			public void onSuccess(Object obj) {
				List<Book> books = (List<Book>) obj;
				storeView.updateNewList(books);
			}

			@Override
			public void onError(Object error) {

			}
		});
	}
}
