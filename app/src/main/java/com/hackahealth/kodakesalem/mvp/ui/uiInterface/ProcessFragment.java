package com.hackahealth.kodakesalem.mvp.ui.uiInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.util.ServerConfig;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by siavash on 5/20/16.
 */
public class ProcessFragment extends Fragment {

    @Bind(R.id.form_view)
    WebView formView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.fragment_form, container, false);
        ButterKnife.bind(this,v);
        formView.loadUrl(ServerConfig.REST_API_BASE_URL+ServerConfig.PROCESS_API_URL);

        // Enable Javascript

        WebSettings webSettings = formView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        formView.setWebViewClient(new WebViewClient());

        return v;
    }
    public static ProcessFragment newInstance(){
        return new ProcessFragment();
    }
}
