package pe.disenio.biblioteca.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class verMiWeb extends WebViewClient {
	@Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
