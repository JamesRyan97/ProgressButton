# ProgressButton

# NOTE
Project that uses the support library of [Android-SpinKit](https://github.com/ybq/Android-SpinKit)

## Usage

#### Step 1. Add the JitPack repository to your build file

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

#### Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.JamesRyan97:ProgressButton: latest-version'
}
```


### Using View
1. Add `ProgressButton` into your activity
 ```xml
 <james.ryan.progressbutton.ProgressButton
        android:id="@+id/btnActive"
        android:paddingBottom="4dp"
        android:layout_centerInParent="true"
        android:layout_marginStart="20dp"
        android:layout_marginEnd="20dp"
        android:layout_marginBottom="20dp"
        android:layout_width="match_parent"
        android:layout_height="42dp"
        app:loading_color="#fff"
        app:text_color="#fff"
        app:text="Active"
        app:text_error="Failed activation"
        app:text_loading="Activating"
        app:text_success="Successful activation"
        app:text_all_caps="true"
        app:loading_style="FadingCircle"
        android:background="@drawable/bg_button_red"/>
 ```
 
 2. Click event
 ```java
 final ProgressButton progressButton = findViewById(R.id.btnActive);
 progressButton.setOnClickListener(new ProgressButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Your Code
            }
        });
```
3. Update the status of the button
```java
 progressButton.onCompleted(isSuccess);
 ```
 
 4. Reset the initial state of the button
 ```java
 progressButton.resetState();
 ```
	
	
 
 
