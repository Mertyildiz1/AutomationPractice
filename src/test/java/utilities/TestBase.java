package utilities;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class TestBase {

    /*
        Test Class içersinde her defasında driver ayarları yapmak yerine
     driver ayarlarını TestBase ismini verdiğimiz class'da tutabiliriz.
     Büyük projelerde method ve class isimlerinde karışıklıklar olabilir
     bunun önüne geçmek için bu class'ı abstract yaparız ve obje oluşumunun
     önüne geçmiş oluruz. Böylece bu class'î sadece miras alarak kullanabiliriz.
     */
    protected WebDriver driver;
    protected Select select;
    protected Actions actions;
    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        /*
            ImplicitlyWait seleniumdan gelen akıllı/dinamik bekleme türlerinden biridir.
        Nasıl manage() methodu ile tarayıcıya maximum olma özelliğini veriyorsak aynı manage()
        methodu ile tarayıcıya max. 15 saniye(Duration.ofSeconds(15)) elemenler oluşana kadar bekle demiş oluyoruz.
            ImplicitlyWait bu beklemeyi tüm elementler için uygular taki oturum sonlanana kadar(driver.quit()).
        Element 2 saniyede oluşursa bir alt satıra geçer ama element belirtilen max. süre boyunca oluşmaz/görüntünemez
        ise exception fırlatır kod çalışmayı durdurur.
         */
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(3000);
        //driver.quit();
    }

    //Hard Wait
    public void bekle(int saniye){
        try {
            Thread.sleep(1000*saniye);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Pencere Geçiş
    public void window(int index){
        driver.switchTo().window(driver.getWindowHandles().toArray()[index].toString());
    }

    //DDM index
    public void selectIndex(WebElement ddmElement, int index){
        select = new Select(ddmElement);
        select.selectByIndex(index);
    }

    //Alert Accept
    public void alertOK(){
        driver.switchTo().alert().accept();
    }
}
