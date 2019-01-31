package cn.deerowl.common.webparser;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface WebParser {

    Document getDocumentFromUrl(String url, Action action, Option option) throws IOException;

    Document getDocumentFromUrl(String url, Action action) throws IOException;
}
