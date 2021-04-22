package net.thucydides.core.requirements;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RequirementsKey {
    public final String topLevelDirectory;
    public final String rootDirectory;
    public final int level;

    public RequirementsKey(String topLevelDirectory, String rootDirectory, int level) {
        this.topLevelDirectory = topLevelDirectory;
        this.rootDirectory = rootDirectory;
        this.level = level;
    }

    public static RequirementsKey forRootPath(String rootPath) {
        return new RequirementsKey(rootPath, rootPath,0);
    }

    public static RequirementsKey forDefault() {
        return new RequirementsKey(null, null,0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RequirementsKey that = (RequirementsKey) o;

        return new EqualsBuilder().append(level, that.level).append(topLevelDirectory, that.topLevelDirectory).append(rootDirectory, that.rootDirectory).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(topLevelDirectory).append(rootDirectory).append(level).toHashCode();
    }
}
