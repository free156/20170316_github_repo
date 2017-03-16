package com.j1.handler;

import java.io.Serializable;

/**
 * Created by che2 on 2016/9/12.
 */
public class Entity implements Serializable {
    private String errorCode;
    private String resultDesc;
    private String resultData;
    private String callbackParms;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public String getCallbackParms() {
        return callbackParms;
    }

    public void setCallbackParms(String callbackParms) {
        this.callbackParms = callbackParms;
    }
}
