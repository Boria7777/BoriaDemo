package com.boria.borialearndemo.DayOneForAIDL;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boria.borialearndemo.IImoocAIDL;
import com.boria.borialearndemo.R;

/***********************************************************************************************
 * 类名称:
 * 类描述:  用于探究多进程aidl通信
 * 创建人:   包勇 2019/3/20. 
 * 创建时间:   2019/3/20. 
 * 创建备注：
 * 创建版本:  
 * 修改人:    
 * 修改时间:  
 * 修改备注: 
 *
 ************************************************************************************************/

public class AidlActivity extends Activity implements View.OnClickListener {
  private EditText num1;
  private EditText num2;
  private Button button;
  private TextView text;

  private IImoocAIDL iImoocAIDL;

  private ServiceConnection conn = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      iImoocAIDL = IImoocAIDL.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      iImoocAIDL = null;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_aidl_dayone);

    bindService();
    initView();

  }

  private void initView() {
    num1 = (EditText) findViewById(R.id.num1);
    num2 = (EditText) findViewById(R.id.num2);
    button = (Button) findViewById(R.id.button);
    text = (TextView) findViewById(R.id.text);

    button.setOnClickListener(this);
  }


  @Override
  public void onClick(View v) {
    int num11 = Integer.parseInt(num1.getText().toString());
    int num22 = Integer.parseInt(num2.getText().toString());

    try {
      int res = iImoocAIDL.add(num11, num22);
      text.setText(num11 + "+" + num22 + "=" + res);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void bindService() {

    Intent intent = new Intent();
    intent.setAction("com.boria.borialearndemo.DayOneForAIDL.AidlService");
    intent.setComponent(new ComponentName("com.boria.borialearndemo", "com.boria.borialearndemo.DayOneForAIDL.AidlService"));

    bindService(intent, conn, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbindService(conn);
  }
}
