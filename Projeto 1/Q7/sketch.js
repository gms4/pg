const UC = 1.5
const RAD = 6.28319
const RED = [255, 0, 0]
const GREEN = [0, 255, 0]
const BLUE = [0, 0, 255]
const YELLOW = [255, 255, 0]

function setup() {
  createCanvas(600, 600, WEBGL);
  frameRate(60)
}

function looping(){
  noFill()
  stroke(YELLOW)
  bezier(400,-100,0 , -800,-200,600, 800,200,600, -400,100,0)
}

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

function draw() {
  background(50);
  orbitControl()
  
  rotateX(-RAD/3)
  rotateX(-RAD/2)
  rotateZ(-RAD/3)
  rotateZ(RAD/8)
  
  coordLines()
  looping()

  translate(400,-100,0)
  normalMaterial()
  sphere(20*UC)
  
}
