package com.example.mydiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;

    Button btnSave,btnImage;
    EditText editTitle,editContent;
    ImageView imageDiary;

    Uri photoURI;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);

        imageDiary = findViewById(R.id.imageDiary);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        if(intent.hasExtra("id")) {
            editTitle.setText(intent.getStringExtra("title"));
            editContent.setText(intent.getStringExtra("content"));
        }

        btnSave.setOnClickListener(v-> {

            Intent intent1 = new Intent(this,MainActivity.class);

            DiaryVO vo = new DiaryVO();

            vo.setTitle(editTitle.getText().toString());
            vo.setContent(editContent.getText().toString());

            if(intent.hasExtra("id")) {
                String id= intent.getStringExtra("id");
                vo.set_id(id);
                MemoDAO.update(dbHelper,vo);
                intent1.putExtra("msg","수정됐습니다.");

            } else {

                MemoDAO.insert(dbHelper,vo);
                intent1.putExtra("msg","등록됐습니다.");

            }

            startActivity(intent1);

        });


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        System.out.println(takePictureIntent.resolveActivity(getPackageManager()));
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) { //빼면 실행됨.
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); //결과를 가져와서 보여줘야하기때문에 ForResult 사용.
//        }

        File photoFile = null;
        try {
            System.out.println("try");
            photoFile = createImageFile();
        } catch (IOException ex) {
            // Error occurred while creating the File
            System.out.println("에러발생");
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.mydiary",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            System.out.println("들어와짐");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);
//            imageDiary.setImageBitmap(photoURI);
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
