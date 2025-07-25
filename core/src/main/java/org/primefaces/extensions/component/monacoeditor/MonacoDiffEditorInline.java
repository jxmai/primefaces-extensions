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
package org.primefaces.extensions.component.monacoeditor;

import java.util.Collection;
import java.util.Map;

import jakarta.faces.application.ResourceDependency;
import jakarta.faces.event.BehaviorEvent;

import org.primefaces.extensions.util.Constants;

/**
 * Component for the Monaco diff code editor JavaScript library. This is the inline monaco editor that creates a new instance in a textarea element on the same
 * page. There is also framed version available that creates an editor in an iframe for better scoping.
 *
 * @see MonacoDiffEditorFramed
 * @since 11.0.1
 */
@SuppressWarnings("java:S110")
@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
@ResourceDependency(library = "primefaces", name = "core.js")
@ResourceDependency(library = Constants.LIBRARY, name = "primefaces-extensions.js")
@ResourceDependency(library = Constants.LIBRARY, name = "monacoeditor/widget-inline.js")
@ResourceDependency(library = Constants.LIBRARY, name = "monacoeditor/monacoeditor.css")
public class MonacoDiffEditorInline extends MonacoDiffEditorBase {

    public static final String COMPONENT_TYPE = "org.primefaces.extensions.component.MonacoDiffEditorInline";

    public static final String RENDERER_TYPE = "org.primefaces.extensions.component.MonacoDiffEditorInlineRenderer";

    public static final String STYLE_CLASS = "ui-monaco-editor ui-monaco-editor-diff ui-monaco-editor-inline ";

    public static final String WIDGET_NAME = "ExtMonacoDiffEditorInline";

    /**
     * Default no-arg constructor for this widget invoked by the framework.
     */
    public MonacoDiffEditorInline() {
        super(RENDERER_TYPE);
    }

    @Override
    public Map<String, Class<? extends BehaviorEvent>> getBehaviorEventMapping() {
        // no additional events for now
        return BASE_BEHAVIOR_EVENT_MAPPING;
    }

    @Override
    public final Collection<String> getEventNames() {
        // no additional events for now
        return BASE_EVENT_NAMES;
    }

    public final String getExtender() {
        return (String) getStateHelper().eval(DiffEditorInlinePropertyKeys.extender, null);
    }

    public final void setExtender(final String extender) {
        getStateHelper().put(DiffEditorInlinePropertyKeys.extender, extender);
    }

    public final String getOverflowWidgetsDomNode() {
        return (String) getStateHelper().eval(DiffEditorInlinePropertyKeys.overflowWidgetsDomNode, null);
    }

    public final void setOverflowWidgetsDomNode(final String overflowWidgetsDomNode) {
        getStateHelper().put(DiffEditorInlinePropertyKeys.overflowWidgetsDomNode, overflowWidgetsDomNode);
    }
}
