package com.example.book.model;

import com.example.book.entity.Address;

/**
 * Created by hanamingming on 16/3/6.
 */
public interface IAddressModel extends IModel {

	/**
	 *添加地址
	 */
	public void saveAddress(Address address, AsyncCallback callback);

	/**
	 * 地址列表
	 */
	public void listAddress(AsyncCallback callback);

	/**
	 * 设置默认地址
	 */
	public void setDefault(int id, AsyncCallback callback);

}
