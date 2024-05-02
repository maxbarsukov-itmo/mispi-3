import { drawCanvasGraph } from "./graph";
import "../css/main.css"

window.drawIt = drawCanvasGraph
function updateErrorMessageR(r) {
  document.querySelector("#error-message").innerHTML = r ? "" : "R не установлено";
}

window.updateErrorMessageR = updateErrorMessageR;
