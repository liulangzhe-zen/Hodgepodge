package com.basics.common.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basics.common.BaseApplication;
import com.basics.common.R;
import com.basics.common.mvp.BasePresenter;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:04
 * @Version: 1.0
 * @Description: java类作用描述
 */
public abstract class BaseActivtiy<T extends BasePresenter> extends AppCompatActivity {

    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        bind = ButterKnife.bind(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mPresenter != null) {
            mPresenter.onStart();
        }
        initView();
        initListener();
        initData();
        initImBar();

    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    public void initImBar() {
        ImmersionBar.with(this).navigationBarColor(R.color.cff6c6c).init();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Unbinder.EMPTY != null) {
            bind.unbind();
        }

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }


    }


}
