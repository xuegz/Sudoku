package com.example.nine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nine.utils.Md5Utils;
import com.example.nine.utils.SharedPreferencesHelper;
import com.example.nine.view.LocusPassWordView;
import com.example.nine.view.LocusPassWordView.OnCompleteListener;

/*
 * 适用于登陆
 */
public class MainActivity2 extends Activity {
	private LocusPassWordView mPwdView; 
	private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        init();
    }  

    private void init() {
    	tv_title=(TextView) findViewById(R.id.tv_title);
    	  mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);  
          mPwdView.setOnCompleteListener(new OnCompleteListener() {  
              @Override  
              public void onComplete(String mPassword) {  
                  SharedPreferencesHelper sph = new SharedPreferencesHelper(MainActivity2.this);  
                  String pwd = sph.getString("password", "");  
                  Md5Utils md5 = new Md5Utils();  
                  //boolean passed = false;  
                  if (pwd.length() == 0) {
                       
                  } else {  
                      String encodedPwd = md5.toMd5(mPassword, "");  
                      if (encodedPwd.equals(pwd)) {  
                          //passed = true;
                          Intent intent = new Intent(MainActivity2.this,SecondActivity.class);  
                          startActivity(intent);  
                          finish();  
                      } else {  
                          mPwdView.markError(); 
                          tv_title.setText("密码错误，请重新绘制");
                      }  
                  }  

              }  
          }); 
	}
  
}