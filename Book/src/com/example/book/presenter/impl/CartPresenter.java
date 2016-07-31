package com.example.book.presenter.impl;

import com.example.book.entity.Book;
import com.example.book.model.ICartModel;
import com.example.book.model.impl.CartModel;
import com.example.book.presenter.ICartPresenter;
import com.example.book.view.ICartView;


public class CartPresenter implements ICartPresenter {
	private ICartModel model;
	private ICartView view;

	public CartPresenter(ICartView view) {
		this.view = view;
		this.model = new CartModel();
	}

	@Override
	public boolean addBook(Book book) {
		return model.addBook(book);
	}

	@Override
	public void deleteBook(int bookId) {
		model.deleteBook(bookId);
		view.updateTotalPrice(model.getTotalPrice());
	}

	@Override
	public void modifyNum(int bookId, int num) {
		model.modifyNum(bookId, num);
		view.updateTotalPrice(model.getTotalPrice());
	}

	@Override
	public void loadTotalPrice() {
		double price=model.getTotalPrice();
		view.updateTotalPrice(price);
	}
}
