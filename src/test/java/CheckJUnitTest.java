import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckJUnitTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void checkJUnit() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();   // - Перейдите в раздел Wiki проекта
        $(".js-wiki-more-pages-link").click();   //  - Убедитесь, что в списке страниц (Pages)
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));  // есть страница SoftAssertions
        $("a[href=\"/selenide/selenide/wiki/SoftAssertions\"]").click();  // Откройте страницу SoftAssertions
        $(".markdown-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                        
                """));
    }

}
