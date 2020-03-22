package com.basics.common.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.basics.common.integration.IRepositoryManager;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:09
 * @Version: 1.0
 * @Description: java类作用描述
 */
public class BaseModel implements IModel, LifecycleObserver {


    /**
     * 用于管理网络请求层, 以及数据缓存层
     * */
    protected IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }



    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}
