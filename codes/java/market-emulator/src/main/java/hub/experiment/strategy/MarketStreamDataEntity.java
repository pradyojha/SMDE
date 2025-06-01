package hub.experiment.strategy;

import java.util.Date;

public class MarketStreamDataEntity {
  //StockName
  private String stockName;
  //Date
  private String tradeDate;
  //Time
  private String tradeTime;
  //Open
  private Double stockOpenPrice;
  //High
  private Double stockHighPrice;
  //Low
  private Double stockLowPrice;
  //Close
  private Double stockClosePrice;
  //Volume
  private Integer stockVolumeAvailable;

  public String getStockName() {
    return stockName;
  }
  public void setStockName(String stockName){
    this.stockName = stockName;
  }

  public String getTradeDate() {
    return tradeDate;
  }
  public void setTradeDate(String tradeDate){
    this.tradeDate = tradeDate;
  }

  public String getTradeTime() {
    return tradeTime;
  }
  public void setTradeTime(String tradeTime){
    this.tradeTime = tradeTime;
  }

  public Double getStockOpenPrice() {
    return stockOpenPrice;
  }
  public void setStockOpenPrice(Double stockOpenPrice) {
    this.stockOpenPrice = stockOpenPrice;
  }

  public Double getStockHighPrice() {
    return stockHighPrice;
  }
  public void setStockHighPrice(Double stockHighPrice) {
    this.stockHighPrice = stockHighPrice;
  }

  public Double getStockLowPrice() {
    return stockLowPrice;
  }
  public void setStockLowPrice(Double stockLowPrice) {
    this.stockLowPrice = stockLowPrice;
  }

  public Double getStockClosePrice() {
    return stockClosePrice;
  }
  public void setStockClosePrice(Double stockClosePrice) {
    this.stockClosePrice = stockClosePrice;
  }

  public Integer getStockVolumeAvailable() {
    return stockVolumeAvailable;
  }
  public void setStockVolumeAvailable(Integer stockVolumeAvailable) {
    this.stockVolumeAvailable = stockVolumeAvailable;
  }

  public MarketStreamDataEntity(String stockName, String tradeDate, String tradeTime, Double stockOpenPrice, Double stockHighPrice,
      Double stockLowPrice, Double stockClosePrice, Integer stockVolumeAvailable){
    this.stockName = stockName;
    this.tradeDate = tradeDate;
    this.tradeTime = tradeTime;
    this.stockOpenPrice = stockOpenPrice;
    this.stockHighPrice = stockHighPrice;
    this.stockLowPrice = stockLowPrice;
    this.stockClosePrice = stockClosePrice;
    this.stockVolumeAvailable = stockVolumeAvailable;
  }
}
