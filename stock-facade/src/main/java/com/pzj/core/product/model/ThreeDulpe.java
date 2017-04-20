/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.model;

/**
 * 
 * @author dongchunfu
 * @version $Id: ThreeDulpe.java, v 0.1 2016年8月11日 下午3:37:04 dongchunfu Exp $
 */
public class ThreeDulpe<T, E, F> {
    private T t;
    private E e;
    private F f;

    public ThreeDulpe() {
        super();
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public F getF() {
        return f;
    }

    public void setF(F f) {
        this.f = f;
    }
}
