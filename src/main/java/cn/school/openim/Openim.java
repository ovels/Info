package cn.school.openim;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersDeleteRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersDeleteResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/4/3.
 */
public class Openim {

    private static String url = OpenimCommon.OpenimUrl;
    private static String appkey = OpenimCommon.Appkey;
    private static String secret = OpenimCommon.AppSecret;
    private static String UID = "";


    public void get() {
//        TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);// url参数输入： http://gw.api.taobao.com/router/rest
//        TaeItemDetailGetRequest req=new TaeItemDetailGetRequest();
//        req.setId("AAEkwBGKAAXszj-DOJ-KKVll");
//        req.setFields("itemInfo,priceInfo,skuInfo,stockInfo,rateInfo,descInfo,sellerInfo,mobileDescInfo,deliveryInfo,storeInfo");
//        TaeItemDetailGetResponse response = client.execute(req);
    }

    public static void main(String[] args) throws ApiException {
        String userId = "wangzhennan,zhangjunhui,addtest1";
        JsonObject userJson = null;
        getIMUser(userId);
        addIMUser(userJson);
//        delIMUser();
    }



    public static void getIMUser(String userId) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersGetRequest req = new OpenimUsersGetRequest();
        req.setUserids(userId);
        OpenimUsersGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        Gson gson = new Gson();
        JsonObject userinfos0 = gson.fromJson(rsp.getBody(),JsonObject.class);
        System.out.println(userinfos0);
        JsonObject openim_users_get_response = gson.fromJson(userinfos0,JsonObject.class);
        JsonObject userinfos = gson.fromJson(openim_users_get_response, JsonObject.class);
        JsonArray userinfos11 = gson.fromJson(userinfos, JsonArray.class);
//        Response response = new Gson().fromJson(userinfos11, Response.class);
        UID = gson.fromJson(userinfos11.get(0),String.class);
        System.out.println("getIMUser"+UID);

//        JsonArray userinfos = gson.fromJson(rsp.getBody(),JsonArray.class);
    }

    class Response
    {
        @SerializedName("userinfos")
        JsonArray key;
    }

    public static void addIMUser2DB(){
        Gson gson = new Gson();

    }

    public static void delIMUser(String userId) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersDeleteRequest req = new OpenimUsersDeleteRequest();
        req.setUserids(userId);
        OpenimUsersDeleteResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    public static void updateIMUser(JsonObject userJson) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setNick("king");
        obj3.setIconUrl("http://xxx.com/xxx");
        obj3.setEmail("uid@taobao.com");
        obj3.setMobile("18600000000");
        obj3.setUserid("addtest1");
        obj3.setPassword("xxxxxx");
        obj3.setExtra("{}");
        obj3.setName("demo");
        obj3.setAge(123L);
        obj3.setGender("M");
        req.setUserinfos(list2);
        OpenimUsersUpdateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    public static void addIMUser(JsonObject userJson) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setNick("king");
        obj3.setIconUrl("http://xxx.com/xxx");
        obj3.setEmail("uid@taobao.com");
        obj3.setMobile("18600000000");
        obj3.setUserid("addtest1");
        obj3.setPassword("123456");
        obj3.setExtra("{}");
        obj3.setName("demo");
        obj3.setAge(123L);
        obj3.setGender("M");
        req.setUserinfos(list2);
        OpenimUsersAddResponse rsp = client.execute(req);
        System.out.println("addIMUser"+rsp.getBody());
    }

}