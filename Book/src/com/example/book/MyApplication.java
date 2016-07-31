package com.example.book;

import org.xutils.x;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.book.entity.Cart;
import com.example.book.entity.User;


public class MyApplication extends Application {

	private static MyApplication context;
	private Cart cart;
	private User user;
	private String token;

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		context=this;
		//初始化购物车
		cart = new Cart();
		cart = cart.readCart();
	}

	public static MyApplication getContext(){
		return context;
	}

	public Cart getCart(){
		return this.cart;
	}

	/**
	 * 保存当前用户
	 * @param user
	 */
	public void saveCurrentUser(User user){
		this.user = user;
	}

	public User getCurrentUser(){
		return this.user;
	}

	public void saveToken(String token){
		this.token = token;
		SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("token",token);
		editor.commit();
	}

	public String getToken(){
		SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
		String token=pref.getString("token","");
		return token;
	}

}



