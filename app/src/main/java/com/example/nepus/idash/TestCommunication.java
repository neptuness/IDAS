package com.example.nepus.idash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.Charset;

import ic.kmitl.idas.datacontroller.AbstractDataController;


public class TestCommunication extends Fragment {
    private AbstractDataController abstractDataController = null;
    private EditText resultEditText;
    private EditText cmdEditText;

    public void setDataController(AbstractDataController abstractDataController) {
        this.abstractDataController = abstractDataController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_testcommunication, container, false);


        cmdEditText = (EditText) rootView.findViewById(R.id.cmdEditText);
        Button sendBtn = (Button) rootView.findViewById(R.id.sendBtn);
        resultEditText = (EditText) rootView.findViewById(R.id.resultEt);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmd = cmdEditText.getText().toString();
                byte[] bytes = cmd.getBytes(Charset.forName("UTF-8"));
                if (abstractDataController != null && abstractDataController.isConnected()) {
                    abstractDataController.sendData(bytes);
                    resultEditText.append("Sending: " + cmd);
                }
                else
                    Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }



    public void appendTextResult(String str){
        resultEditText.append(str);
    }
}
