package com.example.theseus.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    Button generate_QRCode;
    ImageView qrCode;
    EditText mEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);
        generate_QRCode=(Button)view.findViewById(R.id.generate_qr);
        qrCode=(ImageView)view.findViewById(R.id.imageView);
        mEditText=(EditText)view.findViewById(R.id.editText);
        generate_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                IntentIntegrator.forFragment(this).initiateScan(); // `this` is the current Fragment
                // If you're using the support library, use IntentIntegrator.forSupportFragment(this) instead.


                String text=mEditText.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_128,400,100);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
