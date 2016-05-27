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
 * 适用于设置密码
 */
public class MainActivity extends Activity {
	private LocusPassWordView mPwdView; 
	private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }  

    private void init() {
    	  tv_title=(TextView) findViewById(R.id.tv_title);
    	  mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);  
          mPwdView.setOnCompleteListener(new OnCompleteListener() {  
              @Override  
              public void onComplete(String mPassword) {  
                  SharedPreferencesHelper sph = new SharedPreferencesHelper(MainActivity.this);  
                  String pwd = sph.getString("password", "");  
                  Md5Utils md5 = new Md5Utils();  
                  //boolean passed = false;  
                  if (pwd.length() == 0) {
                      sph.putString("password", md5.toMd5(mPassword, "")); 
                      mPwdView.clear(1000);
                      tv_title.setText("请再次绘制");
                      //passed = true;  
                  } else {  
                      String encodedPwd = md5.toMd5(mPassword, "");  
                      if (encodedPwd.equals(pwd)) {  
                          //passed = true;
                          Intent intent = new Intent(MainActivity.this,SecondActivity.class);  
                          startActivity(intent);  
                          finish();  
                      } else {  
                    	  sph.putString("password", "");
                          mPwdView.markError();  
                          tv_title.setText("与上次绘制不一致，请重新绘制");
                      }  
                  }  
                    
                  /*if (passed) {  
                      Intent intent = new Intent(MainActivity.this,MainActivity.class);  
                      startActivity(intent);  
                      finish();  
                  }  */
              }  
          }); 
	}
  
}
