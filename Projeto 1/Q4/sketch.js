var time=60*4,cycle_time=60*8
var axisSize = 80
var x=0,y=0,r=20
var xlst=-r,ylst=0


function setup(){
  createCanvas(600,600)
  frameRate(60)
  background(0)
  grid()
}
function draw(){
  let angle=map(time,0,cycle_time-1,0,TWO_PI);
  let x1=x+r*cos(angle)
  let y1=y+r*sin(angle)
  if(time==60*8 -1 || time == 60*4-1){
    x = xlst
    y = ylst
    xlst = x1
    ylst = y1
    r*=2
  }  

  plot(x1,y1)

  time=(time+1)%cycle_time
}


function grid(){
  strokeWeight(0)
  stroke(255)
}

function plot(x,y){
  strokeWeight(4)
  stroke(0,0,255)
  px = map(x/10,-axisSize/2,axisSize/2,0,width)
  py = map(y/10,-axisSize/2,axisSize/2,height,0)
  point(px,py)
  strokeWeight(1)
  stroke(255,0,0)
  line(0, 300, 600, 300)
  line(300, 0, 300, 600)
}
