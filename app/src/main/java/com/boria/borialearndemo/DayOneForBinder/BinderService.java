package com.boria.borialearndemo.DayOneForBinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
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

public class BinderService extends Service {


  private static final String TAG = "BinderService";
  private List<MemberBean> memberBeanList = new ArrayList<>();


  @Override
  public void onCreate() {
    memberBeanList.add(new MemberBean("tom", 16, 1));
    super.onCreate();
  }

  private MemBinder mStub = new MemBinder() {
    @Override
    public List<MemberBean> getMemList() {
      return memberBeanList;
    }

    @Override
    public void addMember(MemberBean member) {
      if (member == null) {
        member = new MemberBean();
        Log.e(TAG, "null obj");
      }
      memberBeanList.add(member);
      Log.e(TAG, memberBeanList.size() + "");
    }
  };


  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return mStub;
  }
}
