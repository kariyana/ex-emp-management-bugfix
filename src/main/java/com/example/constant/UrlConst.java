package com.example.constant;

/**
 * URL 定数クラス
 */
public class UrlConst {
  
    /*ログイン画面 */
    public static final String LOGIN = "/login";
    /*ユーザー登録画面 */
    public static final String SIGNUP = "/toInsert";
    /*従業員一覧画面 */
    public static final String SHOWLIST = "/employees";
    /*認証扶養画面 */
    public static final String[]NO_AUTHENTICATION = {SIGNUP,LOGIN,"/img/**","/css/**"};
}
