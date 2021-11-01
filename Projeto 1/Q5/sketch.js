const UC = 2
const outer_circle_radius = 100*UC
const inner_circle_radius = 25*UC
const rad = 6.28319
var outer_angle = 0
var inner_angle = 0

function setup() {
  createCanvas(400, 400);
  frameRate(60)
}

function inner_circle(pos) {
  if(outer_angle<=-rad) outer_angle = 0 //reset angle count
  rotate(outer_angle-=(rad)/(4*60)) //rotaciona a elipse menor ao redor da origem
  ellipse(pos.x, pos.y, inner_circle_radius)
  fill(255, 0, 0)
  translate(pos.x, pos.y) //translada a origem pro centro do circulo menor
  rotate(inner_angle+=(rad)/(60)) //rotaciona o ponto vermelho ao redor do centro do circulo menor
  ellipse(inner_circle_radius/2, 0, 5*UC)
  fill(255)
}

function draw() {
  background(220, 220, 220, 50);
  translate(width/2, height/2) //translada a origem pro centro
  ellipse(0 , 0, outer_circle_radius) 
  inner_circle({x: (outer_circle_radius-inner_circle_radius)/2, y: 0})
}
