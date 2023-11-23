//
//import com.google.common.collect.Lists;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//
//        int a =3;
//        for(int i=0; a>=i ;i++) {
//
//            // Configura el controlador de Gecko y entrar a la pagina
//            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
//            FirefoxOptions options = new FirefoxOptions();
//            WebDriver driver = new FirefoxDriver(options);
//            driver.get("https://monsterhunterworld.wiki.fextralife.com/Monsters");
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//
//
//            // Aceptar privacidad
//            WebElement botonAceptar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]")));
//            WebElement privacidad = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]"));
//            privacidad.click();
//
//            List<MonsterData> monsterDataList = new ArrayList<>();
//
//            // Sacar todos los nombres de monstruos
//
//            WebElement divElement = driver.findElement(By.xpath("(//div[@id='tagged-pages-container'])[1]"));
//            List<WebElement> links = divElement.findElements(By.tagName("a"));
//
//            for (WebElement link : links) {
//                String nombre = link.getText();
//                System.out.println(nombre);
//            }
//            driver.quit();
//
//
//            //2.1 Entar en monstruo1
//            driver = new FirefoxDriver(options);
//            driver.get("https://monsterhunterworld.wiki.fextralife.com/Acidic+Glavenus");
//
//            // Aceptar privacidad
//            wait = new WebDriverWait(driver, 10);
//            WebElement botonAceptar2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]")));
//            WebElement privacidad2 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]"));
//            privacidad2.click();
//
//            // Encontrar el elemento <img> con el título "acidic-glavenus-gallery"
//            WebElement imgElement = driver.findElement(By.xpath("//img[@title='acidic-glavenus-gallery']"));
//            String imgSrc = imgElement.getAttribute("src");
//            System.out.println("Imagen URL: " + imgSrc);
//
//            // Encontrar el elemento que contiene el texto y Obtener el texto del elemento
//            WebElement paragraphElement = driver.findElement(By.xpath("//p[contains(text(),'A new subspecies to the ferocious Glavenus, the Acidic Glavenus')]"));
//            String texto = paragraphElement.getText();
//            System.out.println(texto);
//
//            driver.quit();
//
//
//            //2.2 Entrar en especie de monstruo1
//            driver = new FirefoxDriver(options);
//            driver.get("https://monsterhunterworld.wiki.fextralife.com/Brute+Wyverns");
//
//            // Aceptar privacidad
//            wait = new WebDriverWait(driver, 10);
//            WebElement botonAceptar3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]")));
//            WebElement privacidad3 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]"));
//            privacidad3.click();
//
//            // Encontrar el elemento que contiene el texto y Obtener el texto del elemento
//            WebElement titulo = driver.findElement(By.xpath("//*[@id=\"page-title\"]"));
//            String text1 = titulo.getText();
//            System.out.println(text1);
//
//
//            // Encontrar el elemento que contiene el texto y Obtener el texto del elemento
//            WebElement Brute = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[2]/div/div/div/div[2]/div/div[4]/div[1]/p[1]"));
//            String text2 = Brute.getText();
//            System.out.println(text2);
//
//            driver.quit();
//
//
//            //2.2 volver al monstruo1
//            driver = new FirefoxDriver(options);
//            driver.get("https://monsterhunterworld.wiki.fextralife.com/Rotten+Vale");
//
//            // Aceptar privacidad
//            wait = new WebDriverWait(driver, 10);
//            WebElement botonAceptar4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]")));
//            WebElement privacidad4 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]"));
//            privacidad4.click();
//
//
//            WebElement blockquoteElement = driver.findElement(By.tagName("blockquote"));
//
//            System.out.println(blockquoteElement.findElement(By.tagName("p")).getText());
//
//
//        }
//    }
//}
