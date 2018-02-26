package ink.envoy.easycamera.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public class ApplicationUtils {
    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}
