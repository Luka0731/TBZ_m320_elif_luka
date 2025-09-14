import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class StockTracker {

    static class Stock {
        String symbol;
        double price;

        Stock(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }
    }

    public void ShowAll() {
        try {
            // Make sure the URL has NO newlines or extra spaces
            String stockUrl = "https://www.alphavantage.co/query?function=REALTIME_BULK_QUOTES&symbols=" +
                    "MSFT,AMZN,AAPL,GOOGL,META,BABA,TSLA,NFLX,NVDA,AMD," +
                    "INTC,ORCL,PYPL,V,MA,DIS,PEP,KO,MCD,WMT," +
                    "JNJ,PFE,XOM,CVX,BA,NKE,IBM,CRM,SQ,SHOP," +
                    "ADP,ABBV,MRK,LLY,T,VZ,WBA,COST,PG,UNH," +
                    "HD,GS,JPM,BAC,C,MO,TSN,CAT,UPS,GM,ADBE,NOW" +
                    "&apikey=YOUR_API_KEY";

            HttpResponse<String> response = Unirest.get(stockUrl).asString();
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.getBody(), JsonObject.class);

            // Check for Note or Information key (rate limit or error)
            if (json.has("Note") || json.has("Information")) {
                System.out.println("API call failed or limit reached:");
                System.out.println(response.getBody());
                return;
            }

            // Get the array of stock quotes
            JsonArray quotesArray = json.getAsJsonArray("Realtime Bulk Quotes");

            if (quotesArray == null) {
                System.out.println("No stock data found in response:");
                System.out.println(response.getBody());
                return;
            }

            List<Stock> stockList = new ArrayList<>();

            // Loop through each stock and add to list
            for (JsonElement element : quotesArray) {
                JsonObject quote = element.getAsJsonObject();
                String symbol = quote.get("01. symbol").getAsString();
                double price = quote.get("05. price").getAsDouble();
                stockList.add(new Stock(symbol, price));
            }

            // Sort list descending by price
            stockList.sort((s1, s2) -> Double.compare(s2.price, s1.price));

            // Print all stocks
            System.out.println("Stocks sorted by price (highest to lowest):");
            for (Stock s : stockList) {
                System.out.println(s.symbol + " - $" + s.price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StockTracker tracker = new StockTracker();
        tracker.ShowAll();
    }
}
