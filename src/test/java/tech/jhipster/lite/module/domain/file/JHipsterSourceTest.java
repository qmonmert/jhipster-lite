package tech.jhipster.lite.module.domain.file;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterSourceTest {

  @Test
  void shouldAppendMustacheExtensionWhenReadingTemplate() {
    assertThat(new JHipsterSource(Path.of("src/main/resources")).template("Assert.java").get().toString()).endsWith(".mustache");
  }

  @Test
  void shouldNotAppendMustacheExtensionTwiceWhenReadingTemplate() {
    assertThat(new JHipsterSource(Path.of("src/main/resources")).template("Assert.java.mustache").get().toString()).doesNotEndWith(
      ".mustache.mustache"
    );
  }

  @Test
  void shouldGetTemplateExtension() {
    assertThat(new JHipsterSource(Path.of("src/main/resources")).template("Assert.java.mustache").extension()).isEqualTo(".java");
  }

  @Test
  void shouldGetFileExtension() {
    assertThat(new JHipsterSource(Path.of("src/main/resources")).file("Assert.java").extension()).isEqualTo(".java");
  }

  @Test
  void testToStringShowsPath() {
    assertThat(new JHipsterSource(Path.of("sample"))).hasToString("sample");
  }
}
