package cn.deerowl.common.webparser;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode
public class Option {

    private String encode;

    private String userAgent;

    private int timeout;

    private Map<String, String> cookies;

    private Map<String, String> data;

}
