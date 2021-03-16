package org.kuro.campus.utils;

import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
public class SmsListener {
    @Autowired
    private SmsUtils smsUtils;

    public void sendSms(Map<String, String> msg, String tem) throws ClientException {
        if (CollectionUtils.isEmpty(msg)) {
            return;
        }

        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isNoneBlank(phone) && StringUtils.isNoneBlank(code)) {
            this.smsUtils.sendSms(phone, code, "机电二手交易平台", tem);
        }
    }
}
