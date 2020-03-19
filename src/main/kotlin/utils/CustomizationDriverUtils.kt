package utils

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

fun addBorderForEachClickedButton(driver: WebDriver) {
  (driver as JavascriptExecutor)
    .executeScript(
      "document.addEventListener('click',  function() {\n" +
          "    var e = event || window.event;\n" +
          "    var target = e.target || e.srcElement;\n" +
          "    target.style['box-sizing'] = 'border-box';\n" +
          "    target.style['border'] = '2px solid blue';\n" +
          "})"
    )
}