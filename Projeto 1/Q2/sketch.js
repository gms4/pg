function setup() {
  createCanvas(700, 700);
  frameRate(60);
}

//x=x0+r·cos(t), y=y0+r·sen(t)
function draw() {

  background(0);
  stroke(255, 255, 255);
  
  //iniciando completamente alinhado na vertical,
  //finalizando completamente alinhado na horizontal
  //fazendo o movimento giratorio
  //framecount contabiliza os frames desde o comeco da animacao
  let angle = map(min(120, frameCount), 1, 120, 0, PI/4);
  
  //tamanho dos bracos
  let arm = 100, forearm = 150;

  //ponto p1 fixo
  let x0 = width/2, y0 = 2.25*height/5;

  //usando map pra fazer a variacao de movimento dos pontos p2 e p3
  //sin(angle) e cos(angle) serao normalizados
  //-1 e 1 representam o minimo e o maximo
  //sen e cos variam de -1 a 1
  //xo-arm e x0+arm representam o minimo e o maximo do valor que queremos atingir
  //p2 = (x1, y1), p3 = (x2, y2)
  let x1 = map(sin(angle), -1, 1, x0-arm, x0+arm);
  let y1 = map(cos(angle), -1, 1, y0-arm, y0+arm);
  
  //Vang (forearm) = Vang (arm), pois a questao pede
  let x2 = map(sin(2*angle), -1, 1, x1-forearm, x1+forearm);
  let y2 = map(cos(2*angle), -1, 1, y1-forearm, y1+forearm);

  //criando os pontos que formarao o braco, e a grossura deles
  strokeWeight(8)
  point(x0, y0);
  point(x1, y1);
  point(x2, y2);
  strokeWeight(2);
  stroke(0, 0, 255);

  //juntando os pontos acima pra criar o braco
  //p1 -> p2, p2 -> p3
  beginShape(LINES);
    vertex(x0, y0);
    vertex(x1, y1);
    vertex(x1, y1);
    vertex(x2, y2);
  endShape();
}
