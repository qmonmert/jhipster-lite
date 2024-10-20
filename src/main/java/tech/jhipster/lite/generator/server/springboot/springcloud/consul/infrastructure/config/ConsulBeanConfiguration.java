package tech.jhipster.lite.generator.server.springboot.springcloud.consul.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain.ConsulDomainService;
import tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain.ConsulService;

@Configuration
public class ConsulBeanConfiguration {

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;

  public ConsulBeanConfiguration(BuildToolService buildToolService, ProjectRepository projectRepository) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
  }

  @Bean
  public ConsulService consulService() {
    return new ConsulDomainService(buildToolService, projectRepository);
  }
}
