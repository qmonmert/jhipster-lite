package tech.jhipster.lite.generator.server.springboot.async.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.async.application.SpringBootAsyncApplicationService;

@RestController
@RequestMapping("/api/servers/spring-boot/async")
@Tag(name = "Spring Boot")
class SpringBootAsyncResource {

  private final SpringBootAsyncApplicationService springBootAsyncApplicationService;

  public SpringBootAsyncResource(SpringBootAsyncApplicationService springBootAsyncApplicationService) {
    this.springBootAsyncApplicationService = springBootAsyncApplicationService;
  }

  @Operation(summary = "Add asynchronous execution and scheduling configuration")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding asynchronous execution and scheduling configuration")
  @PostMapping
  public void init(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    springBootAsyncApplicationService.init(project);
  }
}
