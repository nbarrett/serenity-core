package net.thucydides.core.requirements;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringStyle.NO_CLASS_NAME_STYLE;

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
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object rhs) {
        return reflectionEquals(this, rhs);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, NO_CLASS_NAME_STYLE);
    }
}
