package tech.jhipster.lite.generator.client.vue.security.jwt.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.security.jwt.domain.VueJwtModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VueJwtApplicationService {

  private final VueJwtModuleFactory vueJwt;

  public VueJwtApplicationService() {
    vueJwt = new VueJwtModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return vueJwt.buildModule(properties);
  }
}
