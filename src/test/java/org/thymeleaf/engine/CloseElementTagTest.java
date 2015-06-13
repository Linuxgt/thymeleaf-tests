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

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.TestTemplateEngineConfigurationBuilder;
import org.thymeleaf.model.ICloseElementTag;
import org.thymeleaf.resource.StringResource;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;
import org.thymeleaf.templateparser.markup.XMLTemplateParser;


public final class CloseElementTagTest {

    private static final HTMLTemplateParser HTML_PARSER = new HTMLTemplateParser(2, 4096);
    private static final XMLTemplateParser XML_PARSER = new XMLTemplateParser(2, 4096);
    private static final IEngineConfiguration TEMPLATE_ENGINE_CONFIGURATION = TestTemplateEngineConfigurationBuilder.build();






    private static ICloseElementTag computeHtmlTag(final String input) {

        final String templateName = "test";
        final TagObtentionTemplateHandler handler = new TagObtentionTemplateHandler();

        HTML_PARSER.parseTemplate(TEMPLATE_ENGINE_CONFIGURATION, TemplateMode.HTML, new StringResource(templateName, input), null, handler);

        return handler.tag;

    }




    private static ICloseElementTag computeXmlTag(final String input) {

        final String templateName = "test";
        final TagObtentionTemplateHandler handler = new TagObtentionTemplateHandler();

        XML_PARSER.parseTemplate(TEMPLATE_ENGINE_CONFIGURATION, TemplateMode.XML, new StringResource(templateName, input), null, handler);

        return handler.tag;

    }




    private static class TagObtentionTemplateHandler extends AbstractTemplateHandler {


        ICloseElementTag tag;


        @Override
        public void handleCloseElement(final ICloseElementTag closeElementTag) {
            this.tag = closeElementTag.cloneElementTag();
        }

    }


}
