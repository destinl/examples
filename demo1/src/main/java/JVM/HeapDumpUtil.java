package JVM;

import com.sun.management.HotSpotDiagnosticMXBean;

import java.lang.management.ManagementFactory;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/16 23:08
 */
public class HeapDumpUtil {
    public static void dumpHeap(String filePath, boolean live) throws Exception {
        HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
                ManagementFactory.getPlatformMBeanServer(),
                "com.sun.management:type=HotSpotDiagnostic",
                HotSpotDiagnosticMXBean.class);
        mxBean.dumpHeap(filePath, live);
    }

    public static void main(String[] args) throws Exception {
        dumpHeap("heapdump.hprof", true);
    }
}