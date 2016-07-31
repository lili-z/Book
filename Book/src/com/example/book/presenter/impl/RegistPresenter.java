package com.example.book.presenter.impl;

import android.graphics.Bitmap;

import com.example.book.entity.User;
import com.example.book.model.IModel;
import com.example.book.model.IUserModel;
import com.example.book.model.impl.UserModel;
import com.example.book.presenter.IRegistPresenter;
import com.example.book.view.IRegistView;


public class RegistPresenter implements IRegistPresenter {
	private IRegistView view;
	private IUserModel model;

	public RegistPresenter(IRegistView view) {
		this.view = view;
		model = new UserModel();
	}

	@Override
	public void loadImage() {
		model.getImageCode(new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.showCodeImage((Bitmap)success);
			}
			public void onError(Object error) {
			}
		});
	}

	@Override
	public void regist(User user, String code) {
		model.regist(user, code, new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.registSuccess();
			}
			public void onError(Object error) {
				view.registError(error.toString());
			}
		});
	}
}
