package VolleyService;

import API.ModelCommon;

public interface VolleyResponseListener {
    void onError(String message);
    void onResponse(ModelCommon response);
}
