package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpVersionTest {
    private static Stream httpVersionProvider() {
        return Stream.of(
                Arguments.of("HTTP/1.1", HttpVersion.HTTP_1_1),
                Arguments.of("HTTP/2.0", HttpVersion.HTTP_2_0),
                Arguments.of("HTTP/2", HttpVersion.HTTP_2_0),
                Arguments.of("HTTP/3.0", HttpVersion.HTTP_3_0),
                Arguments.of("HTTP/3", HttpVersion.HTTP_3_0)
        );
    }

    @DisplayName("텍스트를 HttpVersion으로 변환")
    @ParameterizedTest(name = "입력: {0}")
    @MethodSource("httpVersionProvider")
    void parseHttpVersion(String rawHttp, HttpVersion expected) {
        HttpVersion httpVersion = HttpVersion.of(rawHttp);
        assertThat(httpVersion).isEqualTo(expected);
    }

    @DisplayName("HttpVersion 변환 실패")
    @ParameterizedTest(name = "입력: {0}")
//    @MethodSource("willBeFailedHttpVersionProvider")
    @ValueSource(strings = {"http/1", "HTTP/1.0", "HTTP/4"})
    void willBeFailedParseHttpVersion(String rawHttpVersion) {
        assertThrows(IllegalArgumentException.class, () -> HttpVersion.of(rawHttpVersion));
    }
}
