package com.daeseong.naver_test

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var oAuthLogin: OAuthLogin? = null
    private var mContext: Context? = null

    //네이버 아이디로 로그인
    //https://developers.naver.com/apps/#/list
    private val OAUTH_CLIENT_ID = "Client ID"
    private val OAUTH_CLIENT_SECRET = "Client Secret"
    private val OAUTH_CLIENT_NAME = "네이버 아이디로 로그인"


    private var oAuthLoginButton: OAuthLoginButton? = null
    private var buttonOAuth: Button? = null
    private var buttonVerifier:Button? = null
    private var buttonRefresh:Button? = null
    private var buttonOAuthLogout:Button? = null
    private var buttonOAuthDeleteToken:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //네이버 로그인 초기화
        mContext = this
        oAuthLogin = OAuthLogin.getInstance()
        //oAuthLogin!!.showDevelopersLog(true)
        //oAuthLogin!!.showDevelopersLog(true)
        oAuthLogin!!.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME)

        //로그인 정보 업데이트
        updateView()

        //로그인 버튼 설정
        oAuthLoginButton = findViewById<OAuthLoginButton>(R.id.oAuthLoginButton1)
        oAuthLoginButton!!.setOAuthLoginHandler(mOAuthLoginHandler)

        //인증하기
        buttonOAuth = findViewById(R.id.buttonOAuth)
        buttonOAuth!!.setOnClickListener(View.OnClickListener {

            //oAuthLogin!!.startOauthLoginActivity(this, mOAuthLoginHandler)
            OAuthLogin.getInstance().startOauthLoginActivity(this, object : OAuthLoginHandler() {

                override fun run(success: Boolean) {

                    //사용자 정보 가져오기
                    val accessToken = oAuthLogin!!.getAccessToken(mContext)
                    val refreshToken = oAuthLogin!!.getRefreshToken(mContext)
                    val expiresAt = oAuthLogin!!.getExpiresAt(mContext)
                    val tokenType = oAuthLogin!!.getTokenType(mContext)
                    Log.e(tag, "accessToken : $accessToken")
                    Log.e(tag, "refreshToken : $refreshToken")
                    Log.e(tag, "expiresAt : $expiresAt")
                    Log.e(tag, "tokenType : $tokenType")
                    Log.e(tag, "oAuthLogin.getState : " + oAuthLogin!!.getState(mContext).toString())
                }
            })
        })

        //API호출
        buttonVerifier = findViewById(R.id.buttonVerifier)
        buttonVerifier!!.setOnClickListener {

            RequestApiTask().execute()
        }

        //토큰다시받기
        buttonRefresh = findViewById(R.id.buttonRefresh)
        buttonRefresh!!.setOnClickListener {

            RefreshTokenTask().execute()
        }

        //로그아웃
        buttonOAuthLogout = findViewById(R.id.buttonOAuthLogout)
        buttonOAuthLogout!!.setOnClickListener {

            if (oAuthLogin!!.getState(mContext).toString() != "NEED_LOGIN") {
                oAuthLogin!!.logout(mContext)
                updateView()
            }
        }

        //연동끊기
        buttonOAuthDeleteToken = findViewById(R.id.buttonOAuthDeleteToken)
        buttonOAuthDeleteToken!!.setOnClickListener {

            DeleteTokenTask().execute()
        }
    }

    private fun updateView() {

        //사용자 정보 가져오기
        val accessToken = oAuthLogin!!.getAccessToken(mContext)
        val refreshToken = oAuthLogin!!.getRefreshToken(mContext)
        val expiresAt = oAuthLogin!!.getExpiresAt(mContext)
        val tokenType = oAuthLogin!!.getTokenType(mContext)
        Log.e(tag, "accessToken : $accessToken")
        Log.e(tag, "refreshToken : $refreshToken")
        Log.e(tag, "expiresAt : $expiresAt")
        Log.e(tag, "tokenType : $tokenType")
        Log.e(tag, "oAuthLogin.getState : " + oAuthLogin!!.getState(mContext).toString())
    }

    private val mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {

        override fun run(success: Boolean) {

            //로그인 인증 성공
            if (success) {

                //사용자 정보 가져오기
                val accessToken = oAuthLogin!!.getAccessToken(mContext)
                val refreshToken = oAuthLogin!!.getRefreshToken(mContext)
                val expiresAt = oAuthLogin!!.getExpiresAt(mContext)
                val tokenType = oAuthLogin!!.getTokenType(mContext)
                Log.e(tag, "accessToken : $accessToken")
                Log.e(tag, "refreshToken : $refreshToken")
                Log.e(tag, "expiresAt : $expiresAt")
                Log.e(tag, "tokenType : $tokenType")
                Log.e(tag, "oAuthLogin.getState : " + oAuthLogin!!.getState(mContext).toString())
            } else {

                //로그인 인증 실패
                val errorCode = oAuthLogin!!.getLastErrorCode(mContext).code
                val errorDesc = oAuthLogin!!.getLastErrorDesc(mContext)
                Log.e(tag, "errorCode : $errorCode, errorDesc:$errorDesc")
            }
        }
    }

    inner class DeleteTokenTask : AsyncTask<Void?, Void?, Void?>() {

        override fun doInBackground(vararg params: Void?): Void? {
            val isSuccessDeleteToken: Boolean = oAuthLogin!!.logoutAndDeleteToken(mContext)
            if (!isSuccessDeleteToken) {
                // 서버에서 token 삭제에 실패했어도 클라이언트에 있는 token 은 삭제되어 로그아웃된 상태이다
                // 실패했어도 클라이언트 상에 token 정보가 없기 때문에 추가적으로 해줄 수 있는 것은 없음
                Log.d(tag, "errorCode:" + oAuthLogin!!.getLastErrorCode(mContext))
                Log.d(tag, "errorDesc:" + oAuthLogin!!.getLastErrorDesc(mContext))
            }
            return null
        }

        override fun onPostExecute(v: Void?) {
            updateView()
        }
    }

    inner class RequestApiTask : AsyncTask<Void?, Void?, String>() {
        override fun onPreExecute() {
            Log.d(tag, "onPreExecute")
        }

        override fun doInBackground(vararg params: Void?): String? {
            val url = "https://openapi.naver.com/v1/nid/me"
            val at: String = oAuthLogin!!.getAccessToken(mContext)
            return oAuthLogin!!.requestApi(mContext, at, url)
        }

        override fun onPostExecute(content: String) {
            Log.d(tag, "onPostExecute: $content")

            try {
                val jsonObject = JSONObject(content)
                if (jsonObject.getString("resultcode") == "00") {
                    val subjsonObject = JSONObject(jsonObject.getString("response"))
                    val id = subjsonObject.getString("id")
                    val nickname = subjsonObject.getString("nickname")
                    val email = subjsonObject.getString("email")
                    val name = subjsonObject.getString("name")
                    val age = subjsonObject.getString("age")
                    val gender = subjsonObject.getString("gender")
                    val birthday = subjsonObject.getString("birthday")
                    val profile_image = subjsonObject.getString("profile_image")
                    Log.d(tag, "id: $id")
                    Log.d(tag, "nickname: $nickname")
                    Log.d(tag, "email: $email")
                    Log.d(tag, "name: $name")
                    Log.d(tag, "age: $age")
                    Log.d(tag, "gender: $gender")
                    Log.d(tag, "birthday: $birthday")
                    Log.d(tag, "profile_image: $profile_image")
                }
            } catch (ex: Exception) {
                Log.d(tag, "Exception: " + ex.message.toString())
            }
        }
    }

    inner class RefreshTokenTask : AsyncTask<Void?, Void?, String>() {

        override fun doInBackground(vararg params: Void?): String? {
            return oAuthLogin!!.refreshAccessToken(mContext)
        }

        override fun onPostExecute(res: String) {
            updateView()
        }
    }
}
