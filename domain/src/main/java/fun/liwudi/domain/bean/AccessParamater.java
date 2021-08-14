package fun.liwudi.domain.bean;

import java.util.Map;

/**
 * @author 李武第
 */
public class AccessParamater {

    private String url;

    private Map<String,String> paramater;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParamater() {
        return paramater;
    }

    public void setParamater(Map<String, String> paramater) {
        this.paramater = paramater;
    }

    @Override
    public String toString() {
        return "AccessParamater{" +
                "url='" + url + '\'' +
                ", paramater=" + paramater +
                '}';
    }
}
