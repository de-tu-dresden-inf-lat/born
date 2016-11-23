package de.tudresden.inf.lat.born.gui;

import java.util.Objects;

import de.tudresden.inf.lat.born.owlapi.annotator.AnnotatorConfiguration;
import de.tudresden.inf.lat.born.owlapi.annotator.AnnotatorConfigurationImpl;
import de.tudresden.inf.lat.born.owlapi.multiprocessor.MultiProcessorConfiguration;
import de.tudresden.inf.lat.born.owlapi.multiprocessor.MultiProcessorConfigurationImpl;
import de.tudresden.inf.lat.born.owlapi.processor.ProcessorConfiguration;
import de.tudresden.inf.lat.born.owlapi.processor.ProcessorConfigurationImpl;
import de.tudresden.inf.lat.jproblog.JProblog;

/**
 * This is the model of the graphical user interface.
 * 
 * @author Julian Mendez
 *
 */
public class BornModelImpl implements BornModel {

	private ProcessorConfiguration processorConfiguration = new ProcessorConfigurationImpl();
	private AnnotatorConfiguration annotatorConfiguration = new AnnotatorConfigurationImpl();
	private MultiProcessorConfiguration multiProcessorConfiguration = new MultiProcessorConfigurationImpl();

	/**
	 * Constructs a new BORN model.
	 */
	public BornModelImpl() {
		JProblog queryProcessor = new JProblog();
		this.processorConfiguration.setQueryProcessor(queryProcessor);
		this.multiProcessorConfiguration.setQueryProcessor(queryProcessor);
	}

	@Override
	public AnnotatorConfiguration getAnnotatorConfiguration() {
		return annotatorConfiguration;
	}

	@Override
	public void setAnnotatorConfiguration(AnnotatorConfiguration annotatorConfiguration) {
		this.annotatorConfiguration = Objects.requireNonNull(annotatorConfiguration);
	}

	@Override
	public ProcessorConfiguration getProcessorConfiguration() {
		return processorConfiguration;
	}

	@Override
	public void setProcessorConfiguration(ProcessorConfiguration processorConfiguration) {
		this.processorConfiguration = Objects.requireNonNull(processorConfiguration);
	}

	@Override
	public MultiProcessorConfiguration getMultiProcessorConfiguration() {
		return multiProcessorConfiguration;
	}

	@Override
	public void setMultiProcessorConfiguration(MultiProcessorConfiguration multiProcessorConfiguration) {
		this.multiProcessorConfiguration = Objects.requireNonNull(multiProcessorConfiguration);
	}

}
