package ink.envoy.easycamera.base;

import android.app.Activity;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public interface PermissionListener {
    void onRequestExternalStoragePermissionFailed(Activity activity);
}
