import java.util.ArrayList;

public class SetAllStocks {
    //All Stocks not the most elegant way to do it but saves me a lot of work
    public ArrayList<String> settingStocks(){
        String apiKey = "7XCWU785SED4ALAN";
        ArrayList<String> allStocks = new ArrayList<>();

       /* String url0 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "MSFT" + "&apikey=" + apiKey;
        String url1 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "AMZN" + "&apikey=" + apiKey;
        String url2 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "AAPL" + "&apikey=" + apiKey;
        String url3 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "GOOGL" + "&apikey=" + apiKey;
        String url4 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "META" + "&apikey=" + apiKey; // Facebook is now META
        String url5 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "BABA" + "&apikey=" + apiKey;
        String url6  = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "TSLA" + "&apikey=" + apiKey; // Tesla
        String url7  = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "NFLX" + "&apikey=" + apiKey; // Netflix
        String url8  = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "NVDA" + "&apikey=" + apiKey; // Nvidia
        String url9  = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "AMD" + "&apikey=" + apiKey;  // AMD
        String url10 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "INTC" + "&apikey=" + apiKey; // Intel
        String url11 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "ORCL" + "&apikey=" + apiKey; // Oracle
        String url12 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "PYPL" + "&apikey=" + apiKey; // PayPal
        String url13 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "V" + "&apikey=" + apiKey;    // Visa
        String url14 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "MA" + "&apikey=" + apiKey;   // Mastercard
        String url15 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "DIS" + "&apikey=" + apiKey;  // Disney
        String url16 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "PEP" + "&apikey=" + apiKey;  // PepsiCo
        String url17 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "KO" + "&apikey=" + apiKey;   // Coca-Cola
        String url18 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "MCD" + "&apikey=" + apiKey;  // McDonald's
        String url19 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "WMT" + "&apikey=" + apiKey;  // Walmart
        String url20 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "JNJ" + "&apikey=" + apiKey;  // Johnson & Johnson
        String url21 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "PFE" + "&apikey=" + apiKey;  // Pfizer
        String url22 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "XOM" + "&apikey=" + apiKey;  // ExxonMobil
        String url23 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "CVX" + "&apikey=" + apiKey;  // Chevron
        String url24 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "BA" + "&apikey=" + apiKey;   // Boeing
        String url25 = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + "NKE" + "&apikey=" + apiKey;  // Nike

allStocks.add(url1);
allStocks.add(url2);
allStocks.add(url3);
allStocks.add(url4);
allStocks.add(url5);
allStocks.add(url6);
allStocks.add(url7);
allStocks.add(url8);
allStocks.add(url9);
allStocks.add(url10);
allStocks.add(url11);
allStocks.add(url12);
allStocks.add(url13);
allStocks.add(url14);
allStocks.add(url15);
allStocks.add(url16);
allStocks.add(url17);
allStocks.add(url18);
allStocks.add(url19);
allStocks.add(url20);
allStocks.add(url21);
allStocks.add(url22);
allStocks.add(url23);
allStocks.add(url24);
allStocks.add(url25); */ //Thats a way how I lost all my credits:(
String url="https://www.alphavantage.co/query?\n" +
        "  function=REALTIME_BULK_QUOTES&\n" +
        "  symbols=MSFT,AMZN,AAPL,GOOGL,META,BABA,TSLA,NFLX,NVDA,AMD,\n" +
        "          INTC,ORCL,PYPL,V,MA,DIS,PEP,KO,MCD,WMT,\n" +
        "          JNJ,PFE,XOM,CVX,BA,NKE,IBM,CRM,SQ,SHOP,\n" +
        "          ADP,ABBV,MRK,LLY,T,VZ,WBA,COST,PG,UNH,\n" +
        "          HD,GS,JPM,BAC,C,MO,TSN,CAT,UPS,GM,ADBE,NOW&\n" +
        "  apikey=U5FBY2TL4YBJHMW9\n";

return allStocks;
    }


}
