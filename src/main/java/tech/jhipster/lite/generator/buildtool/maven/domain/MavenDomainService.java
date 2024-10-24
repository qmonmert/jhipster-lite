package tech.jhipster.lite.generator.buildtool.maven.domain;

import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.common.domain.WordUtils.*;
import static tech.jhipster.lite.generator.buildtool.maven.domain.Maven.*;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import java.util.List;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class MavenDomainService implements MavenService {

  public static final String SOURCE = "buildtool/maven";

  private final ProjectRepository projectRepository;

  public MavenDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addParent(Project project, Parent parent) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(DEFAULT_INDENTATION);

    String parentHeaderNode = Maven.getParentHeader(parent, indent).indent(indent);
    String parentHeaderRegexp = (REGEXP_PREFIX_MULTILINE + parentHeaderNode);

    if (!projectRepository.containsRegexp(project, "", POM_XML, parentHeaderRegexp)) {
      String newParentNode = Maven.getParent(parent, indent).indent(indent);
      projectRepository.replaceText(project, "", POM_XML, REGEXP_SPACE_STAR + NEEDLE_PARENT + LF, newParentNode);
    }
  }

  @Override
  public void addDependency(Project project, Dependency dependency) {
    addDependency(project, dependency, null);
  }

  @Override
  public void addDependency(Project project, Dependency dependency, List<Dependency> exclusions) {
    addDependency(
      project,
      dependency,
      exclusions,
      dependency.getScope().filter(s -> s.equals("test")).map(s -> NEEDLE_DEPENDENCY_TEST).orElse(NEEDLE_DEPENDENCY)
    );
  }

  @Override
  public void addDependencyManagement(Project project, Dependency dependency) {
    addDependency(project, dependency, null, NEEDLE_DEPENDENCY_MANAGEMENT);
  }

  private void addDependency(Project project, Dependency dependency, List<Dependency> exclusions, String needle) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(DEFAULT_INDENTATION);

    int level = NEEDLE_DEPENDENCY_MANAGEMENT.equals(needle) ? 3 : 2;

    String dependencyNode = Maven.getDependencyHeader(dependency, indent).indent(2 * indent);
    String dependencyRegexp = (REGEXP_PREFIX_MULTILINE + dependencyNode);

    if (!projectRepository.containsRegexp(project, "", POM_XML, dependencyRegexp)) {
      String newDependencyNode = Maven.getDependency(dependency, indent, exclusions).indent(level * indent);
      String dependencyWithNeedle = (newDependencyNode + indent(level, indent) + needle);

      projectRepository.replaceText(project, "", POM_XML, REGEXP_SPACE_STAR + needle, dependencyWithNeedle);
    }
  }

  @Override
  public void deleteDependency(Project project, Dependency dependency) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(DEFAULT_INDENTATION);

    Dependency dependencyToDelete = Dependency.builder().groupId(dependency.getGroupId()).artifactId(dependency.getArtifactId()).build();

    String dependencyNode = Maven.getDependency(dependencyToDelete, indent).indent(2 * indent);
    String endNode = indent(2, indent) + "</dependency>";
    String dependencyNodeRegExp = ("(?s)" + dependencyNode.replace(endNode, ".*" + endNode));

    projectRepository.replaceRegexp(project, "", POM_XML, dependencyNodeRegExp, "");
  }

  @Override
  public void addPlugin(Project project, Plugin plugin) {
    addPlugin(project, plugin, NEEDLE_PLUGIN);
  }

  @Override
  public void addPluginManagement(Project project, Plugin plugin) {
    addPlugin(project, plugin, NEEDLE_PLUGIN_MANAGEMENT);
  }

  private void addPlugin(Project project, Plugin plugin, String needle) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(DEFAULT_INDENTATION);

    int level = NEEDLE_PLUGIN_MANAGEMENT.equals(needle) ? 4 : 3;

    String pluginNode = Maven.getPluginHeader(plugin, indent).indent(level * indent);

    String pluginRegexp;
    if (NEEDLE_PLUGIN_MANAGEMENT.equals(needle)) {
      //Checking for plugin declaration in <pluginManagement> section
      pluginRegexp =
        (
          FileUtils.REGEXP_PREFIX_DOTALL +
          PLUGIN_MANAGEMENT_BEGIN +
          FileUtils.REGEXP_DOT_STAR +
          pluginNode +
          FileUtils.REGEXP_DOT_STAR +
          PLUGIN_MANAGEMENT_END
        );
    } else {
      //Checking for plugin declaration between <plugin> section and needle (not </plugin> as it can conflict with <plugin> section in <pluginManagement>)
      pluginRegexp =
        FileUtils.REGEXP_PREFIX_DOTALL + PLUGIN_BEGIN + FileUtils.REGEXP_DOT_STAR + pluginNode + FileUtils.REGEXP_DOT_STAR + NEEDLE_PLUGIN;
    }

    if (!projectRepository.containsRegexp(project, "", POM_XML, pluginRegexp)) {
      String newPluginNode = Maven.getPlugin(plugin, indent).indent(level * indent);
      String pluginWithNeedle = (newPluginNode + indent(level, indent) + needle);

      projectRepository.replaceText(project, "", POM_XML, REGEXP_SPACE_STAR + needle, pluginWithNeedle);
    }
  }

  @Override
  public void addProperty(Project project, String key, String version) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(DEFAULT_INDENTATION);

    String pluginRegexp = Maven.getProperty(key, ".*");

    if (!projectRepository.containsRegexp(project, "", POM_XML, pluginRegexp)) {
      String propertyWithNeedle = Maven.getProperty(key, version) + LF + indent(2, indent) + NEEDLE_PROPERTIES;

      projectRepository.replaceText(project, "", POM_XML, NEEDLE_PROPERTIES, propertyWithNeedle);
    }
  }

  @Override
  public void deleteProperty(Project project, String key) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    String propertyNode = Maven.getProperty(key, ".*") + LF;

    projectRepository.replaceText(project, "", POM_XML, propertyNode, "");
  }

  @Override
  public void init(Project project) {
    addPomXml(project);
    addMavenWrapper(project);
  }

  @Override
  public void addPomXml(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(PROJECT_NAME);
    project.addDefaultConfig(BASE_NAME);

    String baseName = project.getBaseName().orElse("");
    project.addConfig("dasherizedBaseName", WordUtils.kebabCase(baseName));

    projectRepository.template(project, SOURCE, POM_XML);
  }

  @Override
  public void addMavenWrapper(Project project) {
    projectRepository.add(project, SOURCE, "mvnw");
    projectRepository.setExecutable(project, "", "mvnw");

    projectRepository.add(project, SOURCE, "mvnw.cmd");
    projectRepository.setExecutable(project, "", "mvnw.cmd");

    String sourceWrapper = getPath(SOURCE, ".mvn", "wrapper");
    String destinationWrapper = getPath(".mvn", "wrapper");

    projectRepository.add(
      project,
      sourceWrapper,
      "MavenWrapperDownloader.java.mustache",
      destinationWrapper,
      "MavenWrapperDownloader.java"
    );
    projectRepository.add(project, sourceWrapper, "maven-wrapper.jar", destinationWrapper);
    projectRepository.add(project, sourceWrapper, "maven-wrapper.properties", destinationWrapper);
  }
}
