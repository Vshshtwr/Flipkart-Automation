package demo;

import java.util.List;
import org.testng.annotations.*;


import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import io.github.bonigarcia.wdm.WebDriverManager;






public class TestCases {
    static ChromeDriver driver;
    static wrapperMethods wrapper;

    @BeforeSuite(alwaysRun = true)
    public static void driverSetup() {
        System.out.println("Initializing : TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @BeforeClass
    public void setUp() {
        wrapper = new wrapperMethods(driver); // Initialize utility here after driver setup
    }

    @Test(priority = 1, enabled = true)
    public void testCase01() throws InterruptedException {

        System.out.println("Start Test case: testCase01");
        driver.get("https://www.flipkart.com");

        wrapper.searchBar(By.xpath(
                "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input"),
                "Washing Machine");

        wrapper.clickElement(By.xpath("//div[text()='Popularity']"));

        Thread.sleep(5000);

        List<WebElement> ratings = driver.findElements(By.className("XQDdHH"));

        int count = 0;
        for (WebElement rating : ratings) {
            double ratingCount = Double.parseDouble(rating.getText());
            if (ratingCount <= 4.0) {
                count++;

            }
        }

        System.out.println(" the count of items with rating less than or equal to 4 stars" + count);

        Thread.sleep(2000);
    }

    @Test(priority = 2, enabled = true)

    public void testCase02() throws InterruptedException {

        System.out.println("Start Test case: testCase02");

        driver.get("https://www.flipkart.com");
        
        Thread.sleep(7000);


        wrapper.searchBar(By.xpath(
            "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input"),
            "Iphone");
        Thread.sleep(2000);

        List<WebElement> discount = driver.findElements(By.className("tUxRFH"));
        int countiphone = 0;
        for (WebElement discounts : discount) {

            WebElement DiscountElement = discounts.findElement(By.className("UkUFwK"));
            String DiscountText = DiscountElement.getText().replaceAll("% off", "");

            WebElement title = discounts.findElement(By.className("KzDlHZ"));
            String titleText = title.getText();

            int Disc = Integer.parseInt(DiscountText);
            if (Disc > 17) {
                System.out.println("Title: " + titleText + ", Discount " + Disc + "%");
                countiphone++;
    
            }
           
            Thread.sleep(2000);

        }

    }

    @Test(priority = 3, enabled = true)

    public void testCase03() throws InterruptedException {

        System.out.println("Start Test case: testCase03");

        driver.get("https://www.flipkart.com");
        Thread.sleep(5000);

        wrapper.searchBar(By.xpath(
            "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input"),
            "Coffee Mug");
        Thread.sleep(7000);

        wrapper.clickElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[5]/div[2]/div/div[1]/div/label/div[1]"));

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='_5OesEi afFzxY']/span/div"));
        List<Item> itemList = new ArrayList<>();
        for (WebElement item : items) {
            try {

                WebElement titleElement = item.findElement(By.xpath(".//a[@class='wjcEIp']"));
                WebElement reviewsElement = item.findElement(By.xpath(".//span[@class='Wphh3N']"));
                WebElement imageElement = item.findElement(By.xpath(".//img[@class='DByuf4']"));

                String title = titleElement.getText();
                String reviewsText = reviewsElement.getText().split(" ")[0].replaceAll(",", "");
                int reviews = Integer.parseInt(reviewsText);
                String imageUrl = imageElement.getAttribute("src");
                itemList.add(new Item(title,reviews,imageUrl));
                

            } catch (Exception e) {
                // Skip the item if any element not found
            }
        }

        // Sort items by the number of reviews in descending order
        itemList.sort(Comparator.comparingInt(Item::getReviews).reversed());

        // Print the top 5 items
        itemList.stream().limit(5).forEach(item -> 
            System.out.println("Title: " + item.getTitle() + ", Image URL: " + item.getImageUrl()));

            System.out.println("end Test case: testCase01");

    }

    @AfterSuite(alwaysRun = true)
    public static void quitDriver() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

        

    }
    class Item {
        private String title;
        private int reviews;
        private String imageUrl;

        public Item(String title, int reviews, String imageUrl) {
            this.title = title;
            this.reviews = reviews;
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public int getReviews() {
            return reviews;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

