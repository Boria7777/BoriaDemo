package com.boria.borialearndemo.DayOneForAIDL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.boria.borialearndemo.IImoocAIDL;

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

public class AidlService extends Service {

  private static String TAG = "AidlServiceDayOne";

  @Override
  public IBinder onBind(Intent intent) {
    return iBinder;
  }

  private IBinder iBinder = new IImoocAIDL.Stub() {

    @Override
    public int add(int num1, int num2) throws RemoteException {
      Log.i(TAG, "Get message from client     " + num1 + "  +  " + num2);
      return num1 + num2;
    }
  };
}
