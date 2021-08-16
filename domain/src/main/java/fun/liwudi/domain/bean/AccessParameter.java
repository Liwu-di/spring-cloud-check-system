package fun.liwudi.domain.bean;

import java.util.Map;

/**
 * @author 李武第
 */
public class AccessParameter {

    private String url;

    private Map<String,String> parameter;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "AccessParameter{" +
                "url='" + url + '\'' +
                ", parameter=" + parameter +
                '}';
    }
}
