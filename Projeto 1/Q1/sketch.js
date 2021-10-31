const UC = 1
let y = 345;
let x = 16;
let speedy=10*UC;
let speedx=4.75*UC;
let gravity=0.5;

function setup() {
  createCanvas(600, 400);
}

function draw() {
  background(0, 0, 0, 20);
  noStroke()
  fill(50, 255, 255)
  ellipse(x, y, 30, 30);
  fill(0, 255, 50)
  rect(0, height-55, width, 55);
  
  print(speedy, speedx)


  if (x >= width-15 || x <= 15) {
    speedx = -speedx;
  }
  
  y=y+speedy;
  x=x+speedx;
  
  speedy=speedy+gravity;
  
  if(y>height-55){
  //reverse the speed
    speedy = -(speedy-gravity);
  }
}