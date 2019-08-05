package webserver.http;

import java.util.Arrays;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1", "HTTP/1.1"),
    HTTP_2_0("HTTP/2.0", "HTTP/2"),
    HTTP_3_0("HTTP/3.0", "HTTP/3");

    private final String value;
    private final String display;

    HttpVersion(String value, String display) {
        this.value = value;
        this.display = display;
    }

    public static HttpVersion of(String value) {
        return Arrays.stream(HttpVersion.values())
                .filter(item -> item.value.equals(value) || item.display.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("'%s'에 해당하는 HttpVersion 을 찾을 수 없습니다.", value)));
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return display;
    }
}
