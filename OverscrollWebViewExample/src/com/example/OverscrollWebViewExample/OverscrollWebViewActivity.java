package com.example.OverscrollWebViewExample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.cossacksolutions.overscrollwebviewlib.OverscrollWebView;

/**
 * @author igor
 *         Date: 08.02.13
 *         Time: 16:43
 */
public class OverscrollWebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overscroll_webview_activity);

        OverscrollWebView webView = (OverscrollWebView) findViewById(R.id.overscroll_web_view);
        webView.loadUrl("http://www.google.com/");
        webView.setSwypeListener(new OverscrollWebView.OnSwypeListener() {
            @Override
            public void onLeftSwype() {
                Toast.makeText(OverscrollWebViewActivity.this, "Left Swype", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightSwype() {
                Toast.makeText(OverscrollWebViewActivity.this, "Right Swype", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
