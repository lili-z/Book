package com.example.book.presenter.impl;

import com.example.book.model.IModel;
import com.example.book.model.IUserModel;
import com.example.book.model.impl.UserModel;
import com.example.book.presenter.IMinePresenter;
import com.example.book.view.IMineView;


public class MinePresenter implements IMinePresenter {
	private IUserModel userModel;
	private IMineView view;

	public MinePresenter(IMineView view) {
		this.view = view;
		this.userModel = new UserModel();
	}

	@Override
	public void loginWithoutPwd(String token) {
		userModel.loginWithoutPwd(token, new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.updateUserInfo();
			}
			public void onError(Object error) {
			}
		});
	}
}
