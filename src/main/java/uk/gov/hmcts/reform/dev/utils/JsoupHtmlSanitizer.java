package uk.gov.hmcts.reform.dev.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * Sanitizes HTML input to remove whitespace and HTML tags using Jsoup.
 */
public class JsoupHtmlSanitizer {

    public static String sanitize(String html) {
        if (html == null) {
            return null;
        }
        return Jsoup.clean(html.trim(), Safelist.none());
    }

}
