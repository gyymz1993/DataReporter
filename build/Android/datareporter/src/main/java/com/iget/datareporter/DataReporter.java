package com.iget.datareporter;

public class DataReporter {
    static {
        System.loadLibrary("data-reporter");
    }

    private static void upload(long key, String[] data, IReport report) {
        if (report != null) {
            report.upload(key, data);
        }
    }

    /**
     * 创建Reporter实例，实例是native的，保存在返回的int值中
     *
     * @param uuid      业务唯一id,由使用者定义一个唯一id
     * @param cachePath 缓存路径，必须全局唯一，不同业务不同路径
     * @param reportImp 上报实现接口
     * @return
     */
    public static native int makeReporter(String uuid, String cachePath, IReport reportImp);

    /**
     * 设置单次上报的数据条数，在start之前调用
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param count          一次上报的数据条数
     */
    public static native void setReportCount(int nativeReporter, int count);

    /**
     * 设置缓存文件单文件最大大小限制，默认值为10k
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param fileMaxSize    缓存文件最大大小
     */
    public static native void setFileMaxSize(int nativeReporter, int fileMaxSize);

    /**
     * 设置缓存文件单文件最大大小限制，默认值为10k
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param expiredTime    数据有效期
     */
    public static native void setExpiredTime(int nativeReporter, long expiredTime);

    /**
     * 设置缓存文件单文件最大大小限制，默认值为10k
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param reportingInterval   上报间隔
     */
    public static native void setReportingInterval(int nativeReporter, long reportingInterval);

    /**
     * 开始上报线程 设置完相关参数之后，执行一次。
     *
     * @param nativeReporter 由makeReporter返回的值
     */
    public static native void start(int nativeReporter);

    /**
     * 唤醒上报，在网络状态转好时调用或者其他想唤醒场景调用
     *
     * @param nativeReporter 由makeReporter返回的值
     */
    public static native void reaWaken(int nativeReporter);


    /**
     * 数据上报调用接口
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param data           需要上报的数据
     */
    public static native void push(int nativeReporter, String data);


    /**
     * 上报成功后调用该方法
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param key            由上报实现接口返回的值
     */
    public static native void uploadSucess(int nativeReporter, long key);

    /**
     * 上报失败后调用该方法
     *
     * @param nativeReporter 由makeReporter返回的值
     * @param key            由上报实现接口返回的值
     */
    public static native void uploadFailed(int nativeReporter, long key);


    /**
     * 释放上报实例，释放后其他方法都不可以调用
     *
     * @param nativeReporter 由makeReporter返回的值
     */
    public static native void releaseReporter(int nativeReporter);


}
