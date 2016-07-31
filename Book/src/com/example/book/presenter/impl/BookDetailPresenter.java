package com.example.book.presenter.impl;

import com.example.book.entity.Book;
import com.example.book.model.ICartModel;
import com.example.book.model.impl.CartModel;
import com.example.book.presenter.IBookDetailPresenter;
import com.example.book.view.IBookDetailView;


public class BookDetailPresenter implements IBookDetailPresenter {

	ICartModel model;
	IBookDetailView view;

	public BookDetailPresenter(IBookDetailView view) {
		model = new CartModel();
		this.view = view;
	}

	@Override
	public void addToCart(Book book) {
		boolean buy = model.addBook(book);
		view.addToCartSuccess();
	}
}
