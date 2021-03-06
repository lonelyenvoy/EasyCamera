package ink.envoy.easycamera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ink.envoy.easycamera.base.CameraListener;
import ink.envoy.easycamera.base.DefaultPermissionListener;
import ink.envoy.easycamera.base.PermissionListener;
import ink.envoy.easycamera.exception.NoCameraAvailableException;
import ink.envoy.easycamera.util.EasyCameraUtils;
import ink.envoy.easycamera.util.FileUtils;

/**
 * Created by LonelyEnvoy on 2018/2/26.
 */

public class EasyCameraActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 65535;
    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_BEFORE_TAKING_PHOTO = 65534;

    private String currentPhotoPath;
    private Uri currentPhotoURI;
    private File currentStorageDirectory;

    private CameraListener cameraListener;
    private PermissionListener permissionListener;

    protected final void openCamera(CameraListener cameraListener) throws NoCameraAvailableException {
        openCamera(null, cameraListener, new DefaultPermissionListener());
    }

    protected final void openCamera(CameraListener cameraListener, PermissionListener permissionListener) throws NoCameraAvailableException {
        openCamera(null, cameraListener, permissionListener);
    }

    protected final void openCamera(File storageDirectory, CameraListener cameraListener) throws NoCameraAvailableException {
        openCamera(storageDirectory, cameraListener, new DefaultPermissionListener());
    }

    protected final void openCamera(File storageDirectory, CameraListener cameraListener, PermissionListener permissionListener) throws NoCameraAvailableException {
        if (!EasyCameraUtils.deviceHasCamera(this)) {
            throw new NoCameraAvailableException();
        }

        this.cameraListener = cameraListener;
        this.permissionListener = permissionListener;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_BEFORE_TAKING_PHOTO);
        } else {
            currentStorageDirectory = storageDirectory;
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_BEFORE_TAKING_PHOTO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    permissionListener.onRequestExternalStoragePermissionFailed(EasyCameraActivity.this);
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {

            // solve SecurityException
            // see https://medium.com/@a1cooke/using-v4-support-library-fileprovider-and-camera-intent-a45f76879d61
            getApplicationContext().revokeUriPermission(currentPhotoURI,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            currentPhotoURI = null;

            switch (resultCode) {
                case RESULT_OK: cameraListener.onPictureTaken(currentPhotoPath); break;
                case RESULT_CANCELED: cameraListener.onCancelled(); break;
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Context context = getApplicationContext();
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile;
            try {
                photoFile = FileUtils.createImageFile(getApplicationContext(), currentStorageDirectory);
                currentPhotoPath = photoFile.getAbsolutePath();
            } catch (IOException e) {
                // Error occurred while creating the file; make app crash
                throw new RuntimeException(e);
            }
            Uri photoURI = FileProvider.getUriForFile(this,
                    context.getPackageName() + ".fileprovider",
                    photoFile);
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

            // solve SecurityException
            // see https://medium.com/@a1cooke/using-v4-support-library-fileprovider-and-camera-intent-a45f76879d61

            // save photoURI
            currentPhotoURI = photoURI;

            List<ResolveInfo> resolvedIntentActivities = context
                    .getPackageManager()
                    .queryIntentActivities(takePhotoIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                String packageName = resolvedIntentInfo.activityInfo.packageName;
                context.grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
