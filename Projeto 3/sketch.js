var dragging, viewAll, creating = false;
var viewPoints = true;
var viewPolys = true;
var viewCurves = true;
var lastPointGrab, mx, my;
var currentCurve;
var btnHeight;

function setup() {
  createCanvas(1200, 600);
  
  btnHeight = height/8

  buttons = new Buttons();
  
  board = new Board([])
}

//checa clique do mouse
function mouseClicked(){
  buttons.alternateButton(mouseX, mouseY)
  buttons.addButton(mouseX, mouseY, board)
  if(!dragging){
    buttons.deleteButton(mouseX, mouseY, board)
  }
  buttons.evaluationsButton(mouseX, mouseY)
  
  buttons.viewButton(mouseX, mouseY)

  buttons.viewPointsButton(mouseX, mouseY)
  buttons.viewPolysButton(mouseX, mouseY)
  buttons.viewCurvesButton(mouseX, mouseY)

  if(creating && mouseX > width/10){
    board.curves[board.curves.length-1].originalControlPoints.push(new Point(mx, my))
  }
}

function keyPressed(){
  if(keyCode == 32){
    board.curves[board.selected].originalControlPoints.push(new Point(mx, my))
  }
  if(keyCode == DELETE){
    for(let i = 0; i < board.curves[board.selected].originalControlPoints.length; i++){
      
      var p = board.curves[board.selected].originalControlPoints[i]

      if(abs(mx - p.x) <= 10){
        if(abs(my - p.y) <= 10){
          board.curves[board.selected].originalControlPoints.splice(i, i)
        }
      }
      
      
    }
  }
}

//checa drag do mouse
function mousePressed() {

  if(viewAll){
    //checa se clicou algum ponto de controle da todas as curvas
    for(let c = 0; c < board.curves.length; c++){
      for(let i = 0; i < board.curves[c].originalControlPoints.length; i++){
        if(abs(mx - board.curves[c].originalControlPoints[i].x) <= 10){
          if(abs(my - board.curves[c].originalControlPoints[i].y) <= 10){
            lastPointGrab = board.curves[c].originalControlPoints[i];
            lastPointGrab.grab();
          }
        }
      }
    }
  }else{
    //checa se clicou algum ponto de controle da curva selecionada
    if(board.curves.length > 0){
      for(let i = 0; i < board.curves[board.selected].originalControlPoints.length; i++){
        if(abs(mx - board.curves[board.selected].originalControlPoints[i].x) <= 10){
          if(abs(my - board.curves[board.selected].originalControlPoints[i].y) <= 10){
            lastPointGrab = board.curves[board.selected].originalControlPoints[i];
            lastPointGrab.grab();
          }
        }
      }
    }
  }

}

function mouseReleased(){
  if(lastPointGrab != null)
    lastPointGrab.drop();
}

function renderUI(){

  var txt = [
    "⟳", "+", "🗑", "⛬", "👁",

    "☉", "☍", "☋", "✔"
  ]

  for(let i = 0; i < 8; i++){
    stroke(25, 25, 25)
    strokeWeight(2)
    if(mouseX < width/10 && mouseY > (i)*btnHeight && mouseY < (i+1)*btnHeight){
      fill(50, 75, 75)
    }else{
      fill(50, 50, 50)
    }

    if(creating && i==1){
      fill(50, 75, 75)
    }
    if(viewAll && i==4){
      fill(50, 75, 75)
    }
    if(viewPoints && i==5){
      fill(50, 75, 75)
    }
    if(viewPolys && i==6){
      fill(50, 75, 75)
    }
    if(viewCurves && i==7){
      fill(50, 75, 75)
    }
    rect(0, i*btnHeight, width/10, btnHeight)

    noStroke()
    textSize(35)
    fill(100, 255, 255)
    if(i == 1){
      if(creating){
        text(txt[txt.length-1], width/30, (i*btnHeight) + (btnHeight/2) + 10)
      }else{
        text(txt[i], width/30, (i*btnHeight) + (btnHeight/2) + 10)
      }
    }else if(i == 3){
      text(txt[i]+" "+board.evaluations, width/80, (i*btnHeight) + (btnHeight/2) + 10)
    }else{
      text(txt[i], width/30, (i*btnHeight) + (btnHeight/2) + 10)
    }
  }

  fill(255)
  textSize(14)
  text("Clique ESPAÇO para adicionar novos pontos de controle à curva selecionada", (width/10)+10, 25)
  text("Clique DELETE para remover pontos de controle da curva selecionada", (width/10)+10, 55)
}

function renderCurves(){
  board.render()
}

function draw() {
  background(220);
  mx = mouseX - (board.start + (board.length/2));
  my = mouseY - (height/2);
  renderCurves();
  if(lastPointGrab != null && lastPointGrab.grabbed){
    lastPointGrab.x = mx
    lastPointGrab.y = my
  }
  renderUI();
}
