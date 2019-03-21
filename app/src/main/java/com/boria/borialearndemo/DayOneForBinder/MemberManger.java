package com.boria.borialearndemo.DayOneForBinder;

import android.os.IInterface;
import android.os.RemoteException;

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

public interface MemberManger extends IInterface {
  List<MemberBean> getMemList();

  void addMember(MemberBean member);

  int add(int num1, int num2) throws RemoteException;
}
