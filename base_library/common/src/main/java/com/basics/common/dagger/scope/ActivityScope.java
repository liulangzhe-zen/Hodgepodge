package com.basics.common.dagger.scope;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 16:17
 * @Version: 1.0
 * @Description: java类作用描述
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
