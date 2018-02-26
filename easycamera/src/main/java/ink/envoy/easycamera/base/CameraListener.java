package ink.envoy.easycamera.base;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public interface CameraListener {
    void onPictureTaken(String picturePath);
}
