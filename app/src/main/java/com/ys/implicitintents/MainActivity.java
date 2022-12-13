package com.ys.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocEditText;
    private EditText mShareEditText;
    private Button mWebsiteButton;
    private Button mLocationButton;
    private Button mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocEditText = findViewById(R.id.location_edittext);
        mShareEditText = findViewById(R.id.share_edittext);

        mWebsiteButton = findViewById(R.id.open_website_button);
        mLocationButton = findViewById(R.id.open_location_button);
        mShareButton = findViewById(R.id.share_text_button);


        mWebsiteButton.setOnClickListener(view -> {
            String websiteURL = mWebsiteEditText.getText().toString();
            Uri webpage = Uri.parse(websiteURL);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            try{
                startActivity(intent);
            }
            catch(Exception ActivityNotFoundException){
                Log.d("ImplicitIntents", "Can't handle this!");
                Toast.makeText(this, "Cannot handle this url intent", Toast.LENGTH_SHORT).show();
            }
        });

        mLocationButton.setOnClickListener(view -> {
            String loc = mLocEditText.getText().toString();
            Uri addressUri = Uri.parse("geo:0,0?q="+loc);
            Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
            try {
                startActivity(intent);
            }
            catch (Exception ActivityNotFoundException) {
                Log.d("ImplicitIntents", "Can't handle this!");
                Toast.makeText(this, "Cannot handle this location intent", Toast.LENGTH_SHORT).show();
            }

        });

        mShareButton.setOnClickListener(view -> {
            String txt = mShareEditText.getText().toString();
            String mimeType = "text/plain";

            new ShareCompat.IntentBuilder(this)//view.getContext()
                    .setType(mimeType)
                    .setChooserTitle("Share this text with: ")
                    .setText(txt)
                    .startChooser();
        });

    }
}