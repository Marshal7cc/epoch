package org.epoch.jpa.util;

import java.util.regex.Pattern;

import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

/**
 * @author Marshal
 * @since 2022/7/14
 */
public class StringQueryUtils {
    private static final String EXPRESSION_PARAMETER = "?#{";
    private static final String QUOTED_EXPRESSION_PARAMETER = "?__HASH__{";

    private static final Pattern EXPRESSION_PARAMETER_QUOTING = Pattern.compile(Pattern.quote(EXPRESSION_PARAMETER));
    private static final Pattern EXPRESSION_PARAMETER_UNQUOTING = Pattern.compile(Pattern
            .quote(QUOTED_EXPRESSION_PARAMETER));

    private static final String ENTITY_NAME = "entityName";
    private static final String ENTITY_NAME_VARIABLE = "#" + ENTITY_NAME;
    private static final String ENTITY_NAME_VARIABLE_EXPRESSION = "#{" + ENTITY_NAME_VARIABLE + "}";

    public static String renderQueryIfExpressionOrReturnQuery(String query, String entityName) {

        SpelExpressionParser parser = new SpelExpressionParser();

        Assert.notNull(query, "query must not be null!");

        if (!containsExpression(query)) {
            return query;
        }


        StandardEvaluationContext evalContext = new StandardEvaluationContext();
        evalContext.setVariable(ENTITY_NAME, entityName);

        query = potentiallyQuoteExpressionsParameter(query);

        Expression expr = parser.parseExpression(query, ParserContext.TEMPLATE_EXPRESSION);

        String result = expr.getValue(evalContext, String.class);

        if (result == null) {
            return query;
        }

        return potentiallyUnquoteParameterExpressions(result);
    }

    private static String potentiallyUnquoteParameterExpressions(String result) {
        return EXPRESSION_PARAMETER_UNQUOTING.matcher(result).replaceAll(EXPRESSION_PARAMETER);
    }

    private static String potentiallyQuoteExpressionsParameter(String query) {
        return EXPRESSION_PARAMETER_QUOTING.matcher(query).replaceAll(QUOTED_EXPRESSION_PARAMETER);
    }

    private static boolean containsExpression(String query) {
        return query.contains(ENTITY_NAME_VARIABLE_EXPRESSION);
    }

}
