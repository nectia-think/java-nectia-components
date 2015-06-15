/**
 * Sistema de Control Casinos
 * (c) 2011-2015 Productos Fernandes
 * 
 * Desarrollo v2.0 Nectia Software
 * Equipo: arquitectura@nectia.com
 */
package com.nectia.think.test.bdd;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;

import java.util.Arrays;

import javax.inject.Inject;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
public class AbstractSpringJBehaveStory extends JUnitStory {
	private static final int STORY_TIMEOUT = 120;

	@Inject
	private ApplicationContext applicationContext;

	public AbstractSpringJBehaveStory() {
		Embedder embedder = new Embedder();
		embedder.useEmbedderControls(embedderControls());
		embedder.useMetaFilters(Arrays.asList("-skip"));
		useEmbedder(embedder);
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.useStoryPathResolver(storyPathResolver())
				.useStoryLoader(storyLoader())
				.useStoryReporterBuilder(storyReporterBuilder())
				.useParameterControls(parameterControls());
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), applicationContext);
	}

	private EmbedderControls embedderControls() {
		return new EmbedderControls().doIgnoreFailureInView(true)
				.useStoryTimeoutInSecs(STORY_TIMEOUT);
	}

	private ParameterControls parameterControls() {
		return new ParameterControls().useDelimiterNamedParameters(true);
	}

	private StoryPathResolver storyPathResolver() {
		return new UnderscoredCamelCaseResolver();
	}

	private StoryLoader storyLoader() {
		return new LoadFromClasspath();
	}

	private StoryReporterBuilder storyReporterBuilder() {
		return new StoryReporterBuilder()
				.withCodeLocation(
						CodeLocations.codeLocationFromClass(this.getClass()))
				.withPathResolver(new ResolveToPackagedName())
				.withFailureTrace(true).withDefaultFormats()
				.withFormats(CONSOLE, TXT, HTML);
	}
}
