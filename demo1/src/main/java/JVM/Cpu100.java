package JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/27 17:02
 */
public class Cpu100 {
//    public ShortUrlRandomSeed getAvailableSeed() {
//        MachineInfo machineInfo = UrlConverUtil.getMachineInfo();
//        for (;;) {
//            ShortUrlRandomSeed seed = shortUrlSeedService.getAvailableSeed(machineInfo);
//            if (seed != null) {
//                int influenceNum = shortUrlSeedService.updateSeedStatus(seed.getId());
//                if (influenceNum > 0) {
//                    return seed;
//                }
//            }
//        }
//    }
    public static void main(String[] args) {
        //OOM
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}
