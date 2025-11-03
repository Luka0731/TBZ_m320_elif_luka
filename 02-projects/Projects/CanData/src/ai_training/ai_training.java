package ai_training;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class ai_training {
    public static void main(String[] args) {
        // Spark-Session starten
        SparkSession spark = SparkSession.builder()
                .appName("Spark MLlib Beispiel")
                .master("local[*]")  // lokal laufen lassen
                .getOrCreate();

        // Beispiel-Dataset
        Dataset<Row> data = spark.createDataFrame(
                java.util.Arrays.asList(
                        new Sentence("Ich liebe Machine Learning"),
                        new Sentence("Spark ist super schnell")
                ),
                Sentence.class
        );

        // Einfaches MLlib-Beispiel: Tokenizer
        Tokenizer tokenizer = new Tokenizer().setInputCol("text").setOutputCol("words");
        Dataset<Row> tokenized = tokenizer.transform(data);
        tokenized.show(false);

        spark.stop();
    }

    public static class Sentence implements java.io.Serializable {
        private String text;
        public Sentence(String text) { this.text = text; }
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
