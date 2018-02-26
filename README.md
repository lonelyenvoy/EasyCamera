# EasyCamera
A tiny, fast library simplifying picture-taking on Android.

## Usage in 3 steps

1. Extend your Activity with **EasyCameraActivity**:
```java
public class MainActivity extends EasyCameraActivity {
  // ...
}
```

2. Call ```openCamera()``` and inject ```CameraListener```
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

3. What you can do with the picture - for example:
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
compile 'ink.envoy.easycamera:app:0.0.1'
```

Via Maven:
```xml
<dependency>
  <groupId>ink.envoy.easycamera</groupId>
  <artifactId>app</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

## Contributing

Any improvement or bug-fixing is welcome. 
Create a <a href="https://github.com/lonelyenvoy/EasyCamera/pulls" target="_blank">pull request</a> when you are done.

## License

<a href="https://github.com/lonelyenvoy/EasyCamera/blob/master/LICENSE" target="_blank">The MIT License</a>
