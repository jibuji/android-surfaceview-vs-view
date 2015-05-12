# android-surfaceview-vs-view
--------------------------------------------

本实例是为了比较SurfaceView和View在绘制的性能差异，主要体现在Canvas上差异。

可以运行本实例，从log中查看绘制每一帧的耗时：adb logcat -s T

本实例默认是打开了硬件加速的，可以把硬件加速关闭，再打印出每一帧的耗时，比较一下。
关闭硬件加速的方法：
在AndroidManifest.xml中把
`android:hardwareAccelerated="true"`
改为
`android:hardwareAccelerated="false"`
