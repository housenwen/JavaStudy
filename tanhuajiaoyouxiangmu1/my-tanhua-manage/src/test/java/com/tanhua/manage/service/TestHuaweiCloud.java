package com.tanhua.manage.service;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

public class TestHuaweiCloud {

    /**
     * 获取token
     */
    @Test
    public void testGetToken() {
        String url = "https://iam.myhuaweicloud.com/v3/auth/tokens";

        String reqBody = JSONUtil.createObj().set("auth", JSONUtil.createObj()
                .set("identity", JSONUtil.createObj()
                        .set("methods", JSONUtil.createArray().set("password"))
                        .set("password", JSONUtil.createObj()
                                .set("user", JSONUtil.createObj()
                                        .set("domain", JSONUtil.createObj().set("name", "hid_yc1nvwdrf-u885o"))
                                        .set("name", "hid_yc1nvwdrf-u885o")
                                        .set("password", "panzhiao21")
                                )
                        )
                )
                .set("scope", JSONUtil.createObj()
                    .set("project",JSONUtil.createObj()
                        .set("name", "cn-east-3")
                    )
                )
        ).toString();

        System.out.println(reqBody);

        String token = HttpRequest.post(url)
                .contentType("application/json;charset=utf8")
                .body(reqBody)
                .execute()
                .header("X-Subject-Token");

        System.out.println(token);

    }
    /**
     * 文本内容审核
     */
    @Test
    public void testTextCheck() {
        String url = "https://moderation.cn-east-3.myhuaweicloud.com/v1.0/moderation/text";
        String token = "MIIVyAYJKoZIhvcNAQcCoIIVuTCCFbUCAQExDTALBglghkgBZQMEAgEwghPaBgkqhkiG9w0BBwGgghPLBIITx3sidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjEtMDUtMDlUMDc6MTM6NTMuOTAxMDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiY2F0YWxvZyI6W10sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJ0ZV9hZ2VuY3kiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2UyLjBfY2ZnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX3Nwb3RfaW5zdGFuY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pdmFzX3Zjcl92Y2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfYXNjZW5kX2thaTEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfa2FlMSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Ric19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19lc3NkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2dwdV92MTAwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NlMi4wX2d2biIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2R3c19wb2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnJfZmlsZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19rYzFfdXNlcl9kZWZpbmVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbWVldGluZ19lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9tYXBfbmxwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc2lzX3Nhc3JfZW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zYWRfYmV0YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NlcnZpY2VzdGFnZV9tZ3JfZHRtX2VuIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfVklTX0ludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfZ3B1X3AycyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c192b2x1bWVfcmVjeWNsZV9iaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kY3NfZGNzMi1lbnRlcnByaXNlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NlMi4wIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdmNwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3ZyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYXBwY3ViZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX211bHRpX2JpbmQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9pb3RzdGFnZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VpcF9wb29sIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcm9tYWV4Y2hhbmdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wcm9qZWN0X2RlbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NoYXJlQmFuZHdpZHRoX3FvcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NjaV9vY2VhbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19yZXR5cGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vbmVhY2Nlc3MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaXIzeCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VsYl9ndWFyYW50ZWVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aHdlc3QtMmIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jaWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zZnN0dXJibyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY19uYXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92cG5fdmd3X2ludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9odl92ZW5kb3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTRlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzZ2IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfSUVDIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGF5dV9kbG1fY2x1c3RlciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2ludGxfY29uZmlndXJhdGlvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NlcnZpY2VzdGFnZV9tZ3JfZHRtIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NwaCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX210ZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfd2tzX2twIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2NpX2t1bnBlbmciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9yaV9kd3MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLXNvdXRod2VzdC0yZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY19mbG93X2xvZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FhZF9iZXRhX2lkYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVwX2FjY2VsZXJhdGlvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19kaXNrQWNjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZHNzX21vbnRoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGVjX21vbnRoX3VzZXIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWZfZWRnZWF1dG9ub215IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdmlwX2JhbmR3aWR0aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29zYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19vbGRfcmVvdXJjZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3dlbGlua2JyaWRnZV9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kY3NfcmkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWYtaW50bCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19hbGciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wc3RuX2VuZHBvaW50X2J1eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21hcF9vY3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbHZfb3Blbl9iZXRhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2R1YWxzdGFjayIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VkY20iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pdnNjcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19jNmEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92cG5fdmd3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc21uX2NhbGxub3RpZnkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbXNfcmVsaWFiaWxpdHkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2VfYXNtX2hrIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19wcm9ncmVzc2JhciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19wb29sX2NhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYmNlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfZGlza180IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXBzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXN0b3JlX2FsbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2wyY2ciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pbnRsX3ZwY19uYXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9mY3NfcGF5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbDJjZ19pbnRsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX3J1LW1vc2Nvdy0xYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfdmdwdV9nNSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX21lc3NhZ2VvdmVyNWciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF90aWNzX29wZW5fYmV0YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19jNyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21hcF92aXNpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfcmkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfcnUtbm9ydGh3ZXN0LTJjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn1dLCJwcm9qZWN0Ijp7ImRvbWFpbiI6eyJuYW1lIjoiaGlkX3ljMW52d2RyZi11ODg1byIsImlkIjoiMGM0ZWEyYTg5NDAwMTBkYzBmNjJjMDA4MmZiOGU1NDAifSwibmFtZSI6ImNuLWVhc3QtMyIsImlkIjoiMGM0ZWEyYThhODAwMTBkYzJmNjVjMDA4N2QyMzdkMDUifSwiaXNzdWVkX2F0IjoiMjAyMS0wNS0wOFQwNzoxMzo1My45MDEwMDBaIiwidXNlciI6eyJkb21haW4iOnsibmFtZSI6ImhpZF95YzFudndkcmYtdTg4NW8iLCJpZCI6IjBjNGVhMmE4OTQwMDEwZGMwZjYyYzAwODJmYjhlNTQwIn0sIm5hbWUiOiJoaWRfeWMxbnZ3ZHJmLXU4ODVvIiwicGFzc3dvcmRfZXhwaXJlc19hdCI6IiIsImlkIjoiMGM0ZWEyYTlhZDgwZjMzMTFmMGRjMDA4Yjg0Y2I2ZmIifX19MYIBwTCCAb0CAQEwgZcwgYkxCzAJBgNVBAYTAkNOMRIwEAYDVQQIDAlHdWFuZ0RvbmcxETAPBgNVBAcMCFNoZW5aaGVuMS4wLAYDVQQKDCVIdWF3ZWkgU29mdHdhcmUgVGVjaG5vbG9naWVzIENvLiwgTHRkMQ4wDAYDVQQLDAVDbG91ZDETMBEGA1UEAwwKY2EuaWFtLnBraQIJANyzK10QYWoQMAsGCWCGSAFlAwQCATANBgkqhkiG9w0BAQEFAASCAQBWHZRsdru1OWON-Qb3KOs7zvlMruZiPgid8ofxGAigeWRezXDvPwUw2LghtAYYgcsi7ZAw89Q2X6MIkMIQwWD7jqogtx2Jijp4Rd4388x-fQtoeLU+X6WnhJCEGAsylhyf50bbi1K2MMTJF8m-xwD4CLoLhYt1oQjP+xlfqxMRB4P4G+7YodcuX7BaRIea-osFZd9u664kIHjwT-bSh3YQsLS9lH8NPGG90tbQN75XuE83IFM9bbL3+s4oH04ZmrZbt9sZVYIhbIye0wsR67IE-GsdChKcEFUNfFzJzBNPBA6iODzYjSQJ0JAfoygoXfG22UMSC3bG75AsQdwCmgPu";

        String reqBody = JSONUtil.createObj()
                .set("categories", "politics,porn,ad,abuse,contraband,flood".split(","))
                .set("items", JSONUtil.createArray()
                        .set(JSONUtil.createObj()
                                .set("text", "dasdsadsadsa")
                                .set("type", "content")
                        )
                ).toString();

        System.out.println(reqBody);

        HttpResponse execute = HttpRequest.post(url)
                .header("X-Auth-Token", token)
                .contentType("application/json;charset=utf8")
                .body(reqBody)
                .execute();
        System.out.println(execute);

       /* System.out.println(resBody);*/

    }
    /**
     * 图片审核
     */
    @Test
    public void testImageCheck() {
        String url = "https://moderation.cn-east-3.myhuaweicloud.com/v1.0/moderation/image/batch";
        String token = "MIIVyAYJKoZIhvcNAQcCoIIVuTCCFbUCAQExDTALBglghkgBZQMEAgEwghPaBgkqhkiG9w0BBwGgghPLBIITx3sidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjEtMDUtMDlUMDc6MTM6NTMuOTAxMDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiY2F0YWxvZyI6W10sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJ0ZV9hZ2VuY3kiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2UyLjBfY2ZnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX3Nwb3RfaW5zdGFuY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pdmFzX3Zjcl92Y2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfYXNjZW5kX2thaTEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfa2FlMSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Ric19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19lc3NkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2dwdV92MTAwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NlMi4wX2d2biIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2R3c19wb2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnJfZmlsZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19rYzFfdXNlcl9kZWZpbmVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbWVldGluZ19lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9tYXBfbmxwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc2lzX3Nhc3JfZW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zYWRfYmV0YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NlcnZpY2VzdGFnZV9tZ3JfZHRtX2VuIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfVklTX0ludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfZ3B1X3AycyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c192b2x1bWVfcmVjeWNsZV9iaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kY3NfZGNzMi1lbnRlcnByaXNlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NlMi4wIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdmNwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3ZyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYXBwY3ViZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX211bHRpX2JpbmQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9pb3RzdGFnZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VpcF9wb29sIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcm9tYWV4Y2hhbmdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wcm9qZWN0X2RlbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NoYXJlQmFuZHdpZHRoX3FvcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NjaV9vY2VhbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19yZXR5cGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vbmVhY2Nlc3MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaXIzeCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VsYl9ndWFyYW50ZWVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aHdlc3QtMmIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jaWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zZnN0dXJibyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY19uYXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92cG5fdmd3X2ludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9odl92ZW5kb3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTRlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzZ2IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfSUVDIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGF5dV9kbG1fY2x1c3RlciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2ludGxfY29uZmlndXJhdGlvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NlcnZpY2VzdGFnZV9tZ3JfZHRtIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NwaCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX210ZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfd2tzX2twIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2NpX2t1bnBlbmciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9yaV9kd3MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLXNvdXRod2VzdC0yZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY19mbG93X2xvZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FhZF9iZXRhX2lkYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVwX2FjY2VsZXJhdGlvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19kaXNrQWNjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZHNzX21vbnRoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGVjX21vbnRoX3VzZXIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWZfZWRnZWF1dG9ub215IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdmlwX2JhbmR3aWR0aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29zYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19vbGRfcmVvdXJjZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3dlbGlua2JyaWRnZV9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kY3NfcmkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWYtaW50bCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19hbGciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wc3RuX2VuZHBvaW50X2J1eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21hcF9vY3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbHZfb3Blbl9iZXRhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2R1YWxzdGFjayIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VkY20iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pdnNjcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19jNmEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92cG5fdmd3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc21uX2NhbGxub3RpZnkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbXNfcmVsaWFiaWxpdHkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2VfYXNtX2hrIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19wcm9ncmVzc2JhciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19wb29sX2NhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYmNlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfZGlza180IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXBzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXN0b3JlX2FsbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2wyY2ciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pbnRsX3ZwY19uYXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9mY3NfcGF5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbDJjZ19pbnRsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX3J1LW1vc2Nvdy0xYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfdmdwdV9nNSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX21lc3NhZ2VvdmVyNWciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF90aWNzX29wZW5fYmV0YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19jNyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21hcF92aXNpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfcmkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfcnUtbm9ydGh3ZXN0LTJjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn1dLCJwcm9qZWN0Ijp7ImRvbWFpbiI6eyJuYW1lIjoiaGlkX3ljMW52d2RyZi11ODg1byIsImlkIjoiMGM0ZWEyYTg5NDAwMTBkYzBmNjJjMDA4MmZiOGU1NDAifSwibmFtZSI6ImNuLWVhc3QtMyIsImlkIjoiMGM0ZWEyYThhODAwMTBkYzJmNjVjMDA4N2QyMzdkMDUifSwiaXNzdWVkX2F0IjoiMjAyMS0wNS0wOFQwNzoxMzo1My45MDEwMDBaIiwidXNlciI6eyJkb21haW4iOnsibmFtZSI6ImhpZF95YzFudndkcmYtdTg4NW8iLCJpZCI6IjBjNGVhMmE4OTQwMDEwZGMwZjYyYzAwODJmYjhlNTQwIn0sIm5hbWUiOiJoaWRfeWMxbnZ3ZHJmLXU4ODVvIiwicGFzc3dvcmRfZXhwaXJlc19hdCI6IiIsImlkIjoiMGM0ZWEyYTlhZDgwZjMzMTFmMGRjMDA4Yjg0Y2I2ZmIifX19MYIBwTCCAb0CAQEwgZcwgYkxCzAJBgNVBAYTAkNOMRIwEAYDVQQIDAlHdWFuZ0RvbmcxETAPBgNVBAcMCFNoZW5aaGVuMS4wLAYDVQQKDCVIdWF3ZWkgU29mdHdhcmUgVGVjaG5vbG9naWVzIENvLiwgTHRkMQ4wDAYDVQQLDAVDbG91ZDETMBEGA1UEAwwKY2EuaWFtLnBraQIJANyzK10QYWoQMAsGCWCGSAFlAwQCATANBgkqhkiG9w0BAQEFAASCAQBWHZRsdru1OWON-Qb3KOs7zvlMruZiPgid8ofxGAigeWRezXDvPwUw2LghtAYYgcsi7ZAw89Q2X6MIkMIQwWD7jqogtx2Jijp4Rd4388x-fQtoeLU+X6WnhJCEGAsylhyf50bbi1K2MMTJF8m-xwD4CLoLhYt1oQjP+xlfqxMRB4P4G+7YodcuX7BaRIea-osFZd9u664kIHjwT-bSh3YQsLS9lH8NPGG90tbQN75XuE83IFM9bbL3+s4oH04ZmrZbt9sZVYIhbIye0wsR67IE-GsdChKcEFUNfFzJzBNPBA6iODzYjSQJ0JAfoygoXfG22UMSC3bG75AsQdwCmgPu";

        String reqBody = JSONUtil.createObj()
                .set("categories", "politics,terrorism,porn".split(","))
                .set("urls", ListUtil.of("https://xiaopanziwudi1.oss-cn-shanghai.aliyuncs.com/54fbb2fb43166d22ca0c87944a2309f79052d2b3.jfif?versionId=CAEQIRiBgICH4f.ryhciIGI2MTUwMWMxNGNlMzRiYTJhMTU4YzkxNTE1MjA2MWZj"))
                .toString();

        System.out.println(reqBody);

        String resBody = HttpRequest.post(url)
                .header("X-Auth-Token", token)
                .contentType("application/json;charset=utf8")
                .body(reqBody)
                .execute()
                .body();

        System.out.println(resBody);

    }

}
