package com.boria.borialearndemo.DayOneForBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boria.borialearndemo.R;

import java.util.List;

/***********************************************************************************************
 * 类名称:   
 * 类描述:  
 * 创建人:   包勇 2019/3/20. 
 * 创建时间:   2019/3/20. 
 * 创建备注：
 * 创建版本:  
 * 修改人:    
 * 修改时间:  
 * 修改备注: 
 *
 ************************************************************************************************/

public class BinderActivity extends Activity implements View.OnClickListener {
  private EditText name, age, sex;
  private Button button, button2;
  private TextView memberListSize;
  private boolean isConnect = false;
  private static String TAG = "BinderActivity";
  private MemberManger personManger;

  private ServiceConnection conn = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      Log.e(TAG, "connect success");
      isConnect = true;
      personManger = MemBinder.asInterface(service);
      List<MemberBean> personList = personManger.getMemList();
      Log.e(TAG, personList.size() + "");

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      isConnect = false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_binder_daytwo);

    bindService();
    initView();

  }

  private void initView() {
    name = (EditText) findViewById(R.id.name);
    age = (EditText) findViewById(R.id.age);
    sex = (EditText) findViewById(R.id.sex);
    button = (Button) findViewById(R.id.button);
    button2 = (Button) findViewById(R.id.button2);
    memberListSize = (TextView) findViewById(R.id.memberListSize);

    button.setOnClickListener(this);
    button2.setOnClickListener(this);
  }


  @Override
  public void onClick(View v) {
    int id = v.getId();

    switch (id) {
      case R.id.button:
        if (personManger == null) {
          Log.e(TAG, "connect error");
          return;
        }
        personManger.addMember(new MemberBean(name.getText().toString(), Integer.parseInt(age.getText().toString()), Integer.parseInt(sex.getText().toString())));
        Log.e(TAG, personManger.getMemList().size() + "");
        break;
      case R.id.button2:
        memberListSize.setText(personManger.getMemList().size() + "");
        break;
    }
  }

  private void bindService() {

    Intent intent = new Intent();
    intent.setAction("com.boria.borialearndemo.DayOneForBinder.BinderService");
    intent.setComponent(new ComponentName("com.boria.borialearndemo", "com.boria.borialearndemo.DayOneForBinder.BinderService"));

    bindService(intent, conn, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbindService(conn);
  }
}
