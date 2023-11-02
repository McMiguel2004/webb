
import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

  public static void main(String[] args) {
    // Configura el controlador de Gecko
    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");

    // Configura opciones para el navegador Firefox (opcional)
    FirefoxOptions options = new FirefoxOptions();

    // Inicializa el WebDriver
    WebDriver driver = new FirefoxDriver(options);

    // Abre la página web
    driver.get("https://monsterhunterworld.wiki.fextralife.com/Monster+Hunter+World+Wiki");

    // Obtiene el título de la página
    String pageTitle = driver.getTitle();
    System.out.println("Título de la página: " + pageTitle);

    // Puedes continuar interactuando con la página para obtener más información, por ejemplo, encontrar elementos por su etiqueta, clase, id, etc.
    // Por ejemplo, para encontrar un elemento por su etiqueta <h1>:
    WebElement headerElement = driver.findElement(By.tagName("h1"));
    System.out.println("Texto del encabezado: " + headerElement.getText());

    // Cierra el navegador al finalizar
    driver.quit();
  }
}
