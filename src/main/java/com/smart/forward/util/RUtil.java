package com.smart.forward.util;

import com.pubinfo.smart.domain.result.R;

public class RUtil {
    public static R<String> error(String data) {
        R<String> result = new R<>();
        result.setSuccess(false);
        result.setData(data);
        result.setMsg("error");
        result.setCode(-1);
        return result;
    }
}
