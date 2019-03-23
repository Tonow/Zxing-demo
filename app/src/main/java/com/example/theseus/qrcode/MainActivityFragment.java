package com.example.theseus.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import me.dm7.barcodescanner.zbar.Result;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    Button generate_QRCode;
    ImageView qrCode;
    TextView mEditTextOf;
    EditText mEditTextLetter;
    EditText mEditTextBac;
    EditText mEditText;
    String of;
    private boolean shouldRefreshOnResume = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);
        generate_QRCode=(Button)view.findViewById(R.id.generate_qr);
        qrCode=(ImageView)view.findViewById(R.id.imageView);
        mEditTextOf=(TextView)view.findViewById(R.id.textViewOf);
        mEditTextLetter=(EditText)view.findViewById(R.id.editTextLetter);
        mEditTextBac=(EditText)view.findViewById(R.id.editTextBac);
        generate_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                IntentIntegrator.forFragment(this).initiateScan(); // `this` is the current Fragment
                // If you're using the support library, use IntentIntegrator.forSupportFragment(this) instead.


                String text= mEditTextOf.getText().toString() + "/" +
                        mEditTextLetter.getText().toString() + "/" +
                        mEditTextBac.getText().toString();
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


    @Override
    public void onResume() {
        super.onResume();


        if(shouldRefreshOnResume){
            // refresh fragment
            TextView mtxtView = (TextView) getView().findViewById(R.id.textViewOf);

            Toast.makeText(getActivity(), "Contents result = " + getOf(), Toast.LENGTH_SHORT).show();

            mtxtView.setText(getOf());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    public String getOf() {
        return of;
    }

    public void setOf(String of_content) {
        this.of = of_content;
    }

}
