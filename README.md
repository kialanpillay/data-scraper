# COVID-19 South Africa Data Scraper
A tool to automate the retrieval and parsing of SA COVID-19 data from the NICD website. Run this tool everyday to produce summary statistics grouped by gender, province, and transmission type.

UPDATE: This tool no longer works since the NICD ceased to publish regular and categorised updates.

## Notes
This tool uses the jsoup library for it's functionality.
It can be downloaded at this [link](https://jsoup.org/download).
Ensure the .jar file is placed in the same directory as the source code

## Run
```javac -cp .;jsoup-1.7.3.jar DataScraper.java```
This step compiles the executable with the neccasary functionality from the jsoup library.
```java -cp .;jsoup-1.7.3.jar DataScraper -u 23```
Indicate the alert number of the NICD data release with the ```-u``` flag.


