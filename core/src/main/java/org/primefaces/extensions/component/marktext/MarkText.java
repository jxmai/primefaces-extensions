/*
 * Copyright (c) 2011-2025 PrimeFaces Extensions
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.primefaces.extensions.component.marktext;

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import org.primefaces.component.api.Widget;
import org.primefaces.extensions.util.Constants;

@ResourceDependency(library = "primefaces", name = "components.css")
@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
@ResourceDependency(library = "primefaces", name = "jquery/jquery-plugin.js")
@ResourceDependency(library = Constants.LIBRARY, name = "marktext/")
public class MarkText extends UIComponentBase implements Widget {
    public static final String COMPONENT_TYPE = "org.primefaces.extensions.component.MarkText";
    public static final String COMPONENT_FAMILY = "org.primefaces.extensions.component";
    public static final String DEFAULT_RENDERER = "org.primefaces.extensions.component.MarkTextRenderer";

    protected enum PropertyKeys {
        widgetVar, target, keyword, highlightStyleClass, element, caseSensitive, diacritics, accuracy, separateWordSearch
    }

    public MarkText() {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getWidgetVar() {
        return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(String widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, widgetVar);
    }

    public String getTarget() {
        return (String) getStateHelper().eval(PropertyKeys.target, null);
    }

    public void setTarget(String target) {
        getStateHelper().put(PropertyKeys.target, target);
    }

    public String getKeyword() {
        return (String) getStateHelper().eval(PropertyKeys.keyword, null);
    }

    public void setKeyword(String keyword) {
        getStateHelper().put(PropertyKeys.keyword, keyword);
    }

    public String getHighlightStyleClass() {
        return (String) getStateHelper().eval(PropertyKeys.highlightStyleClass, null);
    }

    public void setHighlightStyleClass(String highlightStyleClass) {
        getStateHelper().put(PropertyKeys.highlightStyleClass, highlightStyleClass);
    }

    public boolean getSeparateWordSearch() {
        return (Boolean) getStateHelper().eval(PropertyKeys.separateWordSearch, true);
    }

    public void setSeparateWordSearch(boolean separateWordSearch) {
        getStateHelper().put(PropertyKeys.separateWordSearch, separateWordSearch);
    }

    public String getElement() {
        return (String) getStateHelper().eval(PropertyKeys.element, null);
    }

    public void setElement(String element) {
        getStateHelper().put(PropertyKeys.element, element);
    }

    public boolean getCaseSensitive() {
        return (Boolean) getStateHelper().eval(PropertyKeys.caseSensitive, Boolean.FALSE);
    }

    public void setCaseSensitive(boolean caseSensitive) {
        getStateHelper().put(PropertyKeys.caseSensitive, caseSensitive);
    }

    public boolean getDiacritics() {
        return (Boolean) getStateHelper().eval(PropertyKeys.diacritics, Boolean.FALSE);
    }

    public void setDiacritics(boolean diacritics) {
        getStateHelper().put(PropertyKeys.diacritics, diacritics);
    }

    public String getAccuracy() {
        return (String) getStateHelper().eval(PropertyKeys.accuracy, null);
    }

    public void setAccuracy(String accuracy) {
        getStateHelper().put(PropertyKeys.accuracy, accuracy);
    }

}