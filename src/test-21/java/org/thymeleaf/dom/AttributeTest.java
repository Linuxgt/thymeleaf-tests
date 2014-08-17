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
package org.thymeleaf.dom;

import org.junit.Assert;
import org.junit.Test;


public final class AttributeTest {



    @Test
    public void testGetUnprefixedAttributeName() throws Exception {
        Assert.assertEquals("assert", Attribute.getUnprefixedAttributeName("th:assert"));
        Assert.assertEquals("src", Attribute.getUnprefixedAttributeName("src"));
        Assert.assertEquals("th", Attribute.getUnprefixedAttributeName("xmlns:th"));
        Assert.assertEquals("assert", Attribute.getUnprefixedAttributeName("data-th-assert"));
        Assert.assertEquals("data-something", Attribute.getUnprefixedAttributeName("data-something"));
    }


    @Test
    public void testGetPrefix() throws Exception {

        Assert.assertEquals("th", Attribute.getPrefixFromAttributeName("th:assert"));
        Assert.assertEquals(null, Attribute.getPrefixFromAttributeName("src"));
        Assert.assertEquals("xmlns", Attribute.getPrefixFromAttributeName("xmlns:th"));
        Assert.assertEquals("th", Attribute.getPrefixFromAttributeName("data-th-assert"));
        Assert.assertEquals(null, Attribute.getPrefixFromAttributeName("data-something"));

    }


}
