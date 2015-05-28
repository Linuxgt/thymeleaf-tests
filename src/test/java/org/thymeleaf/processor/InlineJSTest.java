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
package org.thymeleaf.processor;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class InlineJSTest extends AbstractDocumentProcessingTest {

    public void testInlineJS() throws Exception {
        
        {
            Document doc = processTemplate("processor/InlineJSTest");
    
            assertNodeExists(doc, "/THYMELEAF_ROOT/html/body/script");
            Node node = getNode(doc, "/THYMELEAF_ROOT/html/body/script[1]");
            assertEquals("/**/var x = 1;/**/", node.getTextContent().trim());
        }
        
        {
            Document doc = processTemplate("processor/InlineJSTest2");
    
            assertNodeExists(doc, "/THYMELEAF_ROOT/html/body/div/script");
            Node node = getNode(doc, "/THYMELEAF_ROOT/html/body/div/script[1]");
            assertEquals("/**/[[\"#A3AFDE\",\"#DEFAB1\",\"#3C5F1B\"]]/**/", node.getTextContent().trim());
        }
        
        {
            Document doc = processTemplate("processor/InlineJSTest3");
    
            assertNodeExists(doc, "/THYMELEAF_ROOT/html/body/div/script");
            Node node = getNode(doc, "/THYMELEAF_ROOT/html/body/div/script[1]");
            assertEquals("/**/'hello, world!'/**/", node.getTextContent().trim());
        }
        
    }

}
