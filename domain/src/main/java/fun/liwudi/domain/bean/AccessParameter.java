package fun.liwudi.domain.bean;

import java.util.Map;

/**
 * @author 李武第
 */
public class AccessParameter {

    /**
     * 路径参数直接在url中构造好
     */
    private String url;

    /**
     * 这里默认是请求头（get），请求体参数（POST）
     */
    private Map<String,String> parameter;

    /**
     * header参数，一般用来鉴权
     */
    private Map<String,String> headerParameter;

    private Object bodyParameter;

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

    public Map<String, String> getHeaderParameter() {
        return headerParameter;
    }

    public void setHeaderParameter(Map<String, String> headerParameter) {
        this.headerParameter = headerParameter;
    }

    public Object getBodyParameter() {
        return bodyParameter;
    }

    public void setBodyParameter(Object bodyParameter) {
        this.bodyParameter = bodyParameter;
    }

    @Override
    public String toString() {
        return "AccessParameter{" +
                "url='" + url + '\'' +
                ", parameter=" + parameter +
                ", headerParameter=" + headerParameter +
                ", bodyParameter=" + bodyParameter +
                '}';
    }
}
