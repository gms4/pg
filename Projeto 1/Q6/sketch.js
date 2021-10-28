const UC = 1.5
const RAD = 6.28319
const RED = [255, 0, 0]
const GREEN = [0, 255, 0]
const BLUE = [0, 0, 255]
const outer_circle_diam = 100*UC
const inner_circle_diam = 25*UC
const indicator_diam = 8*UC
var outer_angle = 0
var inner_angle = 0

function setup() {
  createCanvas(600, 600, WEBGL);
  frameRate(60)
}

function inner_circle(pos) {
  
  
  translate(pos.x, -pos.y)  //  muda a origem
  point(0, 0)               //  mostra o ponto de rotação
  rotate(outer_angle)       //  rotaciona ao redor da origem
  fill(0, 0, 255)
  rotateX(RAD/4)            //rotaciona o plano no eixo X
  rotateY(-RAD/6) 
  translate(outer_circle_diam/2, 0, 50)
  circle(0, 0, inner_circle_diam)
  
  strokeWeight(1)
  fill(255, 0, 0)
  translate(0, 0)
  rotate(inner_angle)
  stroke(256, 0, 0)
  strokeWeight(10)
  point((inner_circle_diam/2),(0),)


}

function draw() {
  background(220);
  //ambientLight(255)

  camera()
  
  rotateX(-RAD/16)
  rotateY(RAD/16)
  
  strokeWeight(2)
  stroke(255, 0, 0)
  line(0,0,0, 1000,0,0)   //X
  stroke(0, 255, 0)
  line(0,0,0, 0,-1000,0)  //Y
  stroke(0, 0, 255)
  line(0,0,0, 0,0,1000)   //Z
  stroke(0)
  
  rotateY(-RAD/4)
  rotateX(RAD/6)
  strokeWeight(2)
  noFill()
  rect(0, 0, outer_circle_diam, -outer_circle_diam)
  fill(255)
  circle((outer_circle_diam/2), -(outer_circle_diam/2), outer_circle_diam)

  inner_circle({x: outer_circle_diam/2, y: outer_circle_diam/2})

  outer_angle -= RAD/240
  inner_angle += RAD/60
}
