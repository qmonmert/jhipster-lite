package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.shared.error.domain.Assert;

public record NodePackage(NodePackageName name, NodePackageVersion version) {
  public NodePackage(String name, String version) {
    this(new NodePackageName(name), new NodePackageVersion(version));
  }

  public NodePackage(NodePackageName name, String version) {
    this(name, new NodePackageVersion(version));
  }

  public NodePackage {
    Assert.notNull("name", name);
    Assert.notNull("version", version);
  }
}
