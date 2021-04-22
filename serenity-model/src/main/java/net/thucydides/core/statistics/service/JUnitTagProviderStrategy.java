package net.thucydides.core.statistics.service;


import net.serenitybdd.core.collect.NewSet;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.requirements.PackageRequirementsTagProvider;
import net.thucydides.core.requirements.RequirementsCacheService;
import net.thucydides.core.requirements.RequirementsKey;
import net.thucydides.core.steps.TestSourceType;


public class JUnitTagProviderStrategy implements TagProviderStrategy {

    @Override
    public boolean canHandleTestSource(String testType) {
        return TestSourceType.TEST_SOURCE_JUNIT.getValue().equals(testType);
    }

    @Override
    public Iterable<? extends TagProvider> getTagProviders() {
        return NewSet.of(
                new PackageRequirementsTagProvider(),
                new AnnotationBasedTagProvider(),
                Injectors.getInjector().getInstance(RequirementsCacheService.class).query(RequirementsKey.forDefault()),
                new FeatureStoryTagProvider(),
                new InjectedTagProvider(),
                new ContextTagProvider()
        );
    }

    @Override
    public boolean hasHighPriority() {
        return false;
    }
}
