# EasyCamera

[![auc][aucsvg]][auc] [![api][apisvg]][api] [![repository][repositorysvg]][repository] [![License][licensesvg]][license]

[aucsvg]: https://img.shields.io/badge/EasyCamera-v0.0.2-brightgreen.svg
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

2. Call ```openCamera()``` and inject ```CameraListener```:
```java
try {
  openCamera(new CameraListener() {
    @Override
    public void onPictureTaken(String picturePath) {
      // Proceed with picturePath, the path to the picture taken just now.
    }
  });
} catch (NoCameraAvailableException e) {
  // ...
}
```

3. Do something with the picture - for example:
```java
@Override
public void onPictureTaken(String picturePath) {
  // add the picture to the system gallery.
  PictureUtils.galleryAddPicture(getApplicationContext(), picturePath);

  // load the picture into an ImageView.
  PictureUtils.loadPicture(imageView, picturePath);
}
```

## Add EasyCamera to your project

Via Gradle:
```groovy
compile 'ink.envoy.easycamera:easycamera:0.0.2'
```

Via Maven:
```xml
<dependency>
  <groupId>ink.envoy.easycamera</groupId>
  <artifactId>easycamera</artifactId>
  <version>0.0.2</version>
  <type>pom</type>
</dependency>
```

## Contributing

Any improvement or bug-fixing is welcome. 
Create a <a href="https://github.com/lonelyenvoy/EasyCamera/pulls" target="_blank">pull request</a> when you are done.

## License

<a href="https://github.com/lonelyenvoy/EasyCamera/blob/master/LICENSE" target="_blank">The MIT License</a>
