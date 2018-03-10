package ink.envoy.easycamera.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import ink.envoy.easycamera.R;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public class DefaultPermissionListener implements PermissionListener {
    @Override
    public void onRequestExternalStoragePermissionFailed(Activity activity) {
        new AlertDialog.Builder(activity)
                .setMessage(R.string.hint_write_external_storage_permission_required)
                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                })
                .show();
    }
}
