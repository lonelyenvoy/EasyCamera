package ink.envoy.easycamera.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public class FileUtils {
    @NonNull
    public static File createImageFile(Context context, File storageDirectory) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        if (storageDirectory == null) {
            // set the storage directory as default: DCIM/APPLICATION_NAME/
            File dcimDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            storageDirectory = new File(dcimDir, ApplicationUtils.getApplicationName(context));
        }

        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdirs()) {
                String msg = "Unable to create storage directory " + storageDirectory.getAbsolutePath();
                Log.e("EasyCameraLog", msg);
                throw new IOException(msg);
            }
        }
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }
}
