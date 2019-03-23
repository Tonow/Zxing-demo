package com.example.theseus.qrcode;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class SimpleScannerFragment extends Fragment implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
//    public TextView mtxtView = (TextView) mScannerView.findViewById(R.id.textViewOf);
    private String of_content = "N° OF";
    LayoutInflater inflater;
    ViewGroup container;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZBarScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {}
        of_content = rawResult.getContents();
        setOfContent(of_content);
        Toast.makeText(getActivity(), "Contents = " + of_content +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();

//        View view= inflater.inflate(R.layout.fragment_main, container, false);
//
//        TextView mtxtView = (TextView) view.findViewById(R.id.textViewOf);
//        mtxtView.setText(of_content);

////        Test set the value on MainActivityFragment
//        MainActivityFragment mainActivityFragment = new MainActivityFragment();
//        mainActivityFragment.setOf(of_content);
//        mainActivityFragment.onStop();
//        mainActivityFragment.onResume();




        // Note:
        // * Wait 4 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerFragment.this);
            }
        }, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public String getOfContent() {
        return of_content;
    }

    public void setOfContent(String of_content) {
        this.of_content = of_content;
    }
}
