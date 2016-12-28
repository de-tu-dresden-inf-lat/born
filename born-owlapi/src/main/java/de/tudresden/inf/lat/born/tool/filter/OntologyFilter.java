package de.tudresden.inf.lat.born.tool.filter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.owlapi.io.OWLRenderer;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.owlxml.renderer.OWLXMLRenderer;

import de.tudresden.inf.lat.born.core.common.ResourceUtil;
import de.tudresden.inf.lat.born.owlapi.processor.ProcessorConfigurationImpl;

/**
 * An object of this class is a tool to filter the EL subset of axioms of a
 * given ontology.
 * 
 * @author Julian Mendez
 *
 */
public class OntologyFilter {

	public static final String HELP = "Parameters: <input ontology> <output ontology>";
	public static final String NEW_ONTOLOGY_SUFFIX = "-filtered";

	/**
	 * Returns the read OWL ontology for the given file name.
	 * 
	 * @param ontologyFileName
	 *            ontology file name
	 * @return the read OWL ontology for the given file name
	 * @throws OWLOntologyCreationException
	 *             if something went wrong with the ontology creation
	 * @throws IOException
	 *             if something went wrong with I/O
	 */
	public OWLOntology readOntology(String ontologyFileName) throws OWLOntologyCreationException, IOException {
		Objects.requireNonNull(ontologyFileName);
		return ProcessorConfigurationImpl.readOntology(new FileInputStream(ontologyFileName));
	}

	/**
	 * Returns an OWL ontology resulting of filtering the given ontology.
	 * 
	 * @param owlOntology
	 *            input OWL ontology
	 * @param filter
	 *            OWL axiom filter
	 * @return an OWL ontology resulting of filtering the given ontology
	 * @throws OWLOntologyCreationException
	 *             if something went wrong with the ontology creation
	 */
	public OWLOntology filterOntology(OWLOntology owlOntology, OwlAxiomFilter filter)
			throws OWLOntologyCreationException {
		Objects.requireNonNull(owlOntology);
		Set<OWLAxiom> newAxioms = owlOntology.getAxioms().stream() //
				.filter(axiom -> filter.accept(axiom)) //
				.collect(Collectors.toSet());
		IRI oldOntologyIri = owlOntology.getOntologyID().getOntologyIRI().get();
		IRI newOntologyIri = IRI.create(oldOntologyIri.toURI().toASCIIString() + NEW_ONTOLOGY_SUFFIX);
		return owlOntology.getOWLOntologyManager().createOntology(newAxioms, newOntologyIri);
	}

	/**
	 * Stores the given ontology.
	 * 
	 * @param owlOntology
	 *            OWL ontology
	 * @param fileName
	 *            file name
	 * @param renderer
	 *            OWL renderer
	 * @throws IOException
	 *             if something went wrong with I/O
	 * @throws OWLException
	 *             if something went wrong serializing the ontology
	 */
	public void storeOntology(OWLOntology owlOntology, String fileName, OWLRenderer renderer)
			throws IOException, OWLException {
		Objects.requireNonNull(owlOntology);
		Objects.requireNonNull(fileName);
		Objects.requireNonNull(renderer);
		FileOutputStream output = new FileOutputStream(ResourceUtil.ensurePath(fileName));
		renderer.render(owlOntology, output);
		output.flush();
		output.close();
	}

	/**
	 * Filters an ontology stored in the given file name with the given filter.
	 * 
	 * @param ontologyFileName
	 *            ontology file name
	 * @param filteredOntologyFileName
	 *            file name of the filtered ontology
	 * @param filter
	 *            filter
	 * @param renderer
	 *            OWL renderer
	 * @throws IOException
	 *             if something went wrong with the I/O
	 * @throws OWLException
	 *             if something went wrong serializing the ontology
	 */
	public void filter(String ontologyFileName, String filteredOntologyFileName, OwlAxiomFilter filter,
			OWLRenderer renderer) throws IOException, OWLException {
		Objects.requireNonNull(ontologyFileName);
		Objects.requireNonNull(filteredOntologyFileName);
		Objects.requireNonNull(renderer);
		storeOntology(filterOntology(readOntology(ontologyFileName), filter), filteredOntologyFileName, renderer);
	}

	/**
	 * This is the entry point to execute the ontology filter.
	 * 
	 * @param args
	 *            arguments
	 * @throws IOException
	 *             if something went wrong with I/O
	 * @throws OWLException
	 *             if something went wrong when using the OWL API
	 */
	public static void main(String[] args) throws IOException, OWLException {
		Objects.requireNonNull(args);
		if (args.length == 2) {
			String ontologyFileName = args[0];
			String outputFileName = args[1];
			OntologyFilter instance = new OntologyFilter();
			instance.filter(ontologyFileName, outputFileName, new ElAxiomFilter(), new OWLXMLRenderer());

		} else {
			System.out.println(HELP);

		}
	}

}
