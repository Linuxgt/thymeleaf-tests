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
package org.thymeleaf.engine30.springintegration;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.engine30.springintegration.context.ErrorsSpringIntegrationWebProcessingContextBuilder;
import org.thymeleaf.engine30.springintegration.context.SpringIntegrationWebProcessingContextBuilder;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;
import org.thymeleaf.tests.util.SpringSpecificVersionUtils;


public class SpringIntegration30Test {
    
    
    public SpringIntegration30Test() {
        super();
    }
    
    
    
    
    @Test
    public void testForm() throws Exception {

        final TestExecutor executor = new TestExecutor();
        executor.setProcessingContextBuilder(new SpringIntegrationWebProcessingContextBuilder());
        executor.setDialects(Arrays.asList(new IDialect[] { SpringSpecificVersionUtils.createSpringStandardDialectInstance()}));
        executor.execute("classpath:engine30/springintegration/form");
        
        Assert.assertTrue(executor.isAllOK());
        
    }

    
    
    @Test
    public void testErrors() throws Exception {

        final TestExecutor executor = new TestExecutor();
        executor.setProcessingContextBuilder(new ErrorsSpringIntegrationWebProcessingContextBuilder());
        executor.setDialects(Arrays.asList(new IDialect[] { SpringSpecificVersionUtils.createSpringStandardDialectInstance()}));
        executor.execute("classpath:engine30/springintegration/errors");
        
        Assert.assertTrue(executor.isAllOK());
        
    }



    @Test
    public void testBeans() throws Exception {

        final TestExecutor executor = new TestExecutor();
        executor.setProcessingContextBuilder(
                new org.thymeleaf.engine.springintegration.context.SpringIntegrationWebProcessingContextBuilder(
                        "classpath:engine30/springintegration/applicationContext-beans.xml"));
        executor.setDialects(Arrays.asList(new IDialect[] { SpringSpecificVersionUtils.createSpringStandardDialectInstance()}));
        executor.execute("classpath:engine30/springintegration/beans");

        Assert.assertTrue(executor.isAllOK());

    }




}
