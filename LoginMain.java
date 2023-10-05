package com.example.testapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class LoginMain extends AppCompatActivity {
    Context context ;
    private EditText editText1,editText2,editText3,editText4;
    private ImageView imageView ;
    private Button btn,btn2 ;
    ActivityResultLauncher<Intent> resultLauncher;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
           imageView = findViewById(R.id.imageView4);
           editText1=findViewById(R.id.editTextText);
           editText2=findViewById(R.id.editTextText2);
           editText3=findViewById(R.id.editTextText3);
           editText4=findViewById(R.id.editTextText4);

           btn  = findViewById(R.id.button5);
           btn2 = findViewById(R.id.button6);
           context = getApplicationContext();
           rgister();
           btn.setOnClickListener(view -> pickImage());
           btn2.setOnClickListener(view -> Afficher());

    }
    private void Afficher(){
        context=getApplicationContext();
        if (editText1.getText()!=null && editText2.getText()!=null && editText3.getText()!=null && editText4.getText()!=null){
            Toast.makeText(context,"Connexion reussite",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"Connexion n'est pas reussite",Toast.LENGTH_LONG).show();

        }

    }
    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }
    private void rgister(){
        context = getApplicationContext();
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            imageView.setImageURI(imageUri);
                        }catch (Exception e){
                            Toast.makeText(context,"No Image Selected",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


}
