package com.lanadelray.scratch.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by Administrator on 2015/6/8 0008.
 * 存储 认证参数，提供静态方法， 兼有baseApi功能
 */
public class PrivateKey extends BaseApi {
    private static final String TAG = PrivateKey.class.getSimpleName();

    private static final String PREF = "app_key",
            PREF_ID = "id",
            PREF_SECRET = "secret",
            PREF_PKG = "package",
            PREF_REDIRECT = "redirect",
            PREF_SCOPE = "scope";

    private static String sAppId, sAppSecret, sRedirectUri, sPackageName, sScope;

    public static void setPrivateKey(String appId, String appSecret,
                                     String redirectUri, String packageName, String scope) {
        sAppId = appId;
        sAppSecret = appSecret;
        sRedirectUri = redirectUri;
        sPackageName = packageName;
        sScope = scope;
    }

    public static void writeToPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        prefs.edit()
                .putString(PREF_ID, sAppId)
                .putString(PREF_SECRET, sAppSecret)
                .putString(PREF_REDIRECT, sRedirectUri)
                .putString(PREF_SCOPE, sScope)
                .putString(PREF_PKG, sPackageName)
                .commit();


    }

    public static boolean readFromPref(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        String id = prefs.getString(PREF_ID, null);
        String secret = prefs.getString(PREF_SECRET, null);
        String redirectUri = prefs.getString(PREF_REDIRECT, null);
        String scope = prefs.getString(PREF_SCOPE, null);
        String packageName = prefs.getString(PREF_PKG, null);

        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(secret)
                && !TextUtils.isEmpty(redirectUri) && !TextUtils.isEmpty(scope)) {
            setPrivateKey(id, secret, redirectUri, scope, packageName);
            return true;
        } else {
            return false;
        }
    }

    public static String[] getAll(){
        return new String[]{
                sAppId,
                sAppSecret,
                sRedirectUri,
                sScope,
                sPackageName
        };
    }

    public static String getOauthLoginPage(){
//            return Constants.OAUTH2_ACCESS_AUTHORIZE + "?" + "client_id=" + sAppId
//                    + "&response_type=token&redirect_uri=" + sRedirectUri
//                    + "&key_hash=" + sAppSecret + (TextUtils.isEmpty(sPackageName) ? "" : "&packagename=" + sPackageName)
//                    + "&display=mobile" + "&scope=" + sScope;

        return Constants.OAUTH2_ACCESS_AUTHORIZE + "?client_id=211160679&response_type=token&redirect_uri=" +
                "http://oauth.weico.cc&key_hash=63b64d531b98c2dbff2443816f274dd3"+ (TextUtils.isEmpty(sPackageName) ? "" : "&packagename=" + sPackageName)
                   + "&display=mobile&scope=ALL";
    }

    public static boolean isUrlRedirected(String url) {return url.startsWith(sRedirectUri);}


}
