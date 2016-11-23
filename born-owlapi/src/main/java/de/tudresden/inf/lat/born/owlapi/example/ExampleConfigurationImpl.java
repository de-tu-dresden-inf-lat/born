package de.tudresden.inf.lat.born.owlapi.example;

import java.util.Objects;

import org.semanticweb.owlapi.model.OWLOntology;

/**
 * An object of this class is an example configuration. It is a tuple containing
 * an ontology name, an ontology file name, an OWL ontology, a Bayesian network
 * file name, a Bayesian network, and a query.
 * 
 * @author Julian Mendez
 *
 */
public class ExampleConfigurationImpl implements ExampleConfiguration {

	private final String ontologyName;
	private final String ontologyFileName;
	private final OWLOntology owlOntology;
	private final String bayesianNetworkFileName;
	private final String bayesianNetwork;
	private final String query;

	/**
	 * Constructs a new ontology and Bayesian network object.
	 * 
	 * @param ontologyName
	 *            ontology name
	 * @param ontologyFileName
	 *            ontology file name
	 * @param owlOntology
	 *            OWL ontology
	 * @param bayesianNetworkFileName
	 *            Bayesian network file name
	 * @param bayesianNetwork
	 *            Bayesian network
	 * @param query
	 *            query (in ProbLog syntax)
	 */
	public ExampleConfigurationImpl(String ontologyName, String ontologyFileName, OWLOntology owlOntology,
			String bayesianNetworkFileName, String bayesianNetwork, String query) {
		this.ontologyName = Objects.requireNonNull(ontologyName);
		this.ontologyFileName = Objects.requireNonNull(ontologyFileName);
		this.owlOntology = Objects.requireNonNull(owlOntology);
		this.bayesianNetworkFileName = Objects.requireNonNull(bayesianNetworkFileName);
		this.bayesianNetwork = Objects.requireNonNull(bayesianNetwork);
		this.query = Objects.requireNonNull(query);
	}

	@Override
	public String getOntologyName() {
		return this.ontologyName;
	}

	@Override
	public String getOntologyFileName() {
		return this.ontologyFileName;
	}

	@Override
	public OWLOntology getOntology() {
		return this.owlOntology;
	}

	@Override
	public String getBayesianNetworkFileName() {
		return this.bayesianNetworkFileName;
	}

	@Override
	public String getBayesianNetwork() {
		return this.bayesianNetwork;
	}

	@Override
	public String getQuery() {
		return this.query;
	}

	@Override
	public int hashCode() {
		return this.ontologyName.hashCode() + 0x1F * (this.ontologyFileName.hashCode()
				+ 0x1F * (this.owlOntology.hashCode() + 0x1F * (this.bayesianNetworkFileName.hashCode()
						+ 0x1F * (this.bayesianNetwork.hashCode() + 0x1F * this.query.hashCode()))));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof ExampleConfiguration) {
			ExampleConfiguration other = (ExampleConfiguration) obj;
			return getOntologyName().equals(other.getOntologyName())
					&& getOntologyFileName().equals(other.getOntologyFileName())
					&& getOntology().equals(other.getOntology())
					&& getBayesianNetworkFileName().equals(other.getBayesianNetworkFileName())
					&& getBayesianNetwork().equals(other.getBayesianNetwork()) && getQuery().equals(other.getQuery());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "\nname = " + getOntologyName() + "\nontology file = " + getOntologyFileName()
				+ "\nBayesian network file = " + getBayesianNetworkFileName() + "\n\nBayesian network = "
				+ getBayesianNetwork() + "\n\nontology = " + getOntology() + "\n\nquery = " + getQuery() + "\n\n";
	}

}
