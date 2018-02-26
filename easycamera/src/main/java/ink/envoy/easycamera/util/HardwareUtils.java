package ink.envoy.easycamera.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public class HardwareUtils {
    public static boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
