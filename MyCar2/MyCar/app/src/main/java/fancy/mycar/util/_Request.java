package fancy.mycar.util;

/**
 * Created by Administrator on 2015/6/18.
 */
public class _Request {
    String service;
    Object data;

    public _Request(String service, Object data) {
        this.service = service;
        this.data = data;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
