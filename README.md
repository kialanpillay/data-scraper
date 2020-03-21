# COVID-19 South Africa Data Scraper
A tool to automate the retrieval and parsing of SA COVID-19 data from the NICD website. Run this tool everyday to produce summary statistics grouped by gender, province, and transmission type.

## Run
```javac DataScraper.java```  

Indicate the alert number of the NICD data release with the ```-u``` flag.  

```java DataScraper -u 23```


## Notes
This tool uses the jsoup library for it's functionality.
It can be downloaded at this [link](https://jsoup.org/download)
