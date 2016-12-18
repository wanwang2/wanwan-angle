package org.wanwanframework.angle.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 作用于系统的第一个元数据：用来注解符号
 * 本系统所有和元数据有关的地方统一定义
 * @author coco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Symbol {
	
	String value() default "@";
}
