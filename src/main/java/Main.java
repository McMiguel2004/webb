
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {


            // Configura el controlador de Gecko y entrar a la pagina
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            WebDriver driver = new FirefoxDriver(options);
            driver.get("https://monsterhunterworld.wiki.fextralife.com/Monsters");
            WebDriverWait wait = new WebDriverWait(driver, 10);


            // Aceptar privacidad
            WebElement botonAceptar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]")));
            WebElement privacidad = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]"));
            privacidad.click();


            // Sacar todos los nombres de monstruos

            List<String> links = new ArrayList<>();

            WebElement divElement = driver.findElement(By.xpath("(//div[@id='tagged-pages-container'])[1]"));

            List<WebElement> tittle = divElement.findElements(By.className("wiki_link"));

            for (WebElement nombres : tittle) {
                links.add(nombres.getAttribute("href"));
            }

            for (String juegosLink : links) {
                driver.get(juegosLink);
                Thread.sleep(2000);


                WebElement aasdasaaaaaaad = driver.findElement(By.className("table-responsive"));
                List<WebElement> titsssssstle = aasdasaaaaaaad.findElements(By.className("wiki_table"));

                for (WebElement nombressadasds : titsssssstle) {
                    System.out.println(nombressadasds.findElement(By.tagName("h2")).getText());

                    WebElement blockquoteElement = driver.findElement(By.tagName("blockquote"));
                    System.out.println(blockquoteElement.findElement(By.tagName("p")).getText());


                }

            }


    }
}






