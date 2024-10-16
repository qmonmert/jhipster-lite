package tech.jhipster.lite.generator.buildtool.maven.domain.maven;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.buildtool.maven.domain.Maven;

@UnitTest
class MavenTest {

  @Test
  void shouldGetParent() {
    // @formatter:off
    String expected =
      """
        <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.5.3</version>
          <relativePath />
        </parent>""";
    // @formatter:on
    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();

    assertThat(Maven.getParent(parent)).isEqualTo(expected);
  }

  @Test
  void shouldGetParentWith4Indentations() {
    // @formatter:off
    String expected =
      """
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.5.3</version>
            <relativePath />
        </parent>""";
    // @formatter:on
    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();

    assertThat(Maven.getParent(parent, 4)).isEqualTo(expected);
  }

  @Test
  void shouldGetDependencyMinimalWith2Indentations() {
    // @formatter:off
    String expected =
      """
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter</artifactId>
        </dependency>""";
    // @formatter:on

    Dependency dependency = minimalDependencyBuilder().build();

    assertThat(Maven.getDependency(dependency, 2)).isEqualTo(expected);
  }

  @Test
  void shouldGetDependencyFullWith2Indentations() {
    // @formatter:off
    String expected =
      """
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter</artifactId>
          <version>2.5.3</version>
          <scope>test</scope>
        </dependency>""";
    // @formatter:on

    Dependency dependency = fullDependencyBuilder().build();

    assertThat(Maven.getDependency(dependency, 2)).isEqualTo(expected);
  }

  @Test
  void shouldGetDependencyFullWith4Indentations() {
    // @formatter:off
    String expected =
      """
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>2.5.3</version>
            <scope>test</scope>
        </dependency>""";
    // @formatter:on

    Dependency dependency = fullDependencyBuilder().build();

    assertThat(Maven.getDependency(dependency, 4)).isEqualTo(expected);
  }

  @Test
  void shouldGetDependencyWithExclusions() {
    // @formatter:off
    String expected =
      """
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
          <exclusions>
            <exclusion>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
          </exclusions>
        </dependency>""";
    // @formatter:on

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
    Dependency dependencyToExclude = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-tomcat")
      .build();

    assertThat(Maven.getDependency(dependency, 2, List.of(dependencyToExclude))).isEqualTo(expected);
  }

  @Test
  void shouldGetExclusionWith2Indentations() {
    // @formatter:off
    String expected =
      """
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>""";
    // @formatter:off

    Dependency dependencyToExclude = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-tomcat")
      .build();

    assertThat(Maven.getExclusion(dependencyToExclude, 2)).isEqualTo(expected);
  }

  @Test
  void shouldGetExclusionsWith2Indentations() {
    // @formatter:off
    String expected =
      """
        <exclusions>
          <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
          </exclusion>
        </exclusions>""";
    // @formatter:off

    Dependency dependencyToExclude = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-tomcat")
      .build();

    assertThat(Maven.getExclusions(List.of(dependencyToExclude), 2)).isEqualTo(expected);
  }

  private Dependency.DependencyBuilder minimalDependencyBuilder() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter");
  }

  private Dependency.DependencyBuilder fullDependencyBuilder() {
    return minimalDependencyBuilder().version("2.5.3").scope("test");
  }

  @Test
  void shouldGetPlugin() {
    String expected = """
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>""";
    Plugin plugin = minimalPluginBuilder().build();

    assertThat(Maven.getPlugin(plugin, 2)).isEqualTo(expected);
  }

  @Test
  void shouldGetPluginWithVersion() {
    String expected = """
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.6.0</version>
      </plugin>""";
    Plugin plugin = fullPluginBuilder().build();

    assertThat(Maven.getPlugin(plugin, 2)).isEqualTo(expected);
  }

  @Test
  void shouldGetPluginWith4Indentations() {
    String expected = """
      <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>""";
    Plugin plugin = minimalPluginBuilder().build();

    assertThat(Maven.getPlugin(plugin, 4)).isEqualTo(expected);
  }

  @Test
  void shouldGetPluginWithAdditionalElements() {
    // @formatter:off
    String expected = """
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.6.0</version>
        <executions>
          <execution>
            <id>default-war</id>
            <goals>
              <goal>war</goal>
            </goals>
            <phase>package</phase>
          </execution>
        </executions>
        <configuration>
          <warSourceIncludes>WEB-INF/**,META-INF/**</warSourceIncludes>
          <failOnMissingWebXml>false</failOnMissingWebXml>
          <warSourceDirectory>target/classes/static/</warSourceDirectory>
          <webResources>
            <resource>
              <directory>src/main/webapp</directory>
              <includes>
                <include>WEB-INF/**</include>
              </includes>
            </resource>
          </webResources>
        </configuration>
      </plugin>""";
    // @formatter:on
    Plugin plugin = fullPluginBuilder()
      .additionalElements(
        """
        <executions>
          <execution>
            <id>default-war</id>
            <goals>
              <goal>war</goal>
            </goals>
            <phase>package</phase>
          </execution>
        </executions>
        <configuration>
          <warSourceIncludes>WEB-INF/**,META-INF/**</warSourceIncludes>
          <failOnMissingWebXml>false</failOnMissingWebXml>
          <warSourceDirectory>target/classes/static/</warSourceDirectory>
          <webResources>
            <resource>
              <directory>src/main/webapp</directory>
              <includes>
                <include>WEB-INF/**</include>
              </includes>
            </resource>
          </webResources>
        </configuration>"""
      )
      .build();

    assertThat(Maven.getPlugin(plugin, 2)).isEqualTo(expected);
  }

  @Test
  void shouldGetProperty() {
    String key = "testcontainers";
    String version = "1.16.0";

    assertThat(Maven.getProperty(key, version)).isEqualTo("<testcontainers.version>1.16.0</testcontainers.version>");
  }

  private Plugin.PluginBuilder minimalPluginBuilder() {
    return Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin");
  }

  private Plugin.PluginBuilder fullPluginBuilder() {
    return minimalPluginBuilder().version("2.6.0");
  }
}
