package com.boria.borialearndemo.DayOneForBinder;


import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;



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

public abstract class MemBinder extends Binder implements MemberManger {
  public static final String DESCRIPTOR = "com.boria.borialearndemo.DayOneForBinder.NumBinder";
  public static final int TRANSAVTION_addMem = IBinder.FIRST_CALL_TRANSACTION;
  public static final int TRANSAVTION_getMemList = IBinder.FIRST_CALL_TRANSACTION + 1;
  public static final int TRANSAVTION_addNum = IBinder.FIRST_CALL_TRANSACTION + 2;

  public static MemberManger asInterface(IBinder mIBinder) {
    IInterface iInterface = mIBinder.queryLocalInterface(DESCRIPTOR);
    if (null != iInterface && iInterface instanceof MemberManger) {
      return (MemberManger) iInterface;
    }
    return new Proxy(mIBinder);
  }

  @Override
  protected boolean onTransact(int code, @NonNull Parcel data, @NonNull Parcel reply, int flags) throws RemoteException {

    switch (code) {
      case INTERFACE_TRANSACTION:
        reply.writeString(DESCRIPTOR);
        return true;

      case TRANSAVTION_addMem:
        data.enforceInterface(DESCRIPTOR);
        MemberBean arg0 = null;
        if (data.readInt() != 0) {
          arg0 = MemberBean.CREATOR.createFromParcel(data);
        }
        this.addMember(arg0);
        reply.writeNoException();
        return true;
      case TRANSAVTION_getMemList:
        data.enforceInterface(DESCRIPTOR);
        List<MemberBean> result = this.getMemList();
        reply.writeNoException();
        reply.writeTypedList(result);
        return true;
      case TRANSAVTION_addNum:
        data.enforceInterface(DESCRIPTOR);
        int _arg0;
        _arg0 = data.readInt();
        int _arg1;
        _arg1 = data.readInt();
        int _result = this.add(_arg0, _arg1);
        reply.writeNoException();
        reply.writeInt(_result);
        return true;

    }

    return super.onTransact(code, data, reply, flags);
  }

  @Override
  public IBinder asBinder() {
    return this;
  }


  public static class Proxy implements MemberManger {
    private IBinder mIBinder;

    public Proxy(IBinder mIBinder) {
      this.mIBinder = mIBinder;
    }

    public String getInterfaceDescriptor() {
      return DESCRIPTOR;
    }

    @Override
    public IBinder asBinder() {
      return null;
    }

    @Override
    public void addMember(MemberBean member) {
      Parcel data = Parcel.obtain();
      Parcel replay = Parcel.obtain();

      try {
        data.writeInterfaceToken(DESCRIPTOR);
        if (member != null) {
          data.writeInt(1);
          member.writeToParcel(data, 0);
        } else {
          data.writeInt(0);
        }
        mIBinder.transact(MemBinder.TRANSAVTION_addMem, data, replay, 0);
        replay.readException();
      } catch (RemoteException e) {
        e.printStackTrace();
      } finally {
        replay.recycle();
        data.recycle();
      }
    }

    @Override
    public int add(int num1, int num2) throws RemoteException {
      android.os.Parcel _data = android.os.Parcel.obtain();
      android.os.Parcel _reply = android.os.Parcel.obtain();
      int _result;
      try {
        _data.writeInterfaceToken(DESCRIPTOR);
        _data.writeInt(num1);
        _data.writeInt(num2);
        mIBinder.transact(MemBinder.TRANSAVTION_addNum, _data, _reply, 0);
        _reply.readException();
        _result = _reply.readInt();
      }
      finally {
        _reply.recycle();
        _data.recycle();
      }
      return _result;
    }

    @Override
    public List<MemberBean> getMemList() {
      Parcel data = Parcel.obtain();
      Parcel replay = Parcel.obtain();
      List<MemberBean> result = null;
      try {
        data.writeInterfaceToken(DESCRIPTOR);
        mIBinder.transact(MemBinder.TRANSAVTION_getMemList, data, replay, 0);
        replay.readException();
        result = replay.createTypedArrayList(MemberBean.CREATOR);
      } catch (RemoteException e) {
        e.printStackTrace();
      } finally {
        replay.recycle();
        data.recycle();
      }
      return result;
    }
  }


}
