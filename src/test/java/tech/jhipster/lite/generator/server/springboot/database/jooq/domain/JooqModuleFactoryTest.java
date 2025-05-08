package tech.jhipster.lite.generator.server.springboot.database.jooq.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class JooqModuleFactoryTest {

  private final JooqModuleFactory factory = new JooqModuleFactory();

  @Test
  void shouldBuildPostgreSQLModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildPostgreSQL(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jooq</artifactId>
            </dependency>
        """
      )
      .containing(
        """
              <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>${jooq.version}</version>
                <executions>
                  <execution>
                    <id>jooq-codegen</id>
                    <phase>generate-resources</phase>
                    <goals>
                      <goal>generate</goal>
                    </goals>
                  </execution>
                </executions>
                <configuration>
                  <jdbc>
                    <url>jdbc:postgresql://localhost:5432/myapp</url>
                    <user>myapp</user>
                    <password></password>
                  </jdbc>
                  <generator>
                    <database>
                      <name>org.jooq.meta.postgres.PostgresDatabase</name>
                      <includes>.*</includes>
                      <inputSchema>public</inputSchema>
                    </database>
                    <target>
                      <packageName>org.jooq.codegen</packageName>
                      <directory>target/generated-sources/jooq</directory>
                    </target>
                  </generator>
                </configuration>
              </plugin>
        """
      );
  }

  @Test
  void shouldBuildMariadbModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildMariaDB(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jooq</artifactId>
            </dependency>
        """
      )
      .containing(
        """
              <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>${jooq.version}</version>
                <executions>
                  <execution>
                    <id>jooq-codegen</id>
                    <phase>generate-resources</phase>
                    <goals>
                      <goal>generate</goal>
                    </goals>
                  </execution>
                </executions>
                <configuration>
                  <jdbc>
                    <url>jdbc:mariadb://localhost:3306/myapp</url>
                    <user>root</user>
                    <password></password>
                  </jdbc>
                  <generator>
                    <database>
                      <name>org.jooq.meta.mariadb.MariaDBDatabase</name>
                      <includes>.*</includes>
                      <inputSchema>myapp</inputSchema>
                    </database>
                    <target>
                      <packageName>org.jooq.codegen</packageName>
                      <directory>target/generated-sources/jooq</directory>
                    </target>
                  </generator>
                </configuration>
              </plugin>
        """
      );
  }

  @Test
  void shouldBuildMysqlModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildMySQL(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jooq</artifactId>
            </dependency>
        """
      )
      .containing(
        """
              <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>${jooq.version}</version>
                <executions>
                  <execution>
                    <id>jooq-codegen</id>
                    <phase>generate-resources</phase>
                    <goals>
                      <goal>generate</goal>
                    </goals>
                  </execution>
                </executions>
                <configuration>
                  <jdbc>
                    <url>jdbc:mysql://localhost:3306/myapp</url>
                    <user>root</user>
                    <password></password>
                  </jdbc>
                  <generator>
                    <database>
                      <name>org.jooq.meta.mysql.MySQLDatabase</name>
                      <includes>.*</includes>
                      <inputSchema>myapp</inputSchema>
                    </database>
                    <target>
                      <packageName>org.jooq.codegen</packageName>
                      <directory>target/generated-sources/jooq</directory>
                    </target>
                  </generator>
                </configuration>
              </plugin>
        """
      );
  }

  @Test
  void shouldBuildMssqlModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildMsSQL(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jooq</artifactId>
            </dependency>
        """
      )
      .containing(
        """
              <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>${jooq.version}</version>
                <executions>
                  <execution>
                    <id>jooq-codegen</id>
                    <phase>generate-resources</phase>
                    <goals>
                      <goal>generate</goal>
                    </goals>
                  </execution>
                </executions>
                <configuration>
                  <jdbc>
                    <url>jdbc:sqlserver://localhost:1433;database=myapp;trustServerCertificate=true</url>
                    <user>SA</user>
                    <password>yourStrong(!)Password</password>
                  </jdbc>
                  <generator>
                    <database>
                      <name>org.jooq.meta.sqlserver.SQLServerDatabase</name>
                      <includes>.*</includes>
                      <inputSchema>model</inputSchema>
                    </database>
                    <target>
                      <packageName>org.jooq.codegen</packageName>
                      <directory>target/generated-sources/jooq</directory>
                    </target>
                  </generator>
                </configuration>
              </plugin>
        """
      );
  }
}
