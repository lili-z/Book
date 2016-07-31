package com.example.book.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.presenter.ILoginPresenter;
import com.example.book.presenter.impl.LoginPresenter;
import com.example.book.view.ILoginView;
import com.example.book.R;

public class LoginActivity extends Activity implements ILoginView {
	@ViewInject(R.id.etLoginname)
	private EditText etLoginname;
	@ViewInject(R.id.etPwd)
	private EditText etPwd;
	@ViewInject(R.id.tvNewAccount)
	private TextView tvToRegist;
	@ViewInject(R.id.tvModifyPwd)
	private TextView tvModifyPwd;
	@ViewInject(R.id.btnLogin)
	private Button btnLogin;
	private ILoginPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		presenter = new LoginPresenter(this);
		//注入控件   初始化
		x.view().inject(this);
		//添加监听
		setListener();
	}

	//添加监听
	private void setListener() {
		LoginViewListener listener = new LoginViewListener();
		tvToRegist.setOnClickListener(listener);
		btnLogin.setOnClickListener(listener);
	}

	public void back(View view){
		finish();
	}

	@Override
	public void loginSuccess() {
		Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
		setResult(RESULT_OK);
		finish();
	}

	@Override
	public void loginFailed(String errorMessage) {
		Toast.makeText(this,"登录失败,"+errorMessage,Toast.LENGTH_SHORT).show();
	}

	class LoginViewListener implements View.OnClickListener{
		public void onClick(View view) {
			switch (view.getId()){
				case R.id.tvNewAccount://新用户注册
					Intent i = new Intent(LoginActivity.this, RegistActivity.class);
					startActivity(i);
					break;
				case R.id.btnLogin:
					String name=etLoginname.getText().toString();
					String pwd=etPwd.getText().toString();
					if(name.equals("") || pwd.equals("")){
						Toast.makeText(LoginActivity.this,"请输入账号或密码",Toast.LENGTH_SHORT).show();
						return;
					}
					presenter.login(name, pwd);
					break;
			}
		}
	}
}
