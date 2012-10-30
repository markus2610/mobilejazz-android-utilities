package cat.mobilejazz.utilities.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple implementation of a class for rendering string templates. The
 * syntax for variables is <code>${name}</code> and values for variables are
 * passed as a map parameter to {@link #render(Map)}.
 * 
 * @author Hannes Widmoser
 * 
 */
public class StringTemplate {

	private static final Pattern pattern = Pattern
			.compile("\\$\\{([\\.\\w]*)\\}");

	private List<String> variables;
	private String template;
	private Matcher matcher;

	/**
	 * Creates a string template with the given template string.
	 * 
	 * @param template
	 *            The template string where variables are denoted by the pattern
	 *            <code>${name}</code>.
	 */
	public StringTemplate(String template) {
		this.template = template;
		matcher = pattern.matcher(template);
	}
	
	public int getVariableIndex(String name) {
		if (variables == null) {
			variables = new ArrayList<String>();
			while (matcher.find()) {
				variables.add(matcher.group(1));
			}
		}
		return variables.indexOf(name);
	}

	/**
	 * Gets the template string.
	 * 
	 * @return the raw template string.
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Renders the template by replacing the variable patterns with the value of
	 * the object whose key matches the name of the variable.
	 * 
	 * @param values
	 *            A {@link Map} that contains the values of the variables.
	 * @return A {@link String} representing the rendered string
	 */
	public String render(Map<String, Object> values) {
		StringBuffer s = new StringBuffer();
		while (matcher.find()) {
			String key = matcher.group(1);
			matcher.appendReplacement(s, String.valueOf(values.get(key)));
		}
		matcher.appendTail(s);
		return s.toString();
	}

}
