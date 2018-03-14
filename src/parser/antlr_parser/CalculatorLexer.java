// Generated from Calculator.g4 by ANTLR 4.7.1
package parser.antlr_parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NUMBER=10, IDENTIFIER=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"Digit", "IdentifierStart", "Exponent", "PLusMinus", "NUMBER", "IDENTIFIER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'!'", "'-'", "'+'", "'%'", "'*'", "'/'", "','", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "NUMBER", 
		"IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CalculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\rS\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17A\n\17\3\17\6\17D\n\17"+
		"\r\17\16\17E\5\17H\n\17\5\17J\n\17\3\20\3\20\3\20\7\20O\n\20\f\20\16\20"+
		"R\13\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\2\27\2\31\2"+
		"\33\2\35\f\37\r\3\2\5\5\2C\\aac|\4\2GGgg\4\2--//\2T\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2"+
		"\2\7%\3\2\2\2\t\'\3\2\2\2\13)\3\2\2\2\r+\3\2\2\2\17-\3\2\2\2\21/\3\2\2"+
		"\2\23\61\3\2\2\2\25\63\3\2\2\2\27\65\3\2\2\2\31\67\3\2\2\2\339\3\2\2\2"+
		"\35;\3\2\2\2\37K\3\2\2\2!\"\7#\2\2\"\4\3\2\2\2#$\7/\2\2$\6\3\2\2\2%&\7"+
		"-\2\2&\b\3\2\2\2\'(\7\'\2\2(\n\3\2\2\2)*\7,\2\2*\f\3\2\2\2+,\7\61\2\2"+
		",\16\3\2\2\2-.\7.\2\2.\20\3\2\2\2/\60\7*\2\2\60\22\3\2\2\2\61\62\7+\2"+
		"\2\62\24\3\2\2\2\63\64\4\62;\2\64\26\3\2\2\2\65\66\t\2\2\2\66\30\3\2\2"+
		"\2\678\t\3\2\28\32\3\2\2\29:\t\4\2\2:\34\3\2\2\2;I\5\25\13\2<=\7\60\2"+
		"\2=G\5\25\13\2>@\5\31\r\2?A\5\33\16\2@?\3\2\2\2@A\3\2\2\2AC\3\2\2\2BD"+
		"\5\25\13\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2G>\3\2\2\2"+
		"GH\3\2\2\2HJ\3\2\2\2I<\3\2\2\2IJ\3\2\2\2J\36\3\2\2\2KP\5\27\f\2LO\5\27"+
		"\f\2MO\5\25\13\2NL\3\2\2\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q \3"+
		"\2\2\2RP\3\2\2\2\t\2@EGINP\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}