class Point{
    constructor(x, y){
        this.x = x;
        this.y = y;
        this.grabbed = false;
    }

    grab(){
        this.grabbed = true;
    }

    drop(){
        this.grabbed = false;
    }

}