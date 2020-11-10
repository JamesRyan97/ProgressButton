package james.ryan.progressbuttonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import james.ryan.progressbutton.ProgressButton;


public class MainActivity extends AppCompatActivity {

    boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressButton progressButton = findViewById(R.id.btnActive);

        isSuccess = true;

        //Click action
        progressButton.setOnClickListener(new ProgressButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //State button (Success or fail)
                        progressButton.onCompleted(isSuccess);
                        isSuccess = !isSuccess;
                    }
                },3000);
            }
        });
    }
}