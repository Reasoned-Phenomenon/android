package com.example.mywebview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mywebview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //http://localhost/webServer/ 을 웹뷰에 띄워줌.
        webView = findViewById(R.id.webview);
        webView.loadUrl("http://10.0.2.2/webServer/");

        //자바스크립트 사용 허용
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //java에서 호출할때 "Android"로 호출. 안 넣어주면 Android가 기본값.
//        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.addJavascriptInterface(this, "Android");

        //
        webView.setWebViewClient(new MyWebViewClient());

    }

    //뒤로가기 버튼 누르면 페이지 뒤로 가기.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    //같은 uri면, 새로운 창뜨지 말고, 페이지 내에서 이동.
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //TODO-system out이 안 찍힘.
            System.out.println("1111");
            System.out.println(url);
            //같은 uri에서 움직일 경우 - 안에서 이동
//            if ("webServer".equals(Uri.parse(url).getHost())) {
//                // This is my website, so do not override; let my WebView load the page
//                System.out.println("같은 사이트");
//                return false;
//            }
            //다른 uri로 움직일 경우 - 액티비티를 새로 열어서 다른 페이지 열 것.
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(intent);
            //웹뷰 내에서 페이지 이동.
            view.loadUrl(url);
            return true;
        }
    }

//    public class WebAppInterface {
//        Context mContext;
//
//        /** Instantiate the interface and set the context */
//        WebAppInterface(Context c) {
//            mContext = c;
//        }
//
//        /** Show a toast from the web page */
//        @JavascriptInterface
//        public void showToast(String toast) {
//            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
//        }
//    }

    //자바스크립트에서 toast 사용
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}