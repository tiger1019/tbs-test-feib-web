package com.feib.api.controller;

import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;

public class BaseController {
  
  protected Span startTrace(String spanName) {
    Span sp = null;
    if (GlobalTracer.get() == null) {
      return sp;
    }
    if (GlobalTracer.get().activeSpan() != null) {
      sp = GlobalTracer.get().buildSpan(spanName).asChildOf(GlobalTracer.get().activeSpan()).start();
    } else {
      sp = GlobalTracer.get().buildSpan(spanName).start();
    }
    
    return sp;
  }

  protected void traceError(Span sp, String msg) {
    if (sp == null) return;
    Tags.ERROR.set(sp, true);
    sp.log(msg);
  }
  
  protected void traceDebug(Span sp, String msg) {
    if (sp == null) return;
    sp.log(msg);
  }
  
  protected void endTrace(Span sp) {
    if (sp == null) return;
    sp.finish();
  }
  
}
