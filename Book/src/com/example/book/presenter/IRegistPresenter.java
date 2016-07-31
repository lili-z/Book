package com.example.book.presenter;

import com.example.book.entity.User;


public interface IRegistPresenter extends IPresenter{
	/**
	 *加载验证码
	 */
	public void loadImage();

	/**
	 *注册
	 */
	public void regist(User user, String code);

}
