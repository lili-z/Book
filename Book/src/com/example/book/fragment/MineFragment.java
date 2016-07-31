package com.example.book.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.MyApplication;
import com.example.book.activity.AddressActivity;
import com.example.book.activity.LoginActivity;
import com.example.book.entity.User;
import com.example.book.presenter.IMinePresenter;
import com.example.book.presenter.impl.MinePresenter;
import com.example.book.view.IMineView;
import com.example.book.R;


public class MineFragment extends Fragment implements IMineView{
	@ViewInject(R.id.relativeMineHeader)
	private RelativeLayout mineHeader;
	@ViewInject(R.id.itemShoucang)
	private RelativeLayout itemShoucang;
	@ViewInject(R.id.itemAddress)
	private RelativeLayout itemAddress;
	@ViewInject(R.id.itemOrder)
	private RelativeLayout itemOrder;
	@ViewInject(R.id.itemSettings)
	private RelativeLayout itemSettings;
	@ViewInject(R.id.itemExit)
	private RelativeLayout itemExit;
	@ViewInject(R.id.tvNickname)
	private TextView tvNickname;
	@ViewInject(R.id.ivPhoto)
	private ImageView ivPhoto;

	private IMinePresenter presenter;

	private static final int REQUEST_CODE_LOGIN_USER = 1;

	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, null);
		presenter = new MinePresenter(this);
		x.view().inject(this, view);
		setListener();
		//自动登录
		String token = MyApplication.getContext().getToken();
		if(token != null) {
			presenter.loginWithoutPwd(token);
		}
		return view;
	}

	/**
	 * 添加监听
	 */
	private void setListener() {
		MineListener listener = new MineListener();
		itemShoucang.setOnClickListener(listener);
		itemAddress.setOnClickListener(listener);
		itemExit.setOnClickListener(listener);
		itemOrder.setOnClickListener(listener);
		itemSettings.setOnClickListener(listener);
		ivPhoto.setOnClickListener(listener);
	}

	@Override
	public void updateUserInfo() {
		User user = MyApplication.getContext().getCurrentUser();
		String nickname=user.getNickname();
		tvNickname.setText(nickname);
	}

	/**
	 * 处理监听
	 */
	class MineListener implements View.OnClickListener{
		public void onClick(View view) {
			switch (view.getId()){
				case R.id.itemShoucang:
					break;
				case R.id.itemAddress:
					Intent intent=new Intent(getActivity(), AddressActivity.class);
					startActivity(intent);
					break;
				case R.id.itemOrder:
					break;
				case R.id.itemSettings:
					break;
				case R.id.itemExit:
					break;
				case R.id.ivPhoto:
					Intent i = new Intent(getActivity(), LoginActivity.class);
					startActivityForResult(i, REQUEST_CODE_LOGIN_USER);
					break;
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode){
			case REQUEST_CODE_LOGIN_USER:
				if(resultCode == Activity.RESULT_OK) {
					updateUserInfo();
				}
				break;
		}
	}

	public void slide(float v) {
		mineHeader.setAlpha(v);
	}

}




