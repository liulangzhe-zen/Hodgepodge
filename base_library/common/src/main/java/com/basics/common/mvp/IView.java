package com.basics.common.mvp;

import androidx.annotation.NonNull;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:05
 * @Version: 1.0
 * @Description: java类作用描述
 */
public interface IView {


    /**
     * 显示加载
     */
    default void showLoading() {

    }


    /**
     * 隐藏加载
     */
    default void hideLoading() {

    }

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 {@code null}
     */
    void showMessage(@NonNull String message);

}
