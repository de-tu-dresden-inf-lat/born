package de.tudresden.inf.lat.born.problog.parser;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.tudresden.inf.lat.born.core.term.Symbol;

/**
 * An object of this class is creator of tokens.
 * 
 * @author Julian Mendez
 *
 */
public class TokenCreator {

	enum Mode {
		UNDEFINED, CODE, CONSTANT, COMMENT
	}

	List<Token> createTokens(String str, int lineNumber) {
		Objects.requireNonNull(str);
		List<Token> ret = new ArrayList<>();
		if (Objects.nonNull(str) && !str.isEmpty()) {
			Mode mode = Mode.CODE;
			StringBuffer token = new StringBuffer();
			int i = 0;
			while (i < str.length()) {
				char ch = str.charAt(i);
				if (Character.isLetterOrDigit(ch) && mode.equals(Mode.CODE)) {
					token.append(ch);
				} else if ((ch == Symbol.APOSTROPHE_CHAR) && mode.equals(Mode.CODE)) {
					ret.add(new TokenImpl(token.toString(), lineNumber));
					token = new StringBuffer();
					token.append(ch);
					mode = Mode.CONSTANT;

				} else if ((ch == Symbol.APOSTROPHE_CHAR) && mode.equals(Mode.CONSTANT)) {
					token.append(ch);
					ret.add(new TokenImpl(token.toString(), lineNumber));
					token = new StringBuffer();
					mode = Mode.CODE;

				} else if ((ch == Symbol.PERCENT_CHAR) && mode.equals(Mode.CODE)) {
					ret.add(new TokenImpl(token.toString(), lineNumber));
					token = new StringBuffer();
					token.append(ch);
					mode = Mode.COMMENT;

				} else if (mode.equals(Mode.CODE)) {
					ret.add(new TokenImpl(token.toString(), lineNumber));
					token = new StringBuffer();
					if ((ch == Symbol.COLON_CHAR) && (i < str.length() - 1)
							&& (str.charAt(i + 1) == Symbol.HYPHEN_CHAR)) {
						ret.add(new TokenImpl(Symbol.IF_SYMBOL, lineNumber));
						i += 1;
					} else {
						ret.add(new TokenImpl("" + ch, lineNumber));
					}

				} else if (mode.equals(Mode.CONSTANT) || mode.equals(Mode.COMMENT)) {
					token.append(ch);

				} else {
					throw new IllegalStateException("Illegal state while parsing '" + str + "' at position " + i
							+ " of line " + lineNumber + ".");
				}

				i += 1;
			}
			if (!token.toString().isEmpty()) {
				ret.add(new TokenImpl(token.toString(), lineNumber));
			}
		}
		return ret;
	}

	List<Token> removeBlanksAndComments(List<Token> tokens) {
		if (Objects.isNull(tokens)) {
			return new ArrayList<>();
		} else {
			return tokens.stream().filter(
					token -> (!token.getType().equals(TokenType.BLANK) && !token.getType().equals(TokenType.COMMENT)))
					.collect(Collectors.toList());
		}
	}

	/**
	 * Returns tokens read from the given reader.
	 * 
	 * @param reader
	 *            reader
	 * @return tokens read from the given reader
	 */
	public List<Token> createTokens(Reader reader) {
		Objects.requireNonNull(reader);
		List<Token> ret = new ArrayList<>();
		BufferedReader in = new BufferedReader(reader);
		int[] lineNumber = new int[1];
		lineNumber[0] = 0;
		in.lines().forEach(line -> {
			lineNumber[0] += 1;
			ret.addAll(removeBlanksAndComments(createTokens(line, lineNumber[0])));
		});
		return ret;
	}

}
