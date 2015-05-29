/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.standard.expression;

import java.util.Map;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.thymeleaf.Configuration;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.context.ProcessingContext;

public class ExpressionBenchmark {

    
    
    public ExpressionBenchmark() {
        super();
    }
    
    
    public static void main(String[] args) throws Exception {

        final Map<String,String> expressionsMap = ExpressionBenchmarkDefinitions.createExpressionsMap();

        final StandardExpressionExecutor executor  = new StandardExpressionExecutor(OgnlVariableExpressionEvaluator.INSTANCE);
        final StandardExpressionParser parser = new StandardExpressionParser(executor);

        final Configuration configuration = new Configuration();
        final IProcessingContext processingContext = new ProcessingContext(new Context());

        for (final Map.Entry<String,String> expressionEntry : expressionsMap.entrySet()) {
            // We won't check parsing in 2.0, as string representations changed a bit in 2.1
            final String expression = expressionEntry.getKey();
            final Expression result = parser.parseExpression(configuration, processingContext, expression);
            Assert.assertNotNull(result);
        }
        
        
        
        final StopWatch sw = new StopWatch();
        
        sw.start();
        
        
        for (int x = 0; x < 1000; x++)
            for (final String expression : expressionsMap.keySet())
                parser.parseExpression(configuration, processingContext, expression);

        sw.stop();
        
        System.out.println("First pass: " + sw.toString());
        
        sw.reset();
        sw.start();
        
        for (int x = 0; x < 1000; x++)
            for (final String expression : expressionsMap.keySet())
                parser.parseExpression(configuration, processingContext, expression);


        sw.stop();
        
        System.out.println("Second pass: " + sw.toString());
        
    }

    
    
    
    
}
