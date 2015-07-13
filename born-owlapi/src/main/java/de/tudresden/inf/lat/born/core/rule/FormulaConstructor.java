package de.tudresden.inf.lat.born.core.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tudresden.inf.lat.born.core.term.Clause;
import de.tudresden.inf.lat.born.core.term.ClauseImpl;
import de.tudresden.inf.lat.born.core.term.Term;
import de.tudresden.inf.lat.born.core.term.TermImpl;

/**
 * An object that implements this class is a formula constructor.
 * 
 * @author Julian Mendez
 *
 */
public class FormulaConstructor {

	public static final String TOP = "top";
	public static final String CON = "con";
	public static final String ROLE = "role";
	public static final String SUB = "sub";
	public static final String SUBS = "subs";
	public static final String EXISTS = "exists";
	public static final String AND = "and";
	public static final String QUERY = "query";

	/**
	 * Constructs a new formula constructor.
	 */
	public FormulaConstructor() {
	}

	/**
	 * Returns a new constant.
	 * 
	 * @param name
	 *            name
	 * @return new constant
	 */
	public Term newCons(String name) {
		List<Term> emptyList = Collections.emptyList();
		return new TermImpl(name, emptyList);
	}

	/**
	 * Returns a new variable.
	 * 
	 * @param name
	 *            name
	 * @return a new variable
	 */

	public Term newVar(String name) {
		Term ret = new TermImpl(name);
		if (!(ret.getType().equals(Term.Type.VARIABLE))) {
			throw new IllegalArgumentException(
					"Invalid variable name: '"
							+ name
							+ "'. A variable name must start with a capital letter or with an underscore ('_').");
		}
		return ret;
	}

	/**
	 * Returns the result of applying the functor to one parameter.
	 * 
	 * @param name
	 *            functor name
	 * @param term
	 *            parameter
	 * @return the result of applying the functor to one parameter
	 */
	public Term fun(String name, Term term) {
		List<Term> arguments = new ArrayList<Term>();
		arguments.add(term);
		return new TermImpl(name, arguments);
	}

	/**
	 * Returns the result of applying the functor to two parameters.
	 * 
	 * @param name
	 *            functor name
	 * @param left
	 *            first parameter
	 * @param right
	 *            second parameter
	 * @return the result of applying the functor to two parameters
	 */
	public Term fun(String name, Term left, Term right) {
		List<Term> arguments = new ArrayList<Term>();
		arguments.add(left);
		arguments.add(right);
		return new TermImpl(name, arguments);
	}

	/**
	 * Returns the 'top' constant.
	 * 
	 * @return the 'top' constant
	 */
	public Term top() {
		return newCons(TOP);
	}

	/**
	 * Returns the result of declaring a term as a concept (compare to a class).
	 * 
	 * @param clss
	 *            the term
	 * @return the result of declaring a term as a concept
	 */
	public Term con(Term clss) {
		return fun(CON, clss);
	}

	/**
	 * Returns the result of declaring a term as a role (compare to an object
	 * property).
	 * 
	 * @param clss
	 *            the term
	 * @return the result of declaring a term as a role (compare to an object
	 *         property)
	 */
	public Term role(Term clss) {
		return fun(ROLE, clss);
	}

	/**
	 * Returns the axiom denoting subsumption.
	 * 
	 * @param subClass
	 *            sub class
	 * @param superClass
	 *            super class
	 * @return the axiom denoting subsumption
	 */
	public Term sub(Term subClass, Term superClass) {
		return fun(SUB, subClass, superClass);
	}

	/**
	 * Returns the axiom denoting subsumption.
	 * 
	 * @param subClass
	 *            sub class
	 * @param superClass
	 *            super class
	 * @return the axiom denoting subsumption
	 */
	public Term subs(Term subClass, Term superClass) {
		return fun(SUBS, subClass, superClass);
	}

	/**
	 * Returns the construction of the intersection of two concepts (or
	 * classses).
	 * 
	 * @param left
	 *            first term
	 * @param right
	 *            second term
	 * @return the construction of the intersection of two concepts (or
	 *         classses).
	 * 
	 */
	public Term and(Term left, Term right) {
		return fun(AND, left, right);
	}

	public Term exists(Term property, Term clss) {
		return fun(EXISTS, property, clss);
	}

	public Clause rule(Term head, List<Term> body) {
		return new ClauseImpl(head, body);
	}

	public Clause query(Term query) {
		List<Term> list = new ArrayList<Term>();
		list.add(query);
		TermImpl term = new TermImpl(QUERY, list);
		List<Term> emptyList = Collections.emptyList();
		return new ClauseImpl(term, emptyList);
	}

}
