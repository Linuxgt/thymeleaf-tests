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
package org.thymeleaf.engine;

import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.TestTemplateEngineConfigurationBuilder;
import org.thymeleaf.resource.IResource;
import org.thymeleaf.resource.StringResource;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;


public final class BareHtmlEngineTest {

    private static final HTMLTemplateParser PARSER = new HTMLTemplateParser(2, 4096);
    private static final IEngineConfiguration TEMPLATE_ENGINE_CONFIGURATION = TestTemplateEngineConfigurationBuilder.build();



    @Test
    public void test() throws Exception {


        check("<!doctype html>");
        check("<img href='http://something.com'>");
        check("<img href='http://something.com'/>");
        check("<img href='http://something.com' >");
        check("<img href='http://something.com' />");
        check("<img href='http://something.com' >");
        check("<img \n href='http://something.com' />");
        check("<img \n href = \"http://something.com\" />");
        check("<img \n href = something >");
        check("<img \n href = something disabled>");
        check("<img \n href = something disabled= 'disabled'>");
        check("<div><img \n href = something disabled= 'disabled'>", "<img \n href = something disabled= 'disabled'>", "//img");
        check("<div><img \n href = something disabled= 'disabled'>", "<img \n href = something disabled= 'disabled'>", "//img");
        check("<p id='http://something.com'>...</p>");
        check("<p id='http://something.com'></p>");
        check("<p id='http://something.com'/>");
        check("<p id='http://something.com'>...</p>");
        check("<p id='http://something.com' >...</p>");
        check("<p id='http://something.com' />...</p>");
        check("<p id='http://something.com' >...</p>");
        check("<p id='http://something.com' >...</p>");
        check("<p \n id='http://something.com' />.\n.\n.\n</p>");
        check("<p \n id = \"http://something.com\" ></p>");
        check("<p \n id = something >\n\n <div>lala</p>");
        check("<p \n id = something disabled>...</p>");
        check("<p \n id = something disabled= 'disabled'>");

    }




    private static void check(final String inputOutput)
            throws Exception{
        check(inputOutput, inputOutput, (String[])null);
    }


    private static void check(final String input, final String output, final String blockSelector)
            throws Exception{
        check(input, output, new String[] { blockSelector });
    }


    private static void check(final String input, final String output, final String[] blockSelectors)
            throws Exception{

        final String templateName = "test";
        final StringWriter writer = new StringWriter();
        final ITemplateHandler handler = new OutputTemplateHandler(writer);

        PARSER.parseStandalone(TEMPLATE_ENGINE_CONFIGURATION, new StringResource(templateName, input), blockSelectors, TemplateMode.HTML, handler);

        Assert.assertEquals("Test failed for file: " + templateName, output, writer.toString());

    }


    private static final class TestStringResourceResolver implements IResourceResolver {

        public String getName() {
            return "StringResourceResolver";
        }

        public IResource resolveResource(
                final IEngineConfiguration engineConfiguration, final IContext context,
                final String resourceName, final String characterEncoding) {
            return new StringResource("stringresource", resourceName);
        }
    }



}
