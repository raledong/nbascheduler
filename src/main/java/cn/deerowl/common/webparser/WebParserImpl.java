package cn.deerowl.common.webparser;

import cn.deerowl.common.webparser.Action;
import cn.deerowl.common.webparser.Option;
import cn.deerowl.common.webparser.WebParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebParserImpl implements WebParser {

    public Document getDocumentFromUrl(String url, Action action, Option option) throws IOException {
        if (url == null || url.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        switch (action) {
            case POST:
                return post(url, option);
            case GET:
            default:
                return get(url, option);
        }
    }

    @Override
    public Document getDocumentFromUrl(String url, Action action) throws IOException {
        return this.getDocumentFromUrl(url, action, null);
    }

    private Document get(String url, Option option) throws IOException {
        Connection connection = Jsoup.connect(url);
        if (option != null) {
            connection.timeout(option.getTimeout());
            connection.userAgent(option.getUserAgent());
            connection.cookies(option.getCookies());
            connection.data(option.getData());
        }
        return connection.get();
    }

    private Document post(String url, Option option) {
        return null;
    }
}
