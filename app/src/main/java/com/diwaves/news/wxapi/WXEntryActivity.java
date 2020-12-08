
package com.diwaves.news.wxapi;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diwaves.news.bean.WxLoginBean;
import com.diwaves.news.mvp.ui.activity.EditPhoneActivity;
import com.diwaves.news.network.Api;
import com.diwaves.news.network.bean.BaseBean;
import com.diwaves.news.tools.MyToast;
import com.diwaves.news.tools.SPToll;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.diwaves.news.network.Api.APP_ID;
import static com.diwaves.news.network.Api.WX_LOGIN;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI mApi;
    int defaultCode;
    String message, code;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        mApi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        mApi.handleIntent(this.getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
            Toast.makeText(WXEntryActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            //登录回调
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    code = ((SendAuth.Resp) baseResp).code;
                    //获取accesstoken
                    Post post = new Post();
                    post.execute();
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                    finish();
                    break;
                default:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    class Post extends AsyncTask<Void, Integer, String> {
        String result = "";

        @Override
        protected String doInBackground(Void... voids) {
            FormBody formBody = new FormBody.Builder().add("code", code).build();
            Request request = new Request.Builder()
                    .url(Api.BASE_URL + WX_LOGIN)
                    .post(formBody)
                    .build();

            try {
                Response response = okhttpclient().newCall(request).execute();
                defaultCode = response.code();
                message = response.message();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (defaultCode == 200) {
                WxLoginBean bean = new Gson().fromJson(result, WxLoginBean.class);
                String openid = bean.getResult().getOpenId();
                String userId = bean.getResult().getUser_Id();
                if (null == userId || openid.equals("")) {
                    startActivity(new Intent(context, EditPhoneActivity.class).putExtra("openId", openid));
                    finish();
                } else {
                    new SPToll(context).setId(userId);
                }
            }
        }
    }

    private static OkHttpClient okhttpclient() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS)
                .build();
        return mOkHttpClient;
    }
}
