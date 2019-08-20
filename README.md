# Shared preferences

Permite almacenar datos primitivos de manera permanente mediante key-values.

Instancia 

```java
SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
```

Guardar información

```java 
SharedPreferences.Editor editor = this.sharedPreferences.edit();
editor.putString("email",email);
editor.apply();
```

Recuperar información

```java
this.sharedPreferences.getString("email","");
```

#  Splash screen

Drawable **splash.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:drawable="@color/colorAccent"/>
    <item>
        <bitmap
            android:gravity="center"
            android:src="@drawable/ic_launcher"/>
    </item>
</layer-list>
```

Style **styles.xml** Windows background

```xml
<!--Splash screen-->
<style name="SplashScreen" parent="Theme.AppCompat.NoActionBar">
    <item name="android:windowBackground">@drawable/splash</item>
</style>
```

Splash activity in **Manifest.xml** launcher and theme

```xml
<activity
    android:name=".Splash.SplashActivity"
    android:theme="@style/SplashScreen">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

Application **MyApplication.java** delay 

```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Delay
        SystemClock.sleep(3000);
    }
}
```