package com.boria.borialearndemo.DayThreeLiveData;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/***********************************************************************************************
 * 类名称:   
 * 类描述:  
 * 创建人:   包勇 2019/3/22. 
 * 创建时间:   2019/3/22. 
 * 创建备注：
 * 创建版本:  
 * 修改人:    
 * 修改时间:  
 * 修改备注: 
 *
 ************************************************************************************************/

public class NameViewModel extends ViewModel {
  private MutableLiveData<String> mCurrentName;

  public MutableLiveData<String> getCurrentName() {
    if (mCurrentName == null) {
      mCurrentName = new MutableLiveData<>();
    }
    return mCurrentName;
  }
}
