package com.kxky.demo.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kxky on 2017/12/15.
 */

public class ACApi {
    private static final String TAG = ACApi.class.getSimpleName();
    /*  public static final String TSET_IM_ULR = "http://192.168.1.44:8080/imservice/";*/
    private static Map<String, String> headers = new HashMap<>();

    /**
     * 共有方法，用于向Retrofit中注入baseurl和headers
     *
     * @param baseUrl
     * @param token
     * @return
     */
    public static APIService getAPIService(String baseUrl, String token) {
        headers.clear();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        if (token != null) {
            headers.put("Authorization", token);
        }
        APIService apiService = RetrofitService.getInstance().createHttpService(baseUrl, headers, APIService.class);
        return apiService;
    }

 /*   public static String getToken() {
        return AppApplication.getInstance().getLastLoginToken();
    }

    *//**
     * 获取联系人版本号
     *//*
    public static Observable<ContactVersion> getUserLoginData(AppLoginInfo requestbean) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        return apiService.getUserLoginData(requestbean);
    }

    *//**
     * 登录
     *//*
    public static Observable<LoginResult> login(LoginRequest loginRequest) {
        APIService apiService = getAPIService(BaseIMUrl, null);
        return apiService.login(loginRequest);
    }

    *//**
     * 获取用户信息
     *//*
    public static Observable<LoginUser> getUserInfo(String id) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        return apiService.getUserInfo(id);
    }

    *//**
     * 根据手机号码 获取验证码
     *
     * @param mobile
     * @return
     *//*
    public static Observable<JSONObject> getCaptchaCode(String mobile) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        return apiService.byMoblieCaptchaCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    *//**
     * 判断验证码是否过期
     *
     * @param account
     * @param code
     * @param seconds
     * @return
     *//*
    public static Observable<JSONObject> getCaptchaStatus(String account, String code, String seconds) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        return apiService.getCaptchaStatus(account, code, seconds);
    }

    *//**
     * 更改用户信息
     *
     * @param object
     * @return
     *//*
    public static Observable<JSONObject> postUserMsg(JSONObject object) {
        Log.d("XXW", "更细用户信息--" + object.toString());
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        return apiService.postChangePwd(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    *//**
     * 获取showme联系人
     *//*
    public static Observable<ShowmeUsers> getNewInnserUsers(SystemUserRequest systemUserRequest) {
        APIService apiService = getAPIService(BaseIMUrl, null);
        String requests = JSON.toJSONString(systemUserRequest);
        Log.d(TAG, "getNewInnserUsers requests:" + requests);
        return apiService.getNewInnserUsers(systemUserRequest);
    }

    *//**
     * 获取机构联系人
     *//*
    public static Observable<OrgMemberResult> getNewInstitutionUserList(OrgMemberRequest requestbean) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        String requests = JSON.toJSONString(requestbean);
        Log.d(TAG, "getNewInstitutionUserList requests:" + requests);
        return apiService.getNewInstitutionUserList(requestbean);
    }

    public static Observable<JSONObject> getVirtualOrgs(String orgig) {
        String token = getToken();
        APIService apiService = getAPIService(EmServiceUrl, token);
        return apiService.getVirtualOrgs(orgig);
    }

    public static Observable<JSONArray> searchYixiuUser(String text) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", text);
        return apiService.searchYixiuUser(jsonObject);
    }

    public static Observable<JSONObject> getQiNiuToken() {
        String token = getToken();
        APIService apiService = getAPIService(SERVICE_URL, token);
        return apiService.getQiNiuToken();
    }

    public static Observable<JSONObject> dimssTXGroup(String groupid) {
        String usersig = BaseAppConfig.TENXUN_APPADMINSIGN;
        String identifier = BaseAppConfig.TENXUN_APPADMIN;
        int sdkappid = BaseAppConfig.TENXUN_SDK_APPID;
        int random = (new Random()).nextInt(100000000);
        String contenttype = "json";
        APIService apiService = getAPIService("https://console.tim.qq.com/v4/", null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("GroupId", groupid);
        return apiService.dimissTXGroup(usersig, identifier, sdkappid, random, contenttype, jsonObject);
    }

    public static Observable<ResponseBean<Page<EMAidInfo>>> getEMListData(int curpage, int pagesize, int orderStatus, String orgcode) {
        String token = getToken();
        APIService apiService = getAPIService(EmServiceUrl, token);
        return apiService.getEMlistData(curpage, pagesize, orderStatus, orgcode);
    }

    public static Observable<ResponseBean<Page<EMAidInfo>>> getEMHistorylistData(int curpage, int pagesize, int orderStatus, String orgcode, String startTimeQuery, String endTimeQuery, String userName) {
        String token = getToken();
        APIService apiService = getAPIService(EmServiceUrl, token);
        return orderStatus == 1 ? apiService.getEMHistorylistDataByAll(curpage, pagesize, orgcode, startTimeQuery, endTimeQuery, userName) : apiService.getEMHistorylistData(curpage, pagesize, orderStatus, orgcode, startTimeQuery, endTimeQuery, userName);
    }

    public static Observable<JSONObject> modifyYixiuUseImg(String uid, File userimg) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        MultipartBody body = RetrofitService.imgFileBody("file", userimg);
        return apiService.modifyYixiuUseImg(uid, body);
    }
    public static Observable<JSONObject> getHospitalUser(String aimhospitalid){
        String token = getToken();
        APIService apiService = getAPIService(YLTServiceUrl, token);
        return apiService.getHospitalUser(aimhospitalid);
    }
    public static Observable<JSONObject> addGroupMember(String groupid,List<String> loginnamelist){
        String usersig = BaseAppConfig.TENXUN_APPADMINSIGN;
        String identifier = BaseAppConfig.TENXUN_APPADMIN;
        int sdkappid = BaseAppConfig.TENXUN_SDK_APPID;
        int random = (new Random()).nextInt(100000000);
        String contenttype = "json";
        APIService apiService = getAPIService("https://console.tim.qq.com/",null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("GroupId",groupid);
        JSONArray memberlist = new JSONArray();
        for(String loginname:loginnamelist){
            JSONObject member = new JSONObject();
            member.put("Member_Account",loginname);
            memberlist.add(member);
        }
        jsonObject.put("MemberList",memberlist);
        return apiService.addGroupMember(usersig,identifier,sdkappid,random,contenttype,jsonObject);
    }
    *//************************** start:滴滴心电 ********************************************//*
    *//**
     * 查询科室静态报告
     *
     * @param pageNum
     * @param pageSize
     * @param object
     * @return
     *//*
    public static Observable<Page<ReportMongoDTO>> getDeptReportList(int pageNum, int pageSize, JSONObject object) {
        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.getDeptReportList(pageNum, pageSize, object);
    }


    *//**
     * 查询科室动态报告
     *
     * @param pageNum
     * @param pageSize
     * @param object
     * @return
     *//*
    public static Observable<Page<ReportDynamicMongoDTO>> getDeptDynamicReportList(int pageNum, int pageSize, JSONObject object) {
        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.getDeptDynamicReportList(pageNum, pageSize, object);
    }

    *//**
     * 静态审核报告查询——本院、外院
     *
     * @param topCount
     * @param object
     * @return
     *//*
    public static Observable<List<DepartmentReport>> getStaticCheckReportList(int topCount, JSONObject object) {
        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.getStaticCheckReportList(topCount, object);
    }

    *//**
     * 动态审核报告查询——本院、外院
     *
     * @param topCount
     * @param object
     * @return
     *//*
    public static Observable<List<DepartmentDynamicReport>> getDynamicCheckReportList(int topCount, JSONObject object) {
        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.getDynamicCheckReportList(topCount, object);

    }

    *//**
     * 下载文件
     *
     * @param id dtoId
     * @return
     *//*
    public static Observable<ResponseBody> downloadEcgFile(String id) {
        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.downloadEcgFile(id);
    }

    public static Observable<String> getDyPdfUrl(String reportId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "base");
        jsonObject.put("key", reportId + ".pdf");

        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.getDyPdfUrl(jsonObject);
    }
    *//************************** end:  滴滴心电 ********************************************//*
    *//************************** start:多参监护 ********************************************//*
    *//**
     * 根据急救单号请求最新一条多参监护记录
     *
     * @param aidId 急救单号
     * @return
     *//*
    public static Observable<ResponseBean<MonitorRecord>> getLastMonitorRecordByAid(String aidId) {
        APIService apiService = getAPIService(EmServiceUrl, getToken());
        return apiService.getLastMonitorRecordByAid(aidId);
    }

    *//**
     * 急救——查询检查报告列表
     *
     * @param aidId 急救单号
     * @return
     *//*
    public static Observable<ResponseListBean<ExamineItem>> getExaminReports(String aidId) {
        APIService apiService = getAPIService(EmServiceUrl, getToken());
        return apiService.getExaminReports(aidId);
    }

    *//**
     * 急救——根据心电ID查询信息详细信息
     *
     * @param ecgId 心电ID
     * @return
     *//*
    public static Observable<ReportMongoDTO> getReportMongoDtoById(String ecgId) {
        APIService apiService = getAPIService(SERVICE_URL, getToken());
        return apiService.getReportMongoDtoById(ecgId);
    }

    public static Observable<NewNodeRes> getOperationRecordList(JSONObject object) {
        APIService apiService = getAPIService(EmServiceUrl, getToken());
        return apiService.getOperationRecordList(object);
    }

    *//**
     * 根据急救单号获取急救详情
     *
     * @param taskCode
     * @return
     *//*
    public static Observable<JSONObject> getEmAidDetailByTaskCode(String taskCode) {
        APIService apiService = getAPIService(EmServiceUrl, getToken());
        return apiService.getEmAidDetailByTaskCode(taskCode);
    }

    *//************************** end:  多参监护 ********************************************//*
    *//************************** start: 云影像  ********************************************//*

    *//**
     * 易影云影像列表
     *
     * @param params
     * @return
     *//*
    public static Observable<YunImageResult> queryYunImage(Map<String, String> params) {
        APIService apiService = getAPIService(YI_YUN_URL, getToken());
        return apiService.queryYunImage(params);
    }
    *//************************** end:   云影像 ********************************************//*
    *//************************** start: 医联体 ********************************************//*
    *//**
     * 根据orgCode查询医联体信息
     *
     * @param orgCode
     * @return
     *//*
    public static Observable<ResponseListBean<HospitalInfo>> getHospitalByOrgCode(String orgCode) {
        APIService apiService = getAPIService(YLTServiceUrl, getToken());
        return apiService.getHospitalByOrgCode(orgCode);
    }

    *//**
     * 分页查询会诊列表
     *
     * @param params
     * @return
     *//*
    public static Observable<ResponseBean<Page<ConsultationInfo>>> getConsultationsByPage(Map<String, String> params) {
        APIService apiService = getAPIService(YLTServiceUrl, getToken());
        return apiService.getConsultationsByPage(params);
    }

    *//**
     * 分页查询机构签约医院
     *
     * @param params
     * @return
     *//*
    public static Observable<ResponseBean<Page<HospitalInfo>>> getOrgHospitalByPage(Map<String, String> params) {
        APIService apiService = getAPIService(YLTServiceUrl, getToken());
        return apiService.getOrgHospitalByPage(params);
    }

    *//**
     * 根据Id获取医联体信息
     *
     * @param hospitalId
     * @return
     *//*
    public static Observable<JSONObject> getHospitalInfoById(String hospitalId) {
        APIService apiService = getAPIService(YLTServiceUrl, getToken());
        return apiService.getHospitalInfoById(hospitalId);
    }

    *//**
     * 分页获取会诊附件
     *
     * @param params
     * @return
     *//*
    public static Observable<ResponseBean<Page<Attachment>>> getAttachmentListByPage(Map<String, String> params) {
        APIService apiService = getAPIService(YLTServiceUrl, getToken());
        return apiService.getAttachmentListByPage(params);
    }

    *//************************** end:   医联体 ********************************************//*


    public static Observable<YLTImageResult> queryOurYunImage(JSONObject jsonObject, int pagenum, int pagesize) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        return apiService.queryOurYunImage(jsonObject, pagenum, pagesize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    *//******************** 排班 ****************//*

    *//**
     * 获取排班医生
     *
     * @param hospitalID
     * @return
     *//*
    public static Observable<JSONObject> getSchedulesByDay(String day, int hospitalID) {
        String token = getToken();
        APIService apiService = getAPIService(YLTServiceUrl, token);
        return apiService.getSchedulesByDay(hospitalID, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    *//**
     * 新建会诊申请含附件上传
     *
     * @param jsonObject
     * @return
     *//*
    public static Observable<JSONObject> reqConsultsWithAttrachs(JSONObject jsonObject) {
        String token = getToken();
        APIService apiService = getAPIService(YLTServiceUrl, token);
        return apiService.reqConsultsWithAttrach(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    *//**
     * 医联体会诊 结束会诊
     *
     * @param taskcode
     * @return
     *//*
    public static Observable<JSONObject> finishHz(String taskcode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", taskcode);
        jsonObject.put("status", "1");
        String token = getToken();
        APIService apiService = getAPIService(YLTServiceUrl, token);
        return apiService.finishHz(jsonObject);
    }


    *//**
     * 获取机构当天的排版医生
     *
     * @param hospitalID
     * @return
     *//*
    public static Observable<JSONObject> getHospitalSchedulings(int hospitalID) {
        String token = getToken();
        APIService apiService = getAPIService(YLTServiceUrl, token);
        return apiService.getHospitalSchedulings(hospitalID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JSONObject> getConsultationDetail(String taskcode) {
        String token = getToken();
        APIService apiService = getAPIService(YLTServiceUrl, token);
        return apiService.getConsultationDetail(taskcode);
    }


    public static Observable<JSONObject> modifyUserGender(String uid, String gender) {
        return modifyYixiuInfo(uid, null, gender, null, null, null, null, null);
    }

    public static Observable<JSONObject> modifyUserSign(String uid, String sign) {
        return modifyYixiuInfo(uid, null, null, sign, null, null, null, null);
    }

    public static Observable<JSONObject> modifyUserMobile(String uid, String mobile) {
        return modifyYixiuInfo(uid, null, null, null, mobile, null, null, null);
    }

    public static Observable<JSONObject> modifyUserPwd(String uid, String pwd) {
        return modifyYixiuInfo(uid, null, null, null, null, null, null, pwd);
    }

    public static Observable<JSONObject> modifyYixiuInfo(String uid, String usrname, String gender,
                                                         String signature, String mobile, String provinceno, String cityno, String pwd) {
        String token = getToken();
        APIService apiService = getAPIService(BaseIMUrl, token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", uid);
        if (!StringUtils.isEmpty(usrname)) {
            jsonObject.put("username", usrname);
        }
        if (!StringUtils.isEmpty(gender) && ("0".equals(gender) || "1".equals(gender))) {
            jsonObject.put("gender", gender);
        }
        if (!StringUtils.isEmpty(signature)) {
            jsonObject.put("signature", signature);
        }
        if (!StringUtils.isEmpty(mobile)) {
            jsonObject.put("mobile", mobile);
        }

        if (!StringUtils.isEmpty(provinceno) && !StringUtils.isEmpty(cityno)) {
            jsonObject.put("provinceno", provinceno);
            jsonObject.put("cityno", cityno);
        }
        if (!StringUtils.isEmpty(pwd)) {
            jsonObject.put("passwd", pwd);
        }
        return apiService.modifyYixiuInfo(jsonObject);
    }
    public static Observable<JSONObject> modifyGroupInfo(EMGroupInfo jsonObject){
        String usersig = BaseAppConfig.TENXUN_APPADMINSIGN;
        String identifier = BaseAppConfig.TENXUN_APPADMIN;
        int sdkappid = BaseAppConfig.TENXUN_SDK_APPID;
        int random = (new Random()).nextInt(100000000);
        String contenttype = "json";
        APIService apiService = getAPIService("https://console.tim.qq.com/",null);
        return apiService.modifyGroupInfo(usersig,identifier,sdkappid,random,contenttype,jsonObject);
    }
    public static Observable<JSONObject> reportXTDiagnosis(String oderid,String diagnosisType,
                                                           String diagnosisResult,String cpDiagnosisCode){
        String token = getToken();
        APIService apiService = getAPIService(EmServiceUrl,token);
        return apiService.reportXTDiagnosis(oderid,diagnosisType,diagnosisResult,cpDiagnosisCode);
    }
    public static Observable<JSONObject> updateOpenDGTime(String taskcode,String time){
        String token = getToken();
        APIService apiService = getAPIService(EmServiceUrl,token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",taskcode);
        jsonObject.put("startConduitTime",time);
        return apiService.updateEMDetail(jsonObject);
    }
    public static Observable<JSONObject> updatestartDGTime(String taskcode,String time){
        String token = getToken();
        APIService apiService = getAPIService(EmServiceUrl,token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",taskcode);
        jsonObject.put("activateConduitTime",time);
        return apiService.updateEMDetail(jsonObject);
    }
*/

    /**************************start:网络请求接口类，定义每个请求的地址、请求体和返回bean类型********************************************/

    public interface APIService {
      /*  *//**
         * 获取联系人版本号
         *
         * @param requestbean
         * @return
         *//*
        @POST("v1/doctorChat/getUserLoginData")
        Observable<ContactVersion> getUserLoginData(@Body AppLoginInfo requestbean);

        *//**
         * 登录自有服务器，验证账户名和密码
         *
         * @param requestbean
         * @return
         *//*
        @POST("v1/doctorChat/login")
        Observable<LoginResult> login(@Body LoginRequest requestbean);

        *//**
         * 登录自有服务器成功后，获取登录用户资料
         *
         * @param id
         * @return
         *//*
        @GET("v1/doctorChat/getUserInfo/{id}")
        Observable<LoginUser> getUserInfo(@Path("id") String id);

        *//**
         * 通过账号或者手机号码更改手机号
         *
         * @param mobile
         * @return
         *//*
        @GET("v1/doctorChat/sendSmsValidateAccount/{mobile}")
        Observable<JSONObject> byMoblieCaptchaCode(@Path("mobile") String mobile);

        *//**
         * 判断验证码是否过期
         *
         * @param account 手机号
         * @param code    验证码
         * @param seconds 秒
         * @return
         *//*
        @GET("v1/doctorChat/validateCode/{account}/{code}/{seconds}")
        Observable<JSONObject> getCaptchaStatus(@Path("account") String account, @Path("code") String code, @Path("seconds") String seconds);

        *//**
         * 更新用户信息
         *
         * @param object
         * @return
         *//*
        @POST("v1/doctorChat/updateUserInfo")
        Observable<JSONObject> postChangePwd(@Body JSONObject object);

        *//**
         * 增量获取showme联系人
         *
         * @param requestbean
         * @return
         *//*
        @POST("v1/doctorChat/getNewSystemUserList.json")
        Observable<ShowmeUsers> getNewInnserUsers(@Body SystemUserRequest requestbean);

        *//**
         * 增量获取机构人员
         *
         * @param requestbean
         * @return
         *//*
        @POST("v1/doctorChat/getNewInstitutionUserList")
        Observable<OrgMemberResult> getNewInstitutionUserList(@Body OrgMemberRequest requestbean);

        *//**
         * 获取虚拟机构信息
         *
         * @param orgCode
         * @return
         *//*
        @GET("/virtualorgs/{orgCode}")
        Observable<JSONObject> getVirtualOrgs(@Path("orgCode") String orgCode);

        @POST("v1/doctorChat/searchUser")
        Observable<JSONArray> searchYixiuUser(@Body JSONObject jsonObject);

        *//**
         * 获取七牛token
         *
         * @return
         *//*
        @GET("file/getQiNiuToken/common")
        Observable<JSONObject> getQiNiuToken();

        @POST("group_open_http_svc/destroy_group")
        Observable<JSONObject> dimissTXGroup(@Query("usersig") String usersig, @Query("identifier") String identifier,
                                             @Query("sdkappid") int sdkappid, @Query("random") int random, @Query("contenttype") String contenttype,
                                             @Body JSONObject jsonObject);

        *//**
         * 获取正在进行中的急救列表
         *
         * @param curPage
         * @param pageSize
         * @param orderStatus
         * @param orgCode
         * @return
         *//*
        @GET("/aidorders/pages/all")
        Observable<ResponseBean<Page<EMAidInfo>>> getEMlistData(@Query("curPage") int curPage, @Query("pageSize") int pageSize, @Query("orderStatus") int orderStatus,
                                                                @Query("orgCode") String orgCode);

        *//**
         * 获取急救历史列表
         *
         * @param curPage
         * @param pageSize
         * @param orderStatus
         * @param orgCode
         * @param startTimeQuery
         * @param endTimeQuery
         * @return
         *//*
        @GET("/aidorders/pages/all")
        Observable<ResponseBean<Page<EMAidInfo>>> getEMHistorylistData(@Query("curPage") int curPage, @Query("pageSize") int pageSize, @Query("orderStatus") int orderStatus,
                                                                       @Query("orgCode") String orgCode, @Query("startTimeQuery") String startTimeQuery, @Query("endTimeQuery") String endTimeQuery, @Query("userName") String userName);

        *//**
         * 获取全部急救历史列表
         *
         * @param curPage
         * @param pageSize
         * @param orgCode
         * @param startTimeQuery
         * @param endTimeQuery
         * @return
         *//*
        @GET("/aidorders/pages/all")
        Observable<ResponseBean<Page<EMAidInfo>>> getEMHistorylistDataByAll(@Query("curPage") int curPage, @Query("pageSize") int pageSize,
                                                                            @Query("orgCode") String orgCode, @Query("startTimeQuery") String startTimeQuery, @Query("endTimeQuery") String endTimeQuery, @Query("userName") String userName);
        @GET("/aidorders/pages/all")
        Observable<JSONObject> getEMHistorylistData(@Query("curPage") int curPage, @Query("pageSize") int pageSize, @Query("orderStatus") int orderStatus,
                                                    @Query("orgCode") String orgCode, @Query("startTimeQuery") String startTimeQuery, @Query("endTimeQuery") String endTimeQuery);
        *//**
         * 修改用户头像
         *
         * @param uid
         * @param body
         * @return
         *//*
        @POST("v1/doctorChat/uploadAvatar/{uid}")
        Observable<JSONObject> modifyYixiuUseImg(@Path("uid") String uid, @Body MultipartBody body);

        @POST("v1/doctorChat/updateUserInfo")
        Observable<JSONObject> modifyYixiuInfo(@Body JSONObject jsonObject);

        *//**
         * 查询科室静态报告
         *
         * @param pageNum
         * @param pageSize
         * @param object
         * @return
         *//*
        @POST("ecg/report/getOuterReportList")
        Observable<Page<ReportMongoDTO>> getDeptReportList(@Query("pagenum") int pageNum, @Query("pagesize") int pageSize, @Body JSONObject object);

        *//**
         * 查询科室动态报告
         *
         * @param pageNum
         * @param pageSize
         * @param object
         * @return
         *//*
        @POST("dynamicecg/report/getOuterReportList")
        Observable<Page<ReportDynamicMongoDTO>> getDeptDynamicReportList(@Query("pagenum") int pageNum, @Query("pagesize") int pageSize, @Body JSONObject object);

        *//**
         * 静态审核报告查询——本院、外院
         *
         * @param topCount
         * @param object
         * @return
         *//*
        @POST("ecg/report/getOuterDeptReportList/{topCount}")
        Observable<List<DepartmentReport>> getStaticCheckReportList(@Path("topCount") int topCount, @Body JSONObject object);

        *//**
         * 动态审核报告查询——本院、外院
         *
         * @param topCount
         * @param object
         * @return
         *//*
        @POST("dynamicecg/report/getOuterDeptReportList/{topCount}")
        Observable<List<DepartmentDynamicReport>> getDynamicCheckReportList(@Path("topCount") int topCount, @Body JSONObject object);


        *//**
         * 下载心电文件
         *
         * @param id
         * @return
         *//*
        @Streaming
        @GET("ecg/datamsg/bin/{id}")
        Observable<ResponseBody> downloadEcgFile(@Path("id") String id);

        *//**
         * 获取动态心电pdf报告Url
         *
         * @param object
         * @return
         *//*
        @POST("file/getQiNiuDownloadUrl")
        Observable<String> getDyPdfUrl(@Body JSONObject object);


        *//**
         * 根据急救单号查询最新一条监护记录
         *
         * @param aidId 急救单号
         * @return
         *//*
        @GET("/getNewMotorData/{aidId}")
        Observable<ResponseBean<MonitorRecord>> getLastMonitorRecordByAid(@Path("aidId") String aidId);


        *//**
         * 急救——查询检查报告列表
         *
         * @param orderId 急救单号
         * @return
         *//*
        @GET("getSupplementaryExamination/{orderid}")
        Observable<ResponseListBean<ExamineItem>> getExaminReports(@Path("orderid") String orderId);

        *//**
         * 根据心电ID查询心电ReportMongoDto
         *
         * @param ecgId
         * @return
         *//*
        @GET("ecg/report/{ecgid}")
        Observable<ReportMongoDTO> getReportMongoDtoById(@Path("ecgid") String ecgId);

        *//**
         * 获取急救时间轴
         *
         * @param object
         * @return
         *//*
        @POST("getOperationRecordList")
        Observable<NewNodeRes> getOperationRecordList(@Body JSONObject object);

        *//**
         * 获取急救详情
         *
         * @param taskCodde
         * @return
         *//*
        @GET("/carout/aidorders/{taskcode}")
        Observable<JSONObject> getEmAidDetailByTaskCode(@Path("taskcode") String taskCodde);

        *//**
         * 易影云影像列表
         *
         * @param params
         * @return
         *//*
        @GET("acmedcare/api/study.ashx")
        Observable<YunImageResult> queryYunImage(@QueryMap Map<String, String> params);

        *//**
         * 根据orgCode查询医联体信息
         *
         * @param orgCode 机构Id
         * @return
         *//*
        @GET("consults/hospitals/institutions/{orgCode}")
        Observable<ResponseListBean<HospitalInfo>> getHospitalByOrgCode(@Path("orgCode") String orgCode);

        *//**
         * 分页查询会诊记录列表
         *
         * @param params
         * @return
         *//*
        @GET("consults/pages")
        Observable<ResponseBean<Page<ConsultationInfo>>> getConsultationsByPage(@QueryMap Map<String, String> params);

        *//**
         * 云影像自己服务器接口
         *
         * @param jsonObject
         * @param pagenum
         * @param pagesize
         * @return
         *//*
        @POST("v1/medicalImage/getList.json")
        Observable<YLTImageResult> queryOurYunImage(@Body JSONObject jsonObject, @Query("pagenum") int pagenum, @Query("pagesize") int pagesize);


        @GET("/consults/{hospitalId}/schedulings")
        Observable<JSONObject> getSchedulesByDay(@Path("hospitalId") int hospitalId, @Query("specDate") String day);


        @POST("/consultsAndEnclosures")
        Observable<JSONObject> reqConsultsWithAttrach(@Body JSONObject jsonObject);

        @GET("/consults/{hospitalId}/schedulings")
        Observable<JSONObject> getHospitalSchedulings(@Path("hospitalId") int hospitalId);

        *//**
         * 分页查询机构签约医院
         *
         * @param params
         * @return
         *//*
        @GET("consults/hospitals/pages")
        Observable<ResponseBean<Page<HospitalInfo>>> getOrgHospitalByPage(@QueryMap Map<String, String> params);

        *//**
         * 根据Id获取医联体信息
         *
         * @param hospitalId
         * @return
         *//*
        @GET("consults/hospitals/{id}")
        Observable<JSONObject> getHospitalInfoById(@Path("id") String hospitalId);

        *//**
         * 分页获取会诊附件
         *
         * @param params
         * @return
         *//*
        @GET("consults/attachments/pages")
        Observable<ResponseBean<Page<Attachment>>> getAttachmentListByPage(@QueryMap Map<String, String> params);


        @GET("/consults/{id}")
        Observable<JSONObject> getConsultationDetail(@Path("id") String taskcode);


        @PUT("/consults")
        Observable<JSONObject> finishHz(@Body JSONObject jsonObject);

        @GET("/hospitals/users/list")
        Observable<JSONObject> getHospitalUser(@Query("hospitalId") String hospitalId);

        @POST("v4/group_open_http_svc/add_group_member")
        Observable<JSONObject> addGroupMember(@Query("usersig")String usersig, @Query("identifier")String identifier,
                                              @Query("sdkappid")int sdkappid, @Query("random") int random, @Query("contenttype")String contenttype,
                                              @Body JSONObject jsonObject);

        @POST("v4/group_open_http_svc/modify_group_base_info")
        Observable<JSONObject> modifyGroupInfo(@Query("usersig")String usersig, @Query("identifier")String identifier,
                                               @Query("sdkappid")int sdkappid, @Query("random") int random, @Query("contenttype")String contenttype,
                                               @Body EMGroupInfo jsonObject);

        @PUT("aidorders/diagnosis")
        Observable<JSONObject> reportXTDiagnosis(@Query("id") String id,@Query("diagnosisType")
                String diagnosisType,@Query("diagnosisResult") String diagnosisResult,@Query("cpDiagnosisCode")String cpDiagnosisCode);

        @PUT("/aidorders")
        Observable<JSONObject> updateEMDetail(@Body JSONObject jsonObject);
    }*/
        /**************************end:网络请求接口类，定义每个请求的地址、请求体和返回bean类型********************************************/
    }
}