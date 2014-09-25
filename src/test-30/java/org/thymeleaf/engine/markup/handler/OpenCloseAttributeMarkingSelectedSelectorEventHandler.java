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
package org.thymeleaf.engine.markup.handler;

/**
 *
 * @author Daniel Fern&aacute;ndez
 * @since 3.0.0
 *
 */
public class OpenCloseAttributeMarkingSelectedSelectorEventHandler extends DelegatingSelectedSelectorEventHandler {

    private static final char[] SELECTOR_ATTRIBUTE_NAME = "selectors".toCharArray();
    private static final char[] INNER_WHITESPACE_BUFFER = " ".toCharArray();
    private static final int SELECTOR_ATTRIBUTE_BUFFER_LEN = 40;

    private boolean lastWasInnerWhiteSpace = false;
    private char[] selectorAttributeBuffer;


    public OpenCloseAttributeMarkingSelectedSelectorEventHandler() {

        super();

        this.selectorAttributeBuffer = new char[SELECTOR_ATTRIBUTE_BUFFER_LEN];
        System.arraycopy(SELECTOR_ATTRIBUTE_NAME, 0, this.selectorAttributeBuffer, 0, SELECTOR_ATTRIBUTE_NAME.length);
        this.selectorAttributeBuffer[SELECTOR_ATTRIBUTE_NAME.length] = '=';
        this.selectorAttributeBuffer[SELECTOR_ATTRIBUTE_NAME.length + 1] = '\"';

    }


    @Override
    public void onSelectedAttribute(
            final String[] selectors, final boolean[] selectorMatches,
            final char[] buffer,
            final int nameOffset, final int nameLen, final int nameLine, final int nameCol,
            final int operatorOffset, final int operatorLen, final int operatorLine, final int operatorCol,
            final int valueContentOffset, final int valueContentLen, final int valueOuterOffset, final int valueOuterLen, final int valueLine, final int valueCol,
            final String documentName,
            final IMarkupHandler handler) {
        this.lastWasInnerWhiteSpace = false;
        handler.onAttribute(
                buffer,
                nameOffset, nameLen, nameLine, nameCol,
                operatorOffset, operatorLen, operatorLine, operatorCol,
                valueContentOffset, valueContentLen, valueOuterOffset, valueOuterLen, valueLine, valueCol,
                documentName);
    }

    @Override
    public void onSelectedStandaloneElementEnd(
            final String[] selectors, final boolean[] selectorMatches,
            final String normalizedName, final char[] buffer, final int offset, final int len,
            final boolean minimized,
            final String documentName, final int line, final int col,
            final IMarkupHandler handler) {

        if (!this.lastWasInnerWhiteSpace) {
            handler.onElementInnerWhiteSpace(INNER_WHITESPACE_BUFFER, 0, INNER_WHITESPACE_BUFFER.length, documentName, line, col);
            this.lastWasInnerWhiteSpace = true;
        }

        StringBuilder selectorValues = null;
        for (int i = 0; i < selectors.length; i++) {
            if (selectorMatches[i]) {
                if (selectorValues != null) {
                    selectorValues.append(' ');
                } else {
                    selectorValues = new StringBuilder(30);
                }
                selectorValues.append(selectors[i]);
            }
        }

        if (selectorValues != null) {

            final int selectorValuesLen = selectorValues.length();
            checkSelectorAttributeLen(selectorValuesLen);

            selectorValues.getChars(0, selectorValuesLen, this.selectorAttributeBuffer, SELECTOR_ATTRIBUTE_NAME.length + 2);
            this.selectorAttributeBuffer[SELECTOR_ATTRIBUTE_NAME.length + 2 + selectorValuesLen] = '\"';

            handler.onAttribute(
                    this.selectorAttributeBuffer,
                    0, SELECTOR_ATTRIBUTE_NAME.length, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length, 1, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length + 2, selectorValuesLen,
                    SELECTOR_ATTRIBUTE_NAME.length + 1, selectorValuesLen + 2, line, col,
                    documentName);

        }

        this.lastWasInnerWhiteSpace = false;
        handler.onStandaloneElementEnd(normalizedName, buffer, offset, len, minimized, documentName, line, col);

    }

    @Override
    public void onSelectedOpenElementEnd(
            final String[] selectors, final boolean[] selectorMatches,
            final String normalizedName, final char[] buffer, final int offset, final int len,
            final String documentName, final int line, final int col,
            final IMarkupHandler handler) {

        if (!this.lastWasInnerWhiteSpace) {
            handler.onElementInnerWhiteSpace(INNER_WHITESPACE_BUFFER, 0, INNER_WHITESPACE_BUFFER.length, documentName, line, col);
            this.lastWasInnerWhiteSpace = true;
        }

        StringBuilder selectorValues = null;
        for (int i = 0; i < selectors.length; i++) {
            if (selectorMatches[i]) {
                if (selectorValues != null) {
                    selectorValues.append(' ');
                } else {
                    selectorValues = new StringBuilder(30);
                }
                selectorValues.append(selectors[i]);
            }
        }

        if (selectorValues != null) {

            final int selectorValuesLen = selectorValues.length();
            checkSelectorAttributeLen(selectorValuesLen);

            selectorValues.getChars(0, selectorValuesLen, this.selectorAttributeBuffer, SELECTOR_ATTRIBUTE_NAME.length + 2);
            this.selectorAttributeBuffer[SELECTOR_ATTRIBUTE_NAME.length + 2 + selectorValuesLen] = '\"';

            handler.onAttribute(
                    this.selectorAttributeBuffer,
                    0, SELECTOR_ATTRIBUTE_NAME.length, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length, 1, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length + 2, selectorValuesLen,
                    SELECTOR_ATTRIBUTE_NAME.length + 1, selectorValuesLen + 2, line, col,
                    documentName);

        }

        this.lastWasInnerWhiteSpace = false;
        handler.onOpenElementEnd(normalizedName, buffer, offset, len, documentName, line, col);

    }

    @Override
    public void onSelectedCloseElementEnd(
            final String[] selectors, final boolean[] selectorMatches,
            final String normalizedName, final char[] buffer, final int offset, final int len,
            final String documentName, final int line, final int col,
            final IMarkupHandler handler) {

        if (!this.lastWasInnerWhiteSpace) {
            handler.onElementInnerWhiteSpace(INNER_WHITESPACE_BUFFER, 0, INNER_WHITESPACE_BUFFER.length, documentName, line, col);
            this.lastWasInnerWhiteSpace = true;
        }

        StringBuilder selectorValues = null;
        for (int i = 0; i < selectors.length; i++) {
            if (selectorMatches[i]) {
                if (selectorValues != null) {
                    selectorValues.append(' ');
                } else {
                    selectorValues = new StringBuilder(30);
                }
                selectorValues.append(selectors[i]);
            }
        }

        if (selectorValues != null) {

            final int selectorValuesLen = selectorValues.length();
            checkSelectorAttributeLen(selectorValuesLen);

            selectorValues.getChars(0, selectorValuesLen, this.selectorAttributeBuffer, SELECTOR_ATTRIBUTE_NAME.length + 2);
            this.selectorAttributeBuffer[SELECTOR_ATTRIBUTE_NAME.length + 2 + selectorValuesLen] = '\"';

            handler.onAttribute(
                    this.selectorAttributeBuffer,
                    0, SELECTOR_ATTRIBUTE_NAME.length, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length, 1, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length + 2, selectorValuesLen,
                    SELECTOR_ATTRIBUTE_NAME.length + 1, selectorValuesLen + 2, line, col,
                    documentName);

        }

        this.lastWasInnerWhiteSpace = false;
        handler.onCloseElementEnd(normalizedName, buffer, offset, len, documentName, line, col);

    }

    @Override
    public void onSelectedUnmatchedCloseElementEnd(
            final String[] selectors, final boolean[] selectorMatches,
            final String normalizedName, final char[] buffer, final int offset, final int len,
            final String documentName, final int line, final int col,
            final IMarkupHandler handler) {

        if (!this.lastWasInnerWhiteSpace) {
            handler.onElementInnerWhiteSpace(INNER_WHITESPACE_BUFFER, 0, INNER_WHITESPACE_BUFFER.length, documentName, line, col);
            this.lastWasInnerWhiteSpace = true;
        }

        StringBuilder selectorValues = null;
        for (int i = 0; i < selectors.length; i++) {
            if (selectorMatches[i]) {
                if (selectorValues != null) {
                    selectorValues.append(' ');
                } else {
                    selectorValues = new StringBuilder(30);
                }
                selectorValues.append(selectors[i]);
            }
        }

        if (selectorValues != null) {

            final int selectorValuesLen = selectorValues.length();
            checkSelectorAttributeLen(selectorValuesLen);

            selectorValues.getChars(0, selectorValuesLen, this.selectorAttributeBuffer, SELECTOR_ATTRIBUTE_NAME.length + 2);
            this.selectorAttributeBuffer[SELECTOR_ATTRIBUTE_NAME.length + 2 + selectorValuesLen] = '\"';

            handler.onAttribute(
                    this.selectorAttributeBuffer,
                    0, SELECTOR_ATTRIBUTE_NAME.length, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length, 1, line, col,
                    SELECTOR_ATTRIBUTE_NAME.length + 2, selectorValuesLen,
                    SELECTOR_ATTRIBUTE_NAME.length + 1, selectorValuesLen + 2, line, col,
                    documentName);

        }

        this.lastWasInnerWhiteSpace = false;
        handler.onUnmatchedCloseElementEnd(normalizedName, buffer, offset, len, documentName, line, col);

    }

    @Override
    public void onSelectedElementInnerWhiteSpace(
            final String[] selectors, final boolean[] selectorMatches,
            final char[] buffer, final int offset, final int len,
            final String documentName, final int line, final int col,
            final IMarkupHandler handler) {
        this.lastWasInnerWhiteSpace = true;
        handler.onElementInnerWhiteSpace(buffer, offset, len, documentName, line, col);
    }




    private void checkSelectorAttributeLen(final int valueLen) {
        final int totalLenRequired = SELECTOR_ATTRIBUTE_NAME.length + 3 + valueLen;
        if (this.selectorAttributeBuffer.length < totalLenRequired) {
            final char[] newSelectorAttributeBuffer = new char[totalLenRequired];
            System.arraycopy(this.selectorAttributeBuffer, 0, newSelectorAttributeBuffer, 0, this.selectorAttributeBuffer.length);
            this.selectorAttributeBuffer = newSelectorAttributeBuffer;
        }
    }

}
