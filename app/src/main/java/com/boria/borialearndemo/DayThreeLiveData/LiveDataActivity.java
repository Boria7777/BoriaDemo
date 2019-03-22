package com.boria.borialearndemo.DayThreeLiveData;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.boria.borialearndemo.R;

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

public class LiveDataActivity extends AppCompatActivity implements View.OnClickListener {

  private TextView text;
  private Button button;
  private NameViewModel mModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_livedata_daythree);

    initView();
    initData();
  }

  private void initView() {
    button = (Button) findViewById(R.id.button);
    text = (TextView) findViewById(R.id.textView);

    button.setOnClickListener(this);
  }

  private void initData() {
    mModel = ViewModelProviders.of(this).get(NameViewModel.class);
    final Observer<String> nameObserver = new Observer<String>() {
      @Override
      public void onChanged(String s) {
        text.setText(s);
      }
    };
    mModel.getCurrentName().observe(this, nameObserver);

//    ViewModelProviders.of(this).get(NameViewModel.class).getCurrentName().observe(this, new Observer<String>() {
//      @Override
//      public void onChanged(String s) {
//        text.setText(s);
//      }
//    });
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.button:
        mModel.getCurrentName().postValue("hehehehe");

        break;
    }
  }
}
