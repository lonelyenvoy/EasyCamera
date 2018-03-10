# EasyCamera

[![auc][aucsvg]][auc] [![api][apisvg]][api] [![repository][repositorysvg]][repository] [![License][licensesvg]][license]

[aucsvg]: https://img.shields.io/badge/EasyCamera-v0.2.0-brightgreen.svg
[auc]: https://github.com/lonelyenvoy/EasyCamera

[apisvg]: https://img.shields.io/badge/API-14+-brightgreen.svg
[api]: https://android-arsenal.com/api?level=14

[repositorysvg]: https://img.shields.io/badge/Bintray_JCenter-latest-blue.svg
[repository]: https://bintray.com/lonelyenvoy/maven/ink.envoy.easycamera

[licensesvg]: https://img.shields.io/badge/License-MIT-blue.svg
[license]: https://github.com/lonelyenvoy/EasyCamera/blob/master/LICENSE


A tiny, fast library simplifying picture-taking on Android.

## Usage in 3 steps

1. Extend your Activity with ```EasyCameraActivity```:
```java
public class MainActivity extends EasyCameraActivity {
  // ...
}
```

2. Call ```openCamera()``` and inject a ```CameraListener```:
```java
  openCamera(new CameraListener() {
    @Override
    public void onPictureTaken(String picturePath) {
      // Proceed with picturePath, the path to the picture taken just now.
    }
    @Override
    public void onCancelled() {
      // ...
    }
  });
```

By default, The ```picturePath``` is ```Environment.DIRECTORY_DCIM/{Your App Name}/{Random Filename}.jpg```. 
It can be customized by an optional argument of ```openCamera()```.

3. Do something with the picture - for example:
```java
@Override
public void onPictureTaken(String picturePath) {
  // add the picture to the system gallery.
  EasyCameraUtils.galleryAddPicture(getApplicationContext(), picturePath);

  // load the picture into an ImageView.
  EasyCameraUtils.loadPicture(imageView, picturePath);
}
```

## Add EasyCamera to your project

Via Gradle:
```groovy
implementation 'ink.envoy.easycamera:easycamera:0.2.0'
```

Via Maven:
```xml
<dependency>
  <groupId>ink.envoy.easycamera</groupId>
  <artifactId>easycamera</artifactId>
  <version>0.2.0</version>
  <type>pom</type>
</dependency>
```

## Pay Attention

If you override ```onActivityResult()``` or ```onRequestPermissionsResult()``` in your activity,
please call ```super.onRequestPermissionsResult()``` or ```super.onActivityResult()``` for EasyCamera to work properly.

## Contributing

Any improvement or bug-fixing is welcome. 
Create a <a href="https://github.com/lonelyenvoy/EasyCamera/pulls" target="_blank">pull request</a> when you are done.

## License

<a href="https://github.com/lonelyenvoy/EasyCamera/blob/master/LICENSE" target="_blank">The MIT License</a>
