package com.feib.business.api.adapter;

public interface BaseIntegrationAdapter<I, O> {
  public O ByEvent(I vo);
  public void ByAsyncEvent(I vo);
  public O ByApi(I vo);

}
