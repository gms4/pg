class Board{
    constructor(curves){
        this.curves = curves;
        this.start = width/10;
        this.length = width - this.start;
        this.selected = 0;
        this.viewAll = false;
        this.viewPoints = true;
        this.viewPolys = true;
        this.viewCurves = true;
        this.evaluations = 100;
        this.colors = [
            [255, 0, 0], //RED
            [0, 255, 0], //GREEN
            [0, 0, 255], //BLUE
            [255, 255, 0], //YELLOW
            [0, 255, 255], //CYAN
            [255, 0, 255], //MAGENTA
            [255, 150, 150], //LIGHT_RED
            [150, 255, 150], //LIGHT_GREEN
            [150, 150, 255], //LIGHT_BLUE
            [255, 255, 150], //LIGHT_YELLOW
            [150, 255, 255], //LIGHT_CYAN
            [255, 150, 255], //LIGHT_MAGENTA
        ]
    }

    render(){
        push()
            stroke(10)
            fill(255, 0, 0)
            circle(0, 0, 70)
            translate(
                this.start + (this.length/2),
                height/2
            )

            fill(25, 25, 25)
            rect(-this.length/2, -height/2, this.length, height)

            //coordinates
            strokeWeight(3)
            stroke(255)
            //x
            line(-width/2, 0, width/2, 0)
            //y
            line(0, -height/2, 0, height/2)
            strokeWeight(1)

            for(let b = 0; b < this.curves.length; b++){
                
                var col = random(this.colors);

                if(b == this.selected){
                    var foreground = b;
                    var foregroundColor = col;
                }else{
                    this.curves[b].render(col, this.viewAll, this)
                }
            }

            //renderiza a curva selecinoada
            if(this.curves.length > 0){
                this.curves[foreground].render(foregroundColor, true, this)
            }

        pop()
    }

    alternate(){
        if(this.selected < this.curves.length - 1){
            this.selected += 1;
        }else{
            this.selected = 0;
        }
    }

    alternateEvaluationNumber(){
        if(this.evaluations < 20){
            this.evaluations += 5;
        }else if(this.evaluations < 50){
            this.evaluations += 10;
        }else if(this.evaluations < 100){
            this.evaluations += 50;
        }else if(this.evaluations < 250){
            this.evaluations += 100;
        }else if(this.evaluations < 750){
            this.evaluations += 250;
        }else{
            this.evaluations = 5;
        }
    }

    view(){
        this.viewAll = !this.viewAll
    }

    delete(){
        if(this.selected == 0){
            this.curves.shift()
        }else{
            this.curves.splice(this.selected, this.selected)
            this.selected = 0
        }
    }
}