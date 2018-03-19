# SpeedAlert
A Library for monitoring the movement speed using speed and plays an alert when exceeding the set speed.

## Getting Started

Step 1: Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency:

```
dependencies {
	        compile 'com.github.YaraAHakiim:SpeedAlert:1.0.1'
	}
```


### Usage


```
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;

    private SpeedAlert mSpeedAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpeedAlert = new SpeedAlert(this, 10);
        mSpeedAlert.setMode(VoiceNotePlayer.Mode.ResourceId);
        mSpeedAlert.setVoiceNoteResId(R.raw.alert);
        
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mSpeedAlert.startUpdates(1, 1);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_LOCATION_PERMISSION_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mSpeedAlert.startUpdates(1, 1);
            }
        }
    }
}
```

## License
```
Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```

