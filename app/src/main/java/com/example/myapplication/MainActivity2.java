package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle data = getIntent().getExtras();
        if (data.containsKey("data")){
            String nameOrImage = data.getString("data");
            TextView title = (TextView) findViewById(R.id.textViewDataFromFirstActivity);
            title.setText(nameOrImage);
        }
        else if (data.containsKey("picture")){
            byte[] byteArray = data.getByteArray("picture");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ImageView image = (ImageView) findViewById(R.id.imageView2);
            image.setImageBitmap(bmp);
        }


        Button sendName = (Button) findViewById(R.id.sendDataToFirstActivityButton);
        sendName.setOnClickListener(this::onClick);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.sendDataToFirstActivityButton:
                EditText text = (EditText) findViewById(R.id.editTextSendDataToFirstActivity);
                String data = text.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("dataFromSecondAct", data);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.backButton:
                finish();
                break;
        }
    }
}