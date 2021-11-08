var t = 0;

function setup(){
  createCanvas(600,600);
  frameRate(60);
}

function interpolate(p0, p1, t){
  return (1-t)*p0 + t*p1
}

//interpolação 
function linearInterpolation(p0, p1, t){
    let pos = []

    pos[0] = interpolate(p0[0], p1[0], t) //x
    pos[1] = interpolate(p0[1], p1[1], t) //y

    return pos
}

function quadraticBezier(p0, p1, p2, p3, t){
  let cp0 = linearInterpolation(p0, p1, t)
  let cp1 = linearInterpolation(p1, p2, t)
  let cp2 = linearInterpolation(p2, p3, t)

  let aux0 = linearInterpolation(cp0, cp1, t)
  let aux1 = linearInterpolation(cp1, cp2, t)
  return linearInterpolation(aux0, aux1, t)
}

function draw() {
  background(50);
  noFill()
  grid()
  translate(width/2, height/2)

  bezier(100, 0, 150, -200, -150, -200, -200, 0)
  var p = quadraticBezier([100, 0],[150, -200], [-150, -200],[-200,0 ], t)
  stroke(255,0,0)
  fill(255, 0,0)
  circle(p[0], p[1], 12)
  t >= 1 ? t = 0 : t += 1/240;
}


function grid(){
  strokeWeight(1);
  stroke(0, 255, 0);
  line(0,height/2,width,height/2);
  line(width/2,0,width/2,height)
  strokeWeight(2);
  for(let i = 0; i <= width; i+= width/12){ // X axis
    line(i,height/2-1,i,height/2+1);
  }
  for(let i = 0; i <= width; i+= width/12){ // Y axis
    line(width/2-1,i,width/2+1,i);
  }
}
