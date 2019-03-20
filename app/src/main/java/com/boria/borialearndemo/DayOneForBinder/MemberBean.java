package com.boria.borialearndemo.DayOneForBinder;

import android.os.Parcel;
import android.os.Parcelable;

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

public class MemberBean implements Parcelable {
  private String name = null;
  private int age = 0;
  private int sex = 0;// 0代表男、1代表女

  public MemberBean() {
    super();
  }

  public MemberBean(String name, int age, int sex) {
    super();
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeInt(age);
    dest.writeInt(sex);
  }

  public static final Parcelable.Creator<MemberBean> CREATOR = new Creator<MemberBean>() {

    @Override
    public MemberBean[] newArray(int size) {
      return new MemberBean[size];
    }

    @Override
    public MemberBean createFromParcel(Parcel source) {
      return new MemberBean(source.readString(), source.readInt(),
        source.readInt());
    }
  };
}