package com.pureilab.controller.master;
/**
 * @(#)AlgorithmException.java
 *
 * Class AlgorithmException - Algorithm Exception
 *
 * @author		Qizhi Zhu <A HREF="mailto: qizhi@sinotar.com">qizhi@sinotar.com</A>
 * @since		JDK 1.2
 * @version		%I%, %G%
 */
/**
 * Algorithm exception.
 */
public class SecurityException extends Exception
{

    /**
     * Default Class constructor
     */
    public SecurityException() {
        super();
    }

    /**
     * Class constructor.
     * @param msg The message
     */
    public SecurityException(String msg) {
        super(msg);
    }
}