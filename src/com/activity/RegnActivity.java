package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:35:16
 * 注册界面
 */  
public class RegnActivity extends Activity implements OnClickListener{

	private Button regn_btn_back,regn_btn_show,regn_btn_getcode,regn_btn_commit;
	private EditText regn_phnum_txt, regn_pwd_txt, regn_phcode_txt, regn_btn_ans;
	private Spinner regn_spSpinner;
	private boolean isShow = true;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_regn);
		initView();
	}
	
	private void initView(){
		regn_btn_back = (Button) findViewById(R.id.regn_btn_back);
		regn_btn_show = (Button) findViewById(R.id.regn_show_btn);
		regn_btn_getcode = (Button) findViewById(R.id.regn_getcode_btn);
		regn_btn_commit = (Button) findViewById(R.id.regn_btn_submit);
		regn_phnum_txt = (EditText) findViewById(R.id.regn_phnum_txt);
		regn_pwd_txt = (EditText) findViewById(R.id.regn_pwd_txt);
		regn_phcode_txt = (EditText) findViewById(R.id.regn_phcode_et);
		regn_btn_ans = (EditText) findViewById(R.id.regn_ans_et);
		regn_spSpinner = (Spinner) findViewById(R.id.regn_ques_sp);
	}

	private void setListenting(){
		regn_btn_back.setOnClickListener(this);
		regn_btn_show.setOnClickListener(this);
		regn_btn_getcode.setOnClickListener(this);
		regn_btn_commit.setOnClickListener(this);
//		regn_spSpinner.
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.regn_btn_back:
			finish();
			break;
		case R.id.regn_show_btn:
			if(isShow){
				regn_pwd_txt.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				regn_btn_show.setText("隐藏");
				isShow = false;
			}else{
				regn_pwd_txt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
				regn_btn_show.setText("显示");
				isShow = true;
			}

		default:
			break;
		}
	}
}
