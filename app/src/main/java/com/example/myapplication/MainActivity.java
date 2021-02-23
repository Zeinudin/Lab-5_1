package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    String textFromSecondActivity = "Text From Second Activity";
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.unnamed);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageBitmap(bmp);

        Button sendName = (Button) findViewById(R.id.send_name_button);
        sendName.setOnClickListener(this::onClick);

        Button sendLastName = (Button) findViewById(R.id.send_lastname_button);
        sendLastName.setOnClickListener(this::onClick);

        Button sendImage = (Button) findViewById(R.id.send_image_button);
        sendImage.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        Intent intent = new Intent(this, MainActivity2.class);
        String data;
        switch (v.getId()){
            case R.id.send_name_button:
                EditText name = (EditText) findViewById(R.id.editTextTextPersonName);
                data = name.getText().toString();
                intent.putExtra("data", data);
                startActivityForResult(intent, 0);
                break;
            case R.id.send_lastname_button:
                EditText lastname = (EditText) findViewById(R.id.editTextTextPersonLastName);
                data = lastname.getText().toString();
                intent.putExtra("data", data);
                startActivityForResult(intent, 0);
                break;
            case R.id.send_image_button:
                intent.putExtra("picture", byteArray);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            if (resultCode == RESULT_OK){
                String returnString = data.getStringExtra("dataFromSecondAct");

                TextView textView = (TextView) findViewById(R.id.textViewGetFromSecondActivity);
                textView.setText(returnString);
            }
        }
    }
}