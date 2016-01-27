package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.Extension.DataService;
import com.alljf.jf.R;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.utils.StringManager;

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
	private HashMap<String, String> listmap = new HashMap<String, String>();
	private List<Map<String, String>> ques_list = new ArrayList<Map<String,String>>();
	public static int MPHONE_CODE = 1;
	private Handler handler; 
	private DataService client;
	private String message_code;
	private String ques_id;
	List<String> strList,idlist;
	private int i;//计时
	private Button mback;
	private SpotsDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_regn);
		initHandler();
		getQues();
		initView();
		setListenting();
	}
	
	private void getQues(){
		listmap.clear();
		listmap.put("type", "system");
		listmap.put("part", "question");
		client = new DataService(handler, 0, listmap);
		client.start();
	}
	
	private void initHandler(){
         handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				String data = "";
				JSONObject jsonObject;
				switch (msg.arg1) {
				case 0:
					try {
						Log.i("问题的返回数据是",msg.obj.toString());
						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("questions");
						
						ques_list = StringManager.getListMapByJson(data);
						strList = new ArrayList<String>();
						idlist = new ArrayList<String>();
						for(int n = 0; n<ques_list.size(); n++){
							Iterator<Entry<String, String>> it=ques_list.get(n).entrySet().iterator();
							while(it.hasNext()){
								Entry<String, String> en = it.next();
								strList.add(en.getValue());
								idlist.add(en.getKey());
							}
						}
						ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegnActivity.this, android.R.layout.simple_list_item_1, strList);
						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
						regn_spSpinner.setAdapter(adapter);
						regn_spSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								ques_id = idlist.get(position);
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								// TODO Auto-generated method stub
								
							}
						});
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				case 1:
					
						try {
							regn_btn_getcode.setClickable(true);
							jsonObject = new JSONObject(msg.obj.toString());
							data = jsonObject.getString("send_status");
							if(data.equals("1")){
								message_code = jsonObject.getString("message_code");
							}else{
								Toast.makeText(RegnActivity.this, "验证码获取失败，请重试", 0).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					break;
				case 2:
					try {
						dialog.dismiss();
						jsonObject = new JSONObject(msg.obj.toString());
						Log.i("注册完成后的数据是",msg.obj.toString());
						data = jsonObject.getString("reg_status");
						if(data.equals("1")){
							Toast.makeText(RegnActivity.this, "成功！", 0).show();
							RegnActivity.this.finish();
						}else if(data.equals("0")){
							Toast.makeText(RegnActivity.this, "注册失败，请重试", 0).show();
						}else if(data.equals("2")){
							Toast.makeText(RegnActivity.this, "手机号已被注册", 0).show();
						}else if(data.equals("3")){
							Toast.makeText(RegnActivity.this, "验证码错误", 0).show();
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				default:
					break;
				}
			};
		};
	}
	
	private void initView(){
		SpotsDialog.TAG=R.style.SpotsDialogDefault_tijiao;
		dialog=new SpotsDialog(this);
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
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String phnum = regn_phnum_txt.getText().toString();
		if(TextUtils.isEmpty(phnum)){
			Toast.makeText(RegnActivity.this, "请填写有效电话号码", 0).show();
			return;
		}
		switch (v.getId()) {
		case R.id.regn_btn_back:
			finish();
			break;
		case R.id.regn_show_btn:
			if(isShow){
				regn_pwd_txt.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				regn_btn_show.setText("显示");
				isShow = false;
			}else{
				regn_pwd_txt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
				regn_btn_show.setText("隐藏");
				isShow = true;
			}
			break;
		case R.id.regn_getcode_btn:
			
			regn_btn_getcode.setClickable(false);
              new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//先让线程睡上1000毫秒，
					for(i=59;i>0;i--)
					{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						regn_btn_getcode.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//更新ui上面的数字
								regn_btn_getcode.setText(i+"");
							}
						});
						
					}
					regn_btn_getcode.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							//更新ui上面的数字
							regn_btn_getcode.setText("获取验证码");
							regn_btn_getcode.setClickable(true);
						}
					});
				}
			}).start();
			listmap.put("type", "system");
			listmap.put("part", "message");
			listmap.put("mobilphone", phnum);
			
			client = new DataService(handler, 1, listmap);
			client.start();
			break;
		case R.id.regn_btn_submit:
			dialog.show();
			listmap.put("type", "user");
			listmap.put("part", "reg");
			listmap.put("mobilphone", phnum);
			listmap.put("code", regn_phcode_txt.getText().toString());
			listmap.put("password", regn_pwd_txt.getText().toString());
			listmap.put("question", ques_id);
			listmap.put("answer", regn_btn_ans.getText().toString());
			client = new DataService(handler, 2, listmap);
			client.start();
			break;
		default:
			break;
		}
	}
}
