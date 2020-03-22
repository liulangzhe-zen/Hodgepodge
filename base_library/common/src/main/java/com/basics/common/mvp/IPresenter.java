package com.basics.common.mvp;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:07
 * @Version: 1.0
 * @Description: java类作用描述
 */
public interface IPresenter {


    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     *  释放 资源
     */
    void onDestroy();
}
