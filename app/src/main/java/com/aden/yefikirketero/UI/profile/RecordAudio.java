package com.aden.yefikirketero.UI.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Post;
import com.aden.yefikirketero.retrofit.model.PostUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecordAudio extends AppCompatActivity {

    Button recorderButton, playerButton, finishButton;

    Boolean isItRecording = false;
    Boolean isItPlaying = false;

    Context context = this;

    YeFikirKeteroApi api;

    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);

        recorderButton = findViewById(R.id.buttonRecorder);
        playerButton = findViewById(R.id.buttonPlayer);
        finishButton = findViewById(R.id.buttonFinishProfile);

        playerButton.setEnabled(false);

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/AudioRecording.3gp";

        recorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPermissions()) {
                    if(!isItRecording) {
                        playerButton.setEnabled(false);
                        mRecorder = new MediaRecorder();
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mRecorder.setOutputFile(mFileName);
                        try {
                            mRecorder.prepare();
                        } catch (IOException e) {
                            Log.e(LOG_TAG, "prepare() failed");
                        }
                        mRecorder.start();
                        recorderButton.setBackgroundResource(R.drawable.stop_recording);
                        Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_LONG).show();
                        isItRecording = true;
                    }else{
                        playerButton.setEnabled(true);
                        mRecorder.stop();
                        mRecorder.release();
                        mRecorder = null;
                        recorderButton.setBackgroundResource(R.drawable.start_recording);
                        Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();
                        isItRecording = false;
                    }
                }
                else
                {
                    RequestPermissions();
                }
            }
        });

        playerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(!isItPlaying) {
                    recorderButton.setEnabled(false);
                    mPlayer = new MediaPlayer();
                    try {
                        mPlayer.setDataSource(mFileName);
                        mPlayer.prepare();
                        mPlayer.start();
                        Toast.makeText(getApplicationContext(), "Recording Started Playing", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "prepare() failed");
                    }
                    playerButton.setBackgroundResource(R.drawable.stop_playing);
                    isItPlaying = true;
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

                        // @Override
                        public void onCompletion(MediaPlayer arg0) {
                            // File has ended !!!
                            mPlayer.release();
                            mPlayer = null;
                            recorderButton.setEnabled(true);
                            playerButton.setBackgroundResource(R.drawable.play_audio);
                            isItPlaying = false;
                        }
                    });
                }else{
                    mPlayer.release();
                    mPlayer = null;
                    recorderButton.setEnabled(true);
                    playerButton.setBackgroundResource(R.drawable.play_audio);
                    Toast.makeText(getApplicationContext(),"Playing Audio Stopped", Toast.LENGTH_SHORT).show();
                    isItPlaying = false;
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                uploadFile(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AudioRecording.3gp")));

                String phoneNumber = "0";
                String myBio = "";
                String myDateBio = "";
                String myGender = "";
                String myName = "";
                int myAge = 0;
                String myReligion = "";
                String myLocation = "";
                String myDateLocation = "";
                String myDateReligion = "";
                int myDateStartAge = 0;
                int myDateTopAge = 0;
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                if (sharedPreferences.contains("LoggedInWithPhoneNumber")) {
                    phoneNumber = sharedPreferences.getString("LoggedInWithPhoneNumber", "");
                    SharedPreferences sharedPreferences2 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences2.contains("MyBio")) {
                        myBio = sharedPreferences2.getString("MyBio", "");
                    }
                    SharedPreferences sharedPreferences3 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences3.contains("MyDateBio")) {
                        myDateBio = sharedPreferences3.getString("MyDateBio", "");
                    }
                    SharedPreferences sharedPreferences4 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences4.contains("MyGender")) {
                        myGender = sharedPreferences4.getString("MyGender", "");
                    }
                    SharedPreferences sharedPreferences5 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences5.contains("MyName")) {
                        myName = sharedPreferences5.getString("MyName", "");
                    }
                    SharedPreferences sharedPreferences6 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences6.contains("MyAge")) {
                        myAge = Integer.valueOf(sharedPreferences6.getString("MyAge", ""));
                    }
                    SharedPreferences sharedPreferences7 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences7.contains("MyReligion")) {
                        myReligion = sharedPreferences7.getString("MyReligion", "");
                    }
                    SharedPreferences sharedPreferences8 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences8.contains("MyLocation")) {
                        myLocation = sharedPreferences8.getString("MyLocation", "");
                    }
                    SharedPreferences sharedPreferences9 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences9.contains("MyDateLocation")) {
                        myDateLocation = sharedPreferences9.getString("MyDateLocation", "");
                    }
                    SharedPreferences sharedPreferences10 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences10.contains("MyDateReligion")) {
                        myDateReligion = sharedPreferences10.getString("MyDateReligion", "");
                    }
                    SharedPreferences sharedPreferences11 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences11.contains("MyDateStartAge")) {
                        myDateStartAge = Integer.valueOf(sharedPreferences11.getString("MyDateStartAge", ""));
                    }
                    SharedPreferences sharedPreferences12 = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    if (sharedPreferences12.contains("MyDateTopAge")) {
                        myDateTopAge = Integer.valueOf(sharedPreferences12.getString("MyDateTopAge", ""));
                    }
                }


                //retrofit method call
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(YeFikirKeteroApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                api = retrofit.create(YeFikirKeteroApi.class);

                String parsedPhoneString = phoneNumber.replaceAll("\\D+","");
                long parsedPhone= Long.parseLong(parsedPhoneString);

//                PostUpload postUpload = new PostUpload(parsedPhone, myName, myAge, myBio, "no", 15, 950 );
//                Call<PostUpload> call = api.uploadPost(postUpload);
//                call.enqueue(new Callback<PostUpload>() {
//                    @Override
//                    public void onResponse(Call<PostUpload> call, Response<PostUpload> response) {
//                        Log.d("samuela", "success");
//                        //t1.setText("Success");
//                        //createPerson();
//                    }
//
//                    @Override
//                    public void onFailure(Call<PostUpload> call, Throwable t) {
//                        Log.d("samuela", "fail");
//                        //t1.setText("fail");
//                    }
//                });

                //save that account is logged in
                SharedPreferences sharedPreferences13 = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences13.edit();
                myEdit.putBoolean("LoggedIn", true);
                myEdit.commit();

                Intent intent = new Intent(context, AcknowledgeProfileInput.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadFile(Uri fileUri) {
        // create upload service client
        /*FileUploadService service =
                ServiceGenerator.createService(FileUploadService.class);*/

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);
        if (getContentResolver().getType(fileUri) == null){
            //Toast.makeText(context, "audio upload success", Toast.LENGTH_LONG).show();
        }

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        //MediaType.parse(getContentResolver().getType(fileUri)),
                        MediaType.parse("multipart/form-data"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("audio", file.getName(), requestFile);

        //retrofit method call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YeFikirKeteroApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(YeFikirKeteroApi.class);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        // finally, execute the request
        Call<ResponseBody> call = api.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Toast.makeText(context, "audio upload success", Toast.LENGTH_LONG).show();
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "audio upload error", Toast.LENGTH_LONG).show();
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    private void createPerson(){
        Call<List<Post>> call = api.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("name", "jdkkld");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(RecordAudio.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }
}
