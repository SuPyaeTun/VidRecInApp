package vidrec.inapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

// get rid of Ok/Cancel step
// need to ask for user's permission and remember it
// set the time limit for video recording
// a way to ask for safety or duress pin numbers after recording

public class MainActivity extends Activity {

    private final int VIDEO_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void captureVideo(View view) {
        Intent camera_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File video_file = getFilepath();
        Uri video_uri = Uri.fromFile(video_file);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, video_uri);
        camera_intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(camera_intent, VIDEO_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Video successfully recorded", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Video NOT successfully recorded", Toast.LENGTH_LONG).show();
            }
        }
    }

    public File getFilepath() {
        File folder = new File("sdcard/videos");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File video_file = new File(folder,"sample_video.mp4");

        return video_file;
    }


}
