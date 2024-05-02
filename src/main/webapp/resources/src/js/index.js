import { drawCanvasGraph } from "./graph";
import "../css/main.css"

document.addEventListener('DOMContentLoaded', function() {
  drawCanvasGraph([], 1);

})

window.drawDots = drawCanvasGraph
function updateErrorMessageR(r) {
  document.querySelector("#error-message").innerHTML = r ? "" : "R не установлено";
}

window.updateErrorMessageR = updateErrorMessageR;
