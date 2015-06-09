package com.lanadelray.scratch.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.lanadelray.scratch.R;
import com.lanadelray.scratch.api.PrivateKey;

/**
 * Created by Administrator on 2015/6/7 0007.
 */
public class LoginActivity extends Activity {//todo extends absactivity

    private final static String TAG = LoginActivity.class.getSimpleName();
    private WebView mWeb;
    //todo private LoginApiCache mLogin;
    private boolean mMulti = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //todo set login_webview should be in toobar
        int mLayout = R.layout.web_login;

        super.onCreate(savedInstanceState);
        //todo to be deleted
        setContentView(mLayout);

        mWeb = (WebView) findViewById(R.id.login_web);


        //todo ismultiuser
        //todo create loginInstance

        //setlogin page webclient
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWeb.setWebViewClient(new MyWebViewClient());


        //todo showAppKeyDialog
        if (false) {

        } else {
            mWeb.loadUrl("about:blank");
            showAppKeyDialog();
        }
    }


    private class MyWebViewClient extends WebViewClient {

    }


    private void showAppKeyDialog() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =inflater.inflate(R.layout.app_key, null);
        final EditText tvId = (EditText) findViewById(R.id.app_id);
        final EditText tvSecret = (EditText) findViewById(R.id.app_secret);
        final EditText tvRedirect = (EditText) findViewById(R.id.redirect_uri);
        final EditText tvScope = (EditText) findViewById(R.id.scope);
        final EditText tvPkg = (EditText) findViewById(R.id.app_pkg);


        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.custom)
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setNeutralButton(R.string.neutralButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = tvId.getText().toString().trim();
                String sec = tvSecret.getText().toString().trim();
                String uri = tvRedirect.getText().toString().trim();
                String scope = tvScope.getText().toString().trim();
                String pkg = tvPkg.getText().toString().trim();

                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(sec)
                        && !TextUtils.isEmpty(uri) && !TextUtils.isEmpty(scope) ){
                    PrivateKey.setPrivateKey(id, sec, uri, scope, pkg);
                    PrivateKey.writeToPref(LoginActivity.this);
                    dialog.dismiss();
                    mWeb.loadUrl(PrivateKey.getOauthLoginPage());
                }

            }
        });



    }
}