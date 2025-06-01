package hub.experiment.strategy;

public class TradingStrategy {
  private Double desiredBuyingPricePercent;
  private Double desiredSellingPricePercent;

  public Double getDesiredBuyingPricePercent() {
    return desiredBuyingPricePercent;
  }

  public void setDesiredBuyingPricePercent(Double desiredBuyingPricePercent) {
    this.desiredBuyingPricePercent = desiredBuyingPricePercent;
  }

  public Double getDesiredSellingPricePercent() {
    return desiredSellingPricePercent;
  }

  public void setDesiredSellingPricePercent(Double desiredSellingPricePercent) {
    this.desiredSellingPricePercent = desiredSellingPricePercent;
  }
  
  //any other rules can go here, but the aim is to read from config / DB and 
  
  public Double evaluateBuyingPrice(Double lastCloseStockPrice){
    Double result = lastCloseStockPrice *(1 - (this.desiredBuyingPricePercent/100));
    return result;
  }

  public Double evaluateSellingPrice(Double lastCloseStockPrice){
    Double result = lastCloseStockPrice * (1 + (this.desiredSellingPricePercent/100));
    return result;
  }
  
  public TradingStrategy(Double desiredBuyingPricePercent, Double desiredSellingPricePercent){
    this.desiredBuyingPricePercent = desiredBuyingPricePercent;
    this.desiredSellingPricePercent = desiredSellingPricePercent;
  }
  
}
