package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.setErr(new PrintStream(System.err, true, "UTF-8"));
        Document doc = Jsoup.connect("https://dominodomoy.ru/catalog/").get();
        Elements category = doc.select("a.catalog-section-list-item-title.intec-cl-text-hover");
        for (Element e : category) {
            System.out.println(e.text());
            Document doc1 = Jsoup.connect("https://dominodomoy.ru" + e.attr("href")).get();
            Elements poz = doc1.select("div.catalog-section-item-wrapper");
            for (Element el : poz) {
                //String s = String.valueOf(el.select("div>div.catalog-section-item-content>div.catalog-section-item-name.text()"));
                Elements name = el.select("div.catalog-section-item-name");
                Elements price = el.select("div.catalog-section-item-price-discount");
                Elements image = el.select("img");
                System.out.println("\t" + name.text() + "\t" + price.text() + "\t" + "https://dominodomoy.ru"+image.attr("data-original"));
            }
        }
    }
}