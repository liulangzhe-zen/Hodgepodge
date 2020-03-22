package com.basics.common.mvp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:10
 * @Version: 1.0
 * @Description: java类作用描述
 */
public class BasePresenter<M extends IModel,V extends IView> implements IPresenter, LifecycleObserver {

    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    protected M mModel;
    protected V mRootView;


    public BasePresenter(@Nullable M mModel,@Nullable V mRootView) {
        this.mModel = mModel;
        this.mRootView = mRootView;
        onStart();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param rootView
     */
    public BasePresenter(@NonNull V rootView) {
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        //将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
            if (mModel != null && mModel instanceof LifecycleObserver) {
                ((LifecycleOwner) mRootView).getLifecycle().addObserver((LifecycleObserver) mModel);
            }
        }
    }

    @Override
    public void onDestroy() {
        unDispose();//解除订阅
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeDisposable = null;

    }


    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }
}
