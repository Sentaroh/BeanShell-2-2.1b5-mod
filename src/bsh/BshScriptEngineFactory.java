package bsh;

import javax.script.ScriptEngine;
import java.util.List;
import java.util.Arrays;

// 	Adopted from http://ikayzo.org/svn/beanshell/BeanShell/engine/src/bsh/engine/BshScriptEngineFactory.java
public class BshScriptEngineFactory implements javax.script.ScriptEngineFactory {
	// Begin impl ScriptEnginInfo

	final List<String> extensions = Arrays.asList("bsh", "java");

	final List<String> mimeTypes = Arrays.asList("application/x-beanshell", "application/x-bsh", "application/x-java-source");

	final List<String> names = Arrays.asList("beanshell", "bsh", "java");


	public String getEngineName() {
		return "BeanShell Engine";
	}


	public String getEngineVersion() {
		return Interpreter.VERSION;
	}


	public List<String> getExtensions() {
		return extensions;
	}


	public List<String> getMimeTypes() {
		return mimeTypes;
	}


	public List<String> getNames() {
		return names;
	}


	public String getLanguageName() {
		return "BeanShell";
	}


	public String getLanguageVersion() {
		return bsh.Interpreter.VERSION + "";
	}


	public Object getParameter(String param) {
		if (param.equals(ScriptEngine.ENGINE)) {
			return getEngineName();
		}
		if (param.equals(ScriptEngine.ENGINE_VERSION)) {
			return getEngineVersion();
		}
		if (param.equals(ScriptEngine.NAME)) {
			return getEngineName();
		}
		if (param.equals(ScriptEngine.LANGUAGE)) {
			return getLanguageName();
		}
		if (param.equals(ScriptEngine.LANGUAGE_VERSION)) {
			return getLanguageVersion();
		}
		if (param.equals("THREADING")) {
			return "MULTITHREADED";
		}

		return null;
	}


	public String getMethodCallSyntax(String objectName, String methodName, String... args) {
		// Note: this is very close to the bsh.StringUtil.methodString()
		// method, which constructs a method signature from arg *types*.  Maybe
		// combine these later.

		StringBuffer sb = new StringBuffer();
		if (objectName != null) {
			sb.append(objectName).append('.');
		}
		sb.append(methodName).append('(');
		if (args.length > 0) {
			sb.append(' ');
		}
		for (int i = 0; i < args.length; i++) {
			sb.append((args[i] == null) ? "null" : args[i]).append(i < (args.length - 1) ? ", " : " ");
		}
		sb.append(")");
		return sb.toString();
	}


	public String getOutputStatement(String message) {
		return "print( \"" + message + "\" );";
	}


	public String getProgram(String... statements) {
		StringBuffer sb = new StringBuffer();
		for (final String statement : statements) {
			sb.append(statement);
			if ( ! statement.endsWith(";")) {
				sb.append(";");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// End impl ScriptEngineInfo

	// Begin impl ScriptEngineFactory


	public ScriptEngine getScriptEngine() {
		return new BshScriptEngine();
	}

	// End impl ScriptEngineFactory
}

