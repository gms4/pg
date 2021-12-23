class Curve{
    constructor(controlPoints){
        this.originalControlPoints = controlPoints
        this.controls = this.originalControlPoints
        this.colorSet = false
        this.color = [255];
    }

    //equação de bezier
    interpolate(p0, p1, t){
        return new Point(
            ( (1-t)*p0.x + t*p1.x ),
            ( (1-t)*p0.y + t*p1.y )
        )
    }
  

    render(pcol, sel, board){
        var evaluated = []
        var col;

        if(!this.colorSet){
            this.color = pcol
            this.colorSet = true
        }

        col = this.color

        //checa se existe pelo menos um ponto de controle
        if(this.originalControlPoints.length > 0){
            //parametro t
            for(let t = 0; t <= 1; t+=1/board.evaluations){
                this.controls = this.originalControlPoints

                //calcula até a útilma camada
                while(this.controls.length > 1){
                    let aux = []

                    //calcula em todas as poligonais dessa camada
                    for(let i = 0; i < this.controls.length - 1; i++){
                        //interpola
                        aux[i] = this.interpolate(this.controls[i], this.controls[i+1], t)
                    }
                    this.controls = aux
                }
                evaluated.push(this.controls[0])
            }

            if(board.viewCurves){
                for(let i = 0; i < evaluated.length-1; i++){
                    if(sel){
                        stroke(col)
                    }else{
                        stroke([col[0]-200, col[1]-200, col[2]-200])
                    }
    
                    strokeWeight(5)
                    line(
                        evaluated[i].x, evaluated[i].y,
                        evaluated[i+1].x, evaluated[i+1].y
                    )
                }
            }

            for(let p = 0; p < this.originalControlPoints.length; p++){

                var translucid;

                //caso a curva esteja selecionada
                if(sel){
                    translucid = [col[0]+100, col[1]+100, col[2]+100, 150]

                    if(p < this.originalControlPoints.length - 1 && board.viewPolys){
                        stroke(translucid)
                        strokeWeight(4)
                        line(
                            this.originalControlPoints[p].x,
                            this.originalControlPoints[p].y,
                            this.originalControlPoints[p+1].x,
                            this.originalControlPoints[p+1].y,    
                        )
                    }
                
                    fill(translucid)
                    strokeWeight(2)
                    if(board.viewPoints){
                        circle(
                            this.originalControlPoints[p].x,
                            this.originalControlPoints[p].y,
                            20
                        )
                    }
                }else{
                    translucid = [col[0]+100, col[1]+100, col[2]+100, 25]

                    if(p < this.originalControlPoints.length - 1 && board.viewPolys){
                        stroke(translucid)
                        strokeWeight(2)
                        line(
                            this.originalControlPoints[p].x,
                            this.originalControlPoints[p].y,
                            this.originalControlPoints[p+1].x,
                            this.originalControlPoints[p+1].y,    
                        )
                    }
                
                    fill(translucid)
                    strokeWeight(1)
                    if(board.viewPoints){
                        circle(
                            this.originalControlPoints[p].x,
                            this.originalControlPoints[p].y,
                            10
                        )
                    }
                }

            }
        }
        
    }
}