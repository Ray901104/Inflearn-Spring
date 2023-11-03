package hello.itemservice.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage() {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("메시지가 없을 경우 예외발생")
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("기본 메시지 설정")
    void notFoundMessageCodeDefaultMessage() {
        String result = messageSource.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    @DisplayName("매개변수 사용")
    void argumentMessage() {
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    @DisplayName("국제화 기본")
    void defaultLang() {
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");

        // 현재 한국어로 설정된 파일이 없기 때문에 디폴트인 messages.properties 로드
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    @DisplayName("국제화 사용")
    void enLang() {
        // 영어로 설정된 파일이 있기 때문에 meesages_en.properties 로드
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
