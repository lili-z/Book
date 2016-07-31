package com.example.book.model.impl;

import com.example.book.MyApplication;
import com.example.book.entity.Book;
import com.example.book.entity.Cart;
import com.example.book.model.ICartModel;


public class CartModel implements ICartModel {
	private Cart cart;

	public CartModel() {
		cart = MyApplication.getContext().getCart();
	}

	@Override
	public boolean addBook(Book book) {
		return cart.buy(book);
	}

	@Override
	public void deleteBook(int bookId) {
		cart.deleteBook(bookId);
	}

	@Override
	public void modifyNum(int bookId, int num) {
		cart.modifyNum(bookId, num);
	}

	@Override
	public double getTotalPrice() {
		return cart.getTotalPrice();
	}
}
