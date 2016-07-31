package com.example.book.presenter.impl;

import com.example.book.model.IModel;
import com.example.book.model.IUserModel;
import com.example.book.model.impl.UserModel;
import com.example.book.presenter.ILoginPresenter;
import com.example.book.view.ILoginView;


public class LoginPresenter implements ILoginPresenter {

	private ILoginView view;
	private IUserModel model;

	public LoginPresenter(ILoginView view) {
		this.view = view;
		this.model = new UserModel();
	}

	@Override
	public void login(String loginname, String password) {
		model.login(loginname,password,new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				//登录成功
				view.loginSuccess();
			}
			public void onError(Object error) {
				view.loginFailed(error.toString());
			}
		});
	}

}
