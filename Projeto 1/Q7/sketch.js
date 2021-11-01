const UC = 1.5
const RAD = 6.28319
const RED = [255, 0, 0]
const GREEN = [0, 255, 0]
const BLUE = [0, 0, 255]
const YELLOW = [255, 255, 0]
var t = 0

function setup() {
  createCanvas(1536, 760, WEBGL);
  frameRate(60)
}

//desenha as linhas das coordenadas
function coordLines(){
  strokeWeight(2)
  stroke(RED)
  line(-1000,0,0, 1000,0,0)   //X
  stroke(GREEN)
  line(0,1000,0, 0,-1000,0)  //Y
  stroke(BLUE)
  line(0,0,0, 0,0,1000)   //Z
  stroke(0)
}

//equação de bezier
function interpolate(p0, p1, t){
  return (1-t)*p0 + t*p1
}

//interpolação 
function linearInterpolation(p0, p1, t){
    let pos = []

    pos[0] = interpolate(p0[0], p1[0], t) //x
    pos[1] = interpolate(p0[1], p1[1], t) //y
    pos[2] = interpolate(p0[2], p1[2], t) //z

    return pos
}

function cubicBezier(p0, p1, p2, p3, t){
  let cp0 = linearInterpolation(p0, p1, t)
  let cp1 = linearInterpolation(p1, p2, t)
  let cp2 = linearInterpolation(p2, p3, t)

  let aux0 = linearInterpolation(cp0, cp1, t)
  let aux1 = linearInterpolation(cp1, cp2, t)
  return linearInterpolation(aux0, aux1, t)
}

function draw() {
  background(50, 50, 50);
  orbitControl()
  
  rotateX(-RAD/3)
  rotateX(-RAD/2)
  rotateZ(-RAD/3)
  rotateZ(RAD/8)
  
  coordLines()
  
  noFill()
  stroke(YELLOW)
  bezier(400,-100,0 , -900,60,800, 900,-60,800, -400,100,0)

  let newPos = cubicBezier(
    [400,-100,0],
    [-900,60,800],
    [900,-60,800],
    [-400,100,0],
    t
  )

  //pontos dados na questão
  stroke(255, 0, 255)
  strokeWeight(10)
  point(400,-100,0)
  point(-100,-50/3,500)
  point(0,0,600)
  point(100,50/3,500)
  point(-400,100,0)

  //criando esfera na posição com base na função cubica de bezier
  print(newPos)
  translate(newPos[0], newPos[1], newPos[2])
  normalMaterial()
  sphere(20*UC)
  
  t >= 1 ? t = 0 : t+=(1/480)
}
