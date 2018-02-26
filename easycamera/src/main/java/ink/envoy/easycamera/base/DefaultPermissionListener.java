package ink.envoy.easycamera.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public class DefaultPermissionListener implements PermissionListener {
    @Override
    public void onRequestExternalStoragePermissionFailed(Activity activity) {
        new AlertDialog.Builder(activity)
                .setMessage("需要存储权限来将拍摄的照片保存到手机上，请在系统设置中授予权限。")
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                })
                .show();
    }
}
