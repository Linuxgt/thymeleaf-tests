/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
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
package org.thymeleaf.templateengine.stsm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.templateengine.stsm.context.STSMWebProcessingContextBuilder;
import org.thymeleaf.testing.templateengine.context.web.SpringWebProcessingContextBuilder;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;
import org.thymeleaf.tests.util.SpringSpecificVersionUtils;


@RunWith(Parameterized.class)
public class STSMTest {


    private final int throttleStep;


    public STSMTest(final Integer throttleStep) {
        super();
        this.throttleStep = throttleStep.intValue();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {

        final int[] throttleSteps = new int[] { Integer.MAX_VALUE, 1000, 100, 11, 9, 5, 1};

        final List<Object[]> params = new ArrayList<Object[]>();
        for (int i = 0; i < throttleSteps.length; i++) {
            params.add(new Object[] { Integer.valueOf(i) });
        }
        return params;

    }




    @Test
    public void testSTSMWithoutIntegratedConversion() throws Exception {

        final TestExecutor executor = new TestExecutor();
        executor.setProcessingContextBuilder(new STSMWebProcessingContextBuilder());
        executor.setDialects(Arrays.asList(new IDialect[] { SpringSpecificVersionUtils.createSpringStandardDialectInstance()}));
        executor.setThrottleStep(this.throttleStep);
        executor.execute("classpath:templateengine/stsm");
        
        Assert.assertTrue(executor.isAllOK());
        
        
    }

    @Test
    public void testSTSMWithIntegratedConversion() throws Exception {

        final SpringWebProcessingContextBuilder contextBuilder = new SpringWebProcessingContextBuilder();
        contextBuilder.setApplicationContextConfigLocation("classpath:templateengine/stsm/applicationContext.xml");

        final TestExecutor executor = new TestExecutor();
        executor.setProcessingContextBuilder(contextBuilder);
        executor.setDialects(Arrays.asList(new IDialect[] { SpringSpecificVersionUtils.createSpringStandardDialectInstance()}));
        executor.setThrottleStep(this.throttleStep);
        executor.execute("classpath:templateengine/stsm");

        Assert.assertTrue(executor.isAllOK());


    }


}
