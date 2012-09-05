/****************************************************************

  Traction Software, Inc. Confidential and Proprietary Information

  Copyright (c) 1996-2012 Traction Software, Inc.
  All rights reserved.

****************************************************************/

// PLEASE DO NOT DELETE THIS LINE -- make copyright depends on it.

package com.tractionsoftware.gwt.user.client.ui.impl;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.tractionsoftware.gwt.user.client.ui.InputWidget;

/**
 * Uses an HTML5 input type=time control to implement the UTCTimeBox
 * 
 * @author andy
 */
public class UTCTimeBoxImplHtml5 extends UTCTimeBoxImplShared {
    
    private static final DateTimeFormat timeInputFormat = DateTimeFormat.getFormat("HH:mm");
    
    private InputWidget widget;
    
    public UTCTimeBoxImplHtml5() {
        widget = new InputWidget("time");
        setTimeFormat(timeInputFormat);
        System.err.println("Created UTCTimeBoxImplHtml5");
        
        widget.addValueChangeHandler(new ValueChangeHandler() {

            @Override
            public void onValueChange(ValueChangeEvent event) {
                fireValueChangeEvent(getValue());
            }
            
        });
        
        initWidget(widget);
    }
    
    @Override
    public Long getValue() {
        return string2long(widget.getValue());
    }

    @Override
    public void setValue(Long value, boolean fireEvents) {
        widget.setValue(long2string(value), fireEvents);
    }

     @Override
    public String getText() {
        return value2text(getValue());
    }

    @Override
    public void setText(String text) {
        setValue(text2value(text), true);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Long> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public void fireValueChangeEvent(Long value) {
        ValueChangeEvent.fire(this, value);        
    }

    @Override
    public void setTabIndex(int tabIndex) {
        widget.setTabIndex(tabIndex);
    }
    
    // ----------------------------------------------------------------------
    // the core translation methods of this class using the form HH:mm

    // we only obey hh:mm
    private Long string2long(String value) {
        return parseUsingFormat(value, timeInputFormat);
    }
    
    private String long2string(Long value) {
        return formatUsingFormat(value, timeInputFormat);
    }    
    
}
 