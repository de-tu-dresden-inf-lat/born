package de.tudresden.inf.lat.born.problog.connector;

import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author Julian Mendez
 *
 */
public class BayesianNetworkCreatorConfigurationImpl implements BayesianNetworkCreatorConfiguration {

	private List<Integer> dependencies;
	private OutputStream output;

	@Override
	public List<Integer> getDependencies() {
		return dependencies;
	}

	@Override
	public void setDependencies(List<Integer> dependencies) {
		this.dependencies = Objects.requireNonNull(dependencies);
	}

	@Override
	public OutputStream getOutput() {
		return output;
	}

	@Override
	public void setOutput(OutputStream output) {
		this.output = Objects.requireNonNull(output);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof BayesianNetworkCreatorConfiguration)) {
			return false;
		} else {
			BayesianNetworkCreatorConfiguration other = (BayesianNetworkCreatorConfiguration) obj;
			return getDependencies().equals(other.getDependencies()) && getOutput().equals(other.getOutput());
		}
	}

	@Override
	public int hashCode() {
		return this.dependencies.hashCode() + 0x1F * this.output.hashCode();
	}

	@Override
	public String toString() {
		return this.dependencies.toString() + " " + this.output.toString();
	}

}
