import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase extrae información de monstruos de un sitio web y genera archivos CSV y XML con los datos obtenidos.
 */
public class Main {

    /**
     * Método principal que inicia el proceso de extracción de datos.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        try {
            // Configuración del WebDriver para Firefox y navegación a la página web
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            WebDriver driver = new FirefoxDriver(options);
            driver.get("https://monsterhunterworld.wiki.fextralife.com/Monsters");
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // Espera y aceptación del aviso de cookies
            WebElement botonAceptar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]")));
            WebElement privacidad = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[2]"));
            privacidad.click();

            // Extracción de enlaces de monstruos de la página principal
            List<String> links = new ArrayList<>();
            WebElement divElement = driver.findElement(By.xpath("(//div[@id='tagged-pages-container'])[1]"));
            List<WebElement> tittle = divElement.findElements(By.className("wiki_link"));

            int linkCounter = 0;

            for (WebElement nombres : tittle) {
                if (linkCounter < 40) {
                    links.add(nombres.getAttribute("href"));
                    linkCounter++;
                } else {
                    break;
                }
            }

            // Creación del archivo CSV para almacenar los datos con comillas como delimitador
            FileWriter csvWriter = new FileWriter("monstruos.csv");
            csvWriter.append("\"Nombre\",\"Imagen\",\"Descripcion\"\n");

            // Creación del documento XML para almacenar los datos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Monstruos");
            doc.appendChild(rootElement);

            // Bucle para cada enlace de monstruo extraído
            for (String juegosLink : links) {
                driver.get(juegosLink);
                Thread.sleep(2000);

                // Extracción de información de cada monstruo
                WebElement tabla = driver.findElement(By.className("table-responsive"));
                List<WebElement> tablilla = tabla.findElements(By.className("wiki_table"));

                for (WebElement nombrestexto : tablilla) {
                    String nombre = nombrestexto.findElement(By.tagName("h2")).getText();
                    String imagen = nombrestexto.findElement(By.tagName("tbody")).findElement(By.tagName("img")).getAttribute("src");
                    String descripcion = driver.findElement(By.tagName("blockquote")).findElement(By.tagName("p")).getText();

                    // Escritura de datos en el archivo CSV
                    csvWriter.append("\"").append(nombre).append("\",");
                    csvWriter.append("\"").append(imagen).append("\",");
                    csvWriter.append("\"").append(descripcion).append("\"\n");

                    // Estructuración del documento XML
                    Element monsterElement = doc.createElement("Monstruo");
                    rootElement.appendChild(monsterElement);

                    Element nameElement = doc.createElement("Nombre");
                    nameElement.appendChild(doc.createTextNode(nombre));
                    monsterElement.appendChild(nameElement);

                    Element imgElement = doc.createElement("Imagen");
                    imgElement.appendChild(doc.createTextNode(imagen));
                    monsterElement.appendChild(imgElement);

                    Element descriptionElement = doc.createElement("Descripcion");
                    descriptionElement.appendChild(doc.createTextNode(descripcion));
                    monsterElement.appendChild(descriptionElement);

                    // Extracción de enlaces adicionales dentro de la página del monstruo
                    List<String> linkstabla = new ArrayList<>();
                    WebElement divtabla = driver.findElement(By.className("wiki_table"));
                    List<WebElement> tittle2 = divtabla.findElements(By.className("wiki_link"));

                    for (WebElement nombresTabla : tittle2) {
                        linkstabla.add(nombresTabla.getAttribute("href"));
                    }

                    // Bucle para cada enlace adicional
                    for (int i = 1; i < linkstabla.size(); i++) {
                        String tablaLink = linkstabla.get(i);
                        driver.get(tablaLink);
                        Thread.sleep(2000);

                        // Extracción de información adicional y actualización del documento XML
                        WebElement blockquoteElement2 = driver.findElement(By.id("wiki-content-block"));
                        Element subDescriptionElement = doc.createElement("SubDescripcion");
                        subDescriptionElement.appendChild(doc.createTextNode(blockquoteElement2.findElement(By.tagName("p")).getText()));
                        monsterElement.appendChild(subDescriptionElement);
                    }
                }
            }

            // Finalización y cierre del archivo CSV
            csvWriter.flush();
            csvWriter.close();

            // Transformación y escritura del documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("monstruos.xml"));
            transformer.transform(source, result);

            // Cierre del navegador WebDriver
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

