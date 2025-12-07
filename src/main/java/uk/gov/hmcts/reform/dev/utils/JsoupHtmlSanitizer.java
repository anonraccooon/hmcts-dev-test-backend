package uk.gov.hmcts.reform.dev.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class JsoupHtmlSanitizer {

    public static String sanitize(String html) {
        if (html == null) {
            return null;
        }
        // Trim whitespace and remove all HTML tags using Jsoup
        return Jsoup.clean(html.trim(), Safelist.none());
    }

}
