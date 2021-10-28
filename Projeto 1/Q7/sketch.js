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

function looping(){
  noFill()
  beginShape()
  vertex(400, 75, 0)  // A2
  vertex(400, 125, 0)  // A1
  vertex(-100, (50/3)+25, 500)   // B1
  vertex(0, 25, 600)   // C1
  vertex(100, (-50/3)+25, 500)   // D1
  vertex(-400, -75, 0)   //E1
  vertex(-400, -125, 0)  //E2
  vertex(100, (-50/3)-25, 500)   // D2
  vertex(0, -25, 600)   // C2
  vertex(-100, (50/3)-25, 500)   // B2
  endShape(CLOSE)
}

function draw() {
  background(50);
  orbitControl()
  
  rotateX(-RAD/3)
  rotateX(-RAD/2)
  rotateZ(-RAD/3)
  rotateZ(RAD/8)
  
  strokeWeight(2)
  stroke(255, 0, 0)
  line(-1000,0,0, 1000,0,0)   //X
  stroke(0, 255, 0)
  line(0,1000,0, 0,-1000,0)  //Y
  stroke(0, 0, 255)
  line(0,0,-1000, 0,0,1000)   //Z
  stroke(0)

  looping()
  
}
