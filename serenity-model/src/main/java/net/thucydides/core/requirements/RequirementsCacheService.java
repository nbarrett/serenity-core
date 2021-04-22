package net.thucydides.core.requirements;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.serenitybdd.core.time.Stopwatch;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequirementsCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequirementsCacheService.class);

    private final LoadingCache<RequirementsKey, FileSystemRequirementsTagProvider> cache;

    public RequirementsCacheService() {
        cache = CacheBuilder.newBuilder()
                .build(
                        new CacheLoader<RequirementsKey, FileSystemRequirementsTagProvider>() {
                            public FileSystemRequirementsTagProvider load(RequirementsKey requirementsKey) {
                                Stopwatch stopwatch = Stopwatch.started();
                                FileSystemRequirementsTagProvider provider = requirementsKey.equals(RequirementsKey.forDefault()) ?
                                        new FileSystemRequirementsTagProvider() :
                                        new FileSystemRequirementsTagProvider(requirementsKey.topLevelDirectory, requirementsKey.rootDirectory, requirementsKey.level, Injectors.getInjector().getInstance(EnvironmentVariables.class));
                                LOGGER.debug("Cached {} requirements for {} in {}", provider.getRequirements().size(), requirementsKey, stopwatch.lapTimeFormatted());
                                return provider;
                            }
                        });
    }

    public FileSystemRequirementsTagProvider query(RequirementsKey requirementsKey) {
        try {
            return cache.get(requirementsKey);
        } catch (Exception e) {
            String message = String.format("Could not query FileSystemRequirementsTagProvider for %s", requirementsKey);
            throw new CouldNotLoadRequirementsException(message, e);
        }
    }

}
